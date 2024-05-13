package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.app.Application
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.data.sensor.XYZ
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.SpaceShipLasery
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.repository.sensor.GravityRepo
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.BoxCoordinates
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import com.mobilegame.spaceshooter.utils.extensions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class MotionsViewModel(
    application: Application,
//    context: Context,
    val ui: SpaceWarGameScreenUI,
//): ViewModel() {
//): AndroidViewModel() {
): AndroidViewModel(application) {
    val TAG = "MotionsViewModel"
    private val eventRepo: DeviceEventRepo = DeviceEventRepo()
    private val startPosition = ui.position.pCenterDp
    private val startHitBoxCoordinates = BoxCoordinates.with(ui.position.pCenter, ui.userSpaceShip.hitBox.canvasSize)
    val displaySize = ui.sizes.displaySize
    val displaySizeDp = ui.sizes.displayDpDeltaBox
    private val shipCenterDeltaDp = ui.sizes.shipBoxCenterDp

    private val frameInterval = 2L
//    private val gravityRepo = GravityRepo(context, frameInterval)
    private val gravityRepo = GravityRepo(getApplication(), frameInterval)
    //todo : shoot speed uses maxSpeed, might be better to use shootSpeed = 2/3 maxSpeed
    private val maxSpeed = (displaySizeDp.width.value * 0.002F).dp
    private val halfMaxSpeedF = maxSpeed.value / 2F
    private val minSpeed = (maxSpeed.value * 0.07F).dp

    private var speedF = 0F
    private var delta = Offset.Zero

    private val _shipPosition = MutableStateFlow(startPosition)
    val shipPosition: StateFlow<DpOffset> = _shipPosition.asStateFlow()
    private val userHitBoxDp = ui.userSpaceShip.hitBox.boxDp
    private val userHitMarge = ui.userSpaceShip.hitBox.boxDpOffset.x
    private val _shipHitBox = MutableStateFlow(startPosition.xyPlus(userHitMarge))
    val shipHitBox: StateFlow<DpOffset> = _shipHitBox.asStateFlow()
    private val _shootList = MutableStateFlow<List<Shoot>>(emptyList())
    val shootList: StateFlow<List<Shoot>> = _shootList.asStateFlow()
    private val _laserList = MutableStateFlow<List<Shoot>>(emptyList())
    val laserList: StateFlow<List<Shoot>> = _laserList.asStateFlow()

    fun getShipTopCenter(): DpOffset = DpOffset(x = _shipPosition.value.x + shipCenterDeltaDp, y = _shipPosition.value.y)

    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()

    private val xBackgroundMarge = ui.backgroundUI.matrix.getVerticalMargeDp()
    private val yBackgroundMarge = ui.backgroundUI.matrix.getHorizontalMargeDp()
    private val _backgroundDpOffset = MutableStateFlow(DpOffset.Zero)
    val backgroundDpOffset: StateFlow<DpOffset> = _backgroundDpOffset.asStateFlow()

    private val _speedMagnitude = MutableStateFlow(SpeedMagnitude.Slow)
    val speedMagnitude: StateFlow<SpeedMagnitude> = _speedMagnitude.asStateFlow()
    fun upDateSpeedMagnitude() {
        if (speedF > halfMaxSpeedF) _speedMagnitude.value = SpeedMagnitude.Fast
        else _speedMagnitude.value = SpeedMagnitude.Slow
    }

    init {
        startEngine()
    }

    lateinit var projectileListeningAction : Job

    private fun startEngine() = viewModelScope.launch(Dispatchers.IO) {
        Log.i(TAG, "startEngine: ")
        projectileListeningAction = async { startListeningToProjectiles() }
        val mo = async { startMotions() }
//        val lasers = async { handleLasers() }
    }

    // Refresh Rate of Updates based on the sensor ship position flow refresh rate
    private suspend fun startMotions() = gravityRepo.averageXYZ.collect { _xyz ->
        updateShoots()
        updateMotion(_xyz)
        updateSpeed(_xyz)
        updateDeltaMoves(_xyz)
        upDateSpeedMagnitude()
        updateShipPosition()
        updateShipPositionRatio()
        updateShipHitBox()
    }

    private fun updateMotion(xyz: XYZ) { _motion.update { xyz.toMotion() } }
    private fun updateSpeed(xyz: XYZ) { xyz.getMotionSpeed(gravityRepo.maxZ) }
    private fun updateDeltaMoves(xyz: XYZ) { xyz.updateDelaOffset() }
    val wifiRepo = DeviceWifiRepo()
    private fun updateShipPosition() {
        val newPCenter = shipPosition.value.calculateNewDpOffset()
//        if (Device.data.name != "POCO")
        _shipPosition.update { newPCenter inBoundsOf displaySizeDp }
    }
    private fun updateShipPositionRatio() {
        val xRatio: Float = ((_shipPosition.value.x / displaySizeDp.width) - 0.5F) * -1F//* 2F
        val yRatio: Float = ((_shipPosition.value.y / displaySizeDp.height) - 0.5F) * -1F //* -2F
        _backgroundDpOffset.value = DpOffset( (xBackgroundMarge.value * xRatio).dp, (yBackgroundMarge.value * yRatio).dp)
    }
//    private fun updateShipHitBox() { _shipHitBox.update { shipPosition.value.xyPlus(userHitMarge.value.toDp().value.dp) } }
    private fun updateShipHitBox() { _shipHitBox.update { shipPosition.value.xyPlus(userHitMarge) } }
//    private suspend fun startListeningToProjectiles(): Nothing = Device.event.projectileFlow.collect {
    private suspend fun startListeningToProjectiles() = Device.event.projectileFlow.collectIndexed { index, shoot ->
//    Log.i(TAG, "startListeningToProjectiles: damage ${it.damage}")
//    if (it != Shoot.UNDEFINED) addShoot(it)
//        Log.i(TAG, "startListeningToProjectiles: damage ${shoot.damage}")
//        Log.i(TAG, "startListeningToProjectiles: index $index")
        if (shoot != Shoot.UNDEFINED) addShoot(shoot)

    }
    suspend private fun updateShoots() {
        var newList: List<Shoot> = _shootList.value.moveAndRemoveShoots()
        val hits = newList.checkHitBox()

        newList = newList.filterNot { hits.contains(it) }
//        _shootList.value = newList
        _shootList.emit(newList)
    }
    private suspend fun List<Shoot>.moveAndRemoveShoots(): List <Shoot> = this
        .map { it.getShootNextDpOffset() }
        .filter {
            if (it.offsetDp touchTopScreen displaySizeDp) {
                val invertedShoot = it.getShootPrecedentDpOffset().prepareShootToSendAway()
                eventRepo.sendProjectile(invertedShoot)
            }
            it.offsetDp isInBoundsOf displaySizeDp
        }


    private fun XYZ.getMotionSpeed(maxZ: Float) {
        val maxVector = maxZ * 0.78F
        speedF = when(this.z.absoluteValue) {
            in maxVector..maxZ -> { ((maxZ - this.z.absoluteValue) / (maxZ - maxVector)) * maxSpeed.value }
            in maxZ..42F -> { minSpeed.value }
            else -> { maxSpeed.value }
        }
        speedF = if (speedF < minSpeed.value) minSpeed.value else speedF
    }

    private fun XYZ.updateDelaOffset() {
        delta = Offset(
            x = (this.y.absoluteValue / (this.x.absoluteValue + this.y.absoluteValue)) * speedF,
            y = (this.x.absoluteValue / (this.x.absoluteValue + this.y.absoluteValue)) * speedF
        )
    }

    private fun XYZ.toMotion(): Motions {
        return if (this.x < 0) {
            if (this.y < 0) {
                if (this.x.absoluteValue > this.y.absoluteValue) Motions.UpLeftNorth
                else Motions.UpLeftSouth
            } else {
                if (this.x.absoluteValue > this.y.absoluteValue) Motions.UpRightNorth
                else Motions.UpRightSouth
            }
        } else {
            if (this.y < 0) {
                if (this.x.absoluteValue > this.y.absoluteValue) Motions.DownLeftSouth
                else Motions.DownLeftNorth
            } else {
                if (this.x.absoluteValue > this.y.absoluteValue) Motions.DownRightSouth
                else Motions.DownRightNorth
            }
        }
    }

    private fun DpOffset.calculateNewDpOffset(): DpOffset = DpOffset(
        x = when {
            motion.value.isRight() -> { this.x add delta.x }
            motion.value.isLeft() -> { this.x subtract delta.x}
            else -> this.x.value.dp
        },
        y = when {
            motion.value.isUp() -> { this.y subtract  delta.y }
            motion.value.isDown() -> { this.y add delta.y }
            else -> this.y.value.dp
        }
    )

//    private suspend fun addShoot(shoot: Shoot) = viewModelScope.launch(Dispatchers.IO) {
    private suspend fun addShoot(shoot: Shoot) = viewModelScope.launch(Dispatchers.Main) {
        Log.i(TAG, "addShoot: ")
        //todo : create a function for this or even place it in the munition class
        if (shoot.type == ShipType.Lasery) {
            if ( shoot.from == MunitionsType.UserProjectile
                && shoot.laserOnOpponentScreen.isNotZeroZero()) {
                val laser = shoot.prepareShootToSendAway()
                eventRepo.sendProjectile(laser)
            }
            viewModelScope.launch(Dispatchers.IO) {
                val laserTime = async {
                    handleLaserTime(shoot)
                }
            }
        } else _shootList.emit(shootList.value.add(shoot))
        Log.i(TAG, "addShoot: shootListSize ${_shootList.value.size}")
    }

    private suspend fun handleLaserTime(shoot: Shoot) {
        _laserList.emit(laserList.value.add(shoot))
        Log.i(TAG, "handleLaserTime: particular behavior = ${shoot.particularBehavior}")
        val timer = if (shoot.particularBehavior == 1) SpaceShipLasery.NORMAL_LASER_LIFE
        else SpaceShipLasery.NORMAL_LASER_LIFE * shoot.particularBehavior
        val delay = if (shoot.particularBehavior == 1) SpaceShipLasery.NORMAL_LASER_LIFE
        else 10L
        var repeat: Int = (timer / delay).toInt()
        Log.i(TAG, "handleLaserTime: repeat = $repeat")
        do {
            repeat--
            if (shoot.from == MunitionsType.EnemiesProjectile && shoot.isLaserHittingShip())
                Device.event.hitStateFlow.emit(shoot)
            delay(delay)
//            Log.i(TAG, "handleLaserTime: repeat = $repeat")
        } while (repeat > 0)
//        delay(timer )
        _laserList.emit(laserList.value.remove(shoot))
    }

    private fun Shoot.isLaserHittingShip(): Boolean {
        var ret = false
        val laserStart = this.laserOnOpponentScreen.first.toDpOffset()
        val laserEnd = this.laserOnOpponentScreen.second.toDpOffset()

        val topLeftCorner: DpOffset = shipHitBox.value
        val topRightCorner: DpOffset = topLeftCorner.xPlus(userHitBoxDp.width)

        val hitBoxXRange: ClosedRange<Dp> = (topLeftCorner.x)..(topRightCorner.x)
        val hitBoxYRange: ClosedRange<Dp> = (topLeftCorner.y)..(topLeftCorner.y + userHitBoxDp.height)

        val laserXTopLeft = getXForY(laserStart, laserEnd, topLeftCorner.y)
        val laserYLeftTop = getYForX(laserStart, laserEnd, topLeftCorner.x)
        val laserYRightTop = getYForX(laserStart, laserEnd, topRightCorner.x)

        if (laserXTopLeft in hitBoxXRange) ret = true
        if (laserYLeftTop in hitBoxYRange) ret = true
        if (laserYRightTop in hitBoxYRange) ret = true

        return ret
    }
    //todo : make it better for square projectile
    private fun Shoot.isProjectileHittingShip(): Boolean {
        var ret = false
        val topLeftCorner: DpOffset = shipHitBox.value
        val shipTopY: Dp = topLeftCorner.y

        val projectileLeftX: Dp = this.offsetDp.x
        val projectileRightX: Dp = projectileLeftX + this.boxDp
        val projectileBottomY: Dp = this.offsetDp.y + this.boxDp

        if (projectileBottomY > shipTopY ) {
            val shipXRange: ClosedRange<Dp> = (topLeftCorner.x) .. (topLeftCorner.x + userHitBoxDp.width)
            if ( projectileLeftX in shipXRange ||  projectileRightX in shipXRange ) {
                ret = true
            }
        }
        return ret
    }
    private suspend fun List<Shoot>.checkHitBox(): List<Shoot> = this
        .filter { projectile -> projectile.from == MunitionsType.EnemiesProjectile }
//        .filter { it.offsetDp isInsideOf shipHitBox.value }
        .filter { it.isProjectileHittingShip() }
        .map {
            Log.e(TAG, "checkHitBox: HIT")
            Device.event.hitStateFlow.emit(it)
            it
        }

    fun getShootVector(): DpOffset {
        val x = when {
            motion.value.isRight() -> delta.x
            motion.value.isLeft() -> delta.x * -1F
            else -> 0F
        }
        val y = when {
            motion.value.isUp() -> maxSpeed.value + (delta.y / 2F)
            motion.value.isDown() -> maxSpeed.value - (delta.y / 2F)
            else -> maxSpeed.value
        }
        return DpOffset(x.dp, (1.5 * y).dp)
    }

    fun getTargetAngle(motion: Motions, speed: SpeedMagnitude): Float = when {
        motion.isUp() -> {
            when {
                motion.isLeftNorth() && speed.isSlow() -> -1F
                motion.isLeftNorth() && speed.isFast() -> -3F
                motion.isRightNorth() && speed.isSlow() -> 1F
                motion.isRightNorth() && speed.isFast() -> 3F
                motion.isLeftSouth() && speed.isSlow() -> -4F
                motion.isLeftSouth() && speed.isFast() -> -8F
                motion.isRightSouth() && speed.isSlow()-> 4F
                motion.isRightSouth() && speed.isFast()-> 8F
                else -> 0F
            }
        }
        motion.isDown() -> {
            when {
                motion.isLeftSouth() && speed.isSlow()-> -1F
                motion.isLeftSouth() && speed.isFast()-> -3F
                motion.isRightSouth() && speed.isSlow()-> 1F
                motion.isRightSouth() && speed.isFast()-> 3F
                motion.isLeftNorth() && speed.isSlow()-> -4F
                motion.isLeftNorth() && speed.isFast()-> -8F
                motion.isRightNorth() && speed.isSlow()-> 4F
                motion.isRightNorth() && speed.isFast()-> 8F
                else -> 0F
            }
        }
        else -> 0F
    }

    fun clear() { onCleared() }
    override fun onCleared() {
        gravityRepo.stop()
        projectileListeningAction.cancel()
        super.onCleared()
    }
}