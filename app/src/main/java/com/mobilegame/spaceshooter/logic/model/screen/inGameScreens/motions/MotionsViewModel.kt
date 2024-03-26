package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.content.Context
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.data.sensor.XYZ
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.repository.sensor.GravityRepo
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.BoxCoordinates
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.MunitionsType
import com.mobilegame.spaceshooter.utils.extensions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class MotionsViewModel(
    context: Context,
    ui: SpaceWarGameScreenUI,
): ViewModel() {
    private val deviceEvent = Device.event
    val TAG = "MotionsViewModel"
    private val eventRepo: DeviceEventRepo = DeviceEventRepo()
    private val startPosition = ui.position.pCenterDp
    private val startHitBoxCoordinates = BoxCoordinates.with(ui.position.pCenter, ui.userSpaceShip.hitBox.size)
    private val displaySizeDp = ui.sizes.displayDpDeltaBox
    private val shipCenterDeltaDp = ui.sizes.shipBoxCenterDp

    private val frameInterval = 2L
    private val gravityRepo = GravityRepo(context, frameInterval)
    //todo : shoot speed uses maxSpeed, might be better to use shootSpeed = 2/3 maxSpeed
    private val maxSpeed = (displaySizeDp.width.value * 0.002F).dp
    private val halfMaxSpeedF = maxSpeed.value / 2F
    private val minSpeed = (maxSpeed.value * 0.07F).dp

    private var speedF = 0F
    private var delta = Offset.Zero

    private val _shipPosition = MutableStateFlow(startPosition)
    val shipPosition: StateFlow<DpOffset> = _shipPosition.asStateFlow()
    private val _shipHitBox = MutableStateFlow(startHitBoxCoordinates)
    val shipHitBox: StateFlow<BoxCoordinates> = _shipHitBox.asStateFlow()
    private val _shootList = MutableStateFlow<List<Shoot>>(emptyList())
    val shootList: StateFlow<List<Shoot>> = _shootList.asStateFlow()
//    val shootList
    val _hitStateFlow = MutableSharedFlow<Shoot>()

    private val _gameOnPause = MutableStateFlow(false)
    val gameOnPause: StateFlow<Boolean> = _gameOnPause.asStateFlow()

    private val userHitBox = ui.userSpaceShip.hitBox
    fun getShipTopCenter(): DpOffset = DpOffset(x = _shipPosition.value.x + shipCenterDeltaDp, y = _shipPosition.value.y)

    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()
    fun changeMotionTo(motion: Motions) {
        _motion.value = motion
    }

    private val _speedMagnitude = MutableStateFlow(SpeedMagnitude.Slow)
    val speedMagnitude: StateFlow<SpeedMagnitude> = _speedMagnitude.asStateFlow()
    fun upDateSpeedMagnitude() {
        if (speedF > halfMaxSpeedF) _speedMagnitude.value = SpeedMagnitude.Fast
        else _speedMagnitude.value = SpeedMagnitude.Slow
    }

    init { startEngine() }

    private fun startEngine() = viewModelScope.launch(Dispatchers.IO) {
        Log.i(TAG, "startEngine: ")
        val ev = async { startListeningToProjectiles() }
        val mo = async { startMotions() }
    }

    // Refresh Rate of Updates based on the sensor ship position flow refresh rate
    private suspend fun startMotions() = gravityRepo.averageXYZ.collect { _xyz ->
        updateShoots()
        updateMotion(_xyz)
        updateSpeed(_xyz)
        updateDeltaMoves(_xyz)
        upDateSpeedMagnitude()
        updateShipPosition()
        updateShipHitBox()
    }

    private fun updateMotion(xyz: XYZ) { _motion.update { xyz.toMotion() } }
    private fun updateSpeed(xyz: XYZ) { xyz.getMotionSpeed(gravityRepo.maxZ) }
    private fun updateDeltaMoves(xyz: XYZ) { xyz.updateDelaOffset() }
    val wifiRepo = DeviceWifiRepo()
    private fun updateShipPosition() {
//        if (wifiRepo.isDeviceServer()) {
            val newPCenter = shipPosition.value.calculateNewDpOffset()
            _shipPosition.update { newPCenter inBoundsOf displaySizeDp }
//        }
    }
    private fun updateShipHitBox() { _shipHitBox.update { it.getUpdatedBoxCoordinates(shipPosition.value) } }
    private suspend fun startListeningToProjectiles(): Nothing = Device.event.projectileFlow.collect { addShoot(it) }
    suspend private fun updateShoots() {
        var newList: List<Shoot> = _shootList.value.moveAndRemoveShoots()
        val hits = newList.checkHitBox()

        newList = newList.filterNot { hits.contains(it) }
//        _shootList.value = newList
        _shootList.emit(newList)
        updateUserProjectiles()
    }
    private suspend fun List<Shoot>.checkHitBox(): List<Shoot> = this
        .filter { projectile -> projectile.from == MunitionsType.EnemiesProjectile }
        .filter { it.offsetDp isInsideOf shipHitBox.value }
        .map {
            Log.e(TAG, "checkHitBox: HIT")
//            _hitStateFlow.update { it = 50F }
//            _hitStateFlow.emit(ui.shipType.info.damage)
            _hitStateFlow.emit(it)
            it
        }
    private suspend fun List<Shoot>.moveAndRemoveShoots(): List <Shoot> = this
        .map { it.getShootNextDpOffset() }
        .filter {
            if (it.offsetDp touchTopScreen displaySizeDp) {
                Log.i(TAG, "updateUserProjectiles: TOUCH TOP SCREEN")
                val invertedShoot = it.getShootPrecedentDpOffset().prepareShootToSendAway()
//                eventRepo.sendProjectile(invertedShoot)
            }
            it.offsetDp isInBoundsOf displaySizeDp
        }

    private fun <Shoot> List<Shoot>.checkHitBox() {
//        this.filter { projectile ->
//            projectile.from
//        }
    }
    private suspend fun updateEnemiesProjectiles() {
    }

    fun updateUserProjectiles() {
    }

    private fun XYZ.getMotionSpeed(maxZ: Float) {
//        val maxVector = maxZ * 0.78F
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
            else -> this.x.value.dp
        }
    )

    suspend fun addShoot(shoot: Shoot) {
//        Log.e(TAG, "addShoot: Shoot from ShipType ${shoot.type.name}", )
//        Log.i(TAG, "addShoot: test == circle ${shoot.type == ShipType.Circle}")
//        Log.i(TAG, "addShoot: test == square ${shoot.type == ShipType.Square}")
        //todo: simplify extension add
//        Log.i(TAG, "addShoot: shootlist size before ${_shootList.value.size}")
//        _shootList.value = _shootList.value.add(shoot)
        _shootList.emit(shootList.value.add(shoot))
//        Log.i(TAG, "addShoot: shootlist size after ${_shootList.value.size}")
//        _shootList.value.forEach {
//            Log.i(TAG, "addShoot: list shoot ${it.type.name} ${it.from.name} == FromUser ${it.from == MunitionsType.UserProjectile}")
//        }
    }

    fun getShootVector(): Size {
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
        return Size(x, y)
    }

    private fun updateShootsListPositions(shootList: List<Shoot>) {
        for (i in shootList.indices) {
            if (shootList[i].offsetDp isInBoundsOf displaySizeDp)
                shootList[i].updateDpOffset()
        }
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

    override fun onCleared() {
        gravityRepo.stop()
        super.onCleared()
    }
}