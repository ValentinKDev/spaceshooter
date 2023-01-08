package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.content.Context
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.sensor.GravitySensor
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.sensor.AccelerometerViewModel
import com.mobilegame.spaceshooter.logic.model.sensor.XYZ
import com.mobilegame.spaceshooter.logic.uiHandler.games.DuelGameScreenUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class MotionsViewModel(
    context: Context,
    ui: DuelGameScreenUI,
): ViewModel() {
    private val startPosition = ui.position.pCenterDp
    private val displaySizeDp = ui.sizes.displayDpDeltaBox
    private val shipCenterDeltaDp = ui.sizes.shipBoxCenterDp

    private val accelerometerVM = AccelerometerViewModel(GravitySensor(context))
    //todo : shoot speed uses maxSpeed, might be better to use shootSpeed = 2/3 maxSpeed
    private val maxSpeed = (0.001F * displaySizeDp.width.value).dp
    private val halfMaxSpeedF = maxSpeed.value / 2F
    private val minSpeed = (maxSpeed.value * 0.07F).dp
    private val frameInterval = 1L

    private var speedF = 0F
    private var deltaX = 0F
    private var deltaY = 0F
    private var xyz = XYZ.ZERO
    private fun upDateXYZ() { xyz = accelerometerVM.averagePosition }

    private val _shipPosition = MutableStateFlow(startPosition)
    val shipPosition: StateFlow<DpOffset> = _shipPosition.asStateFlow()
    fun moveShipTo(newPCenter: DpOffset) { _shipPosition.value = newPCenter inBoundsOf displaySizeDp }
    fun getShipTopCenter(): DpOffset = DpOffset(x = _shipPosition.value.x + shipCenterDeltaDp, y = _shipPosition.value.y)

    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()
    fun changeMotionTo(motion: Motions) { _motion.value = motion }

    private val _speedMagnitude = MutableStateFlow(SpeedMagnitude.Slow)
    val speedMagnitude: StateFlow<SpeedMagnitude> = _speedMagnitude.asStateFlow()
    fun upDateSpeedMagnitude() {
        if (speedF > halfMaxSpeedF) _speedMagnitude.value = SpeedMagnitude.Fast
        else _speedMagnitude.value = SpeedMagnitude.Slow
    }

    init { startFrameLoop() }

    fun updateFrame() {
        upDateXYZ()
        val newMotion = getMotion()
        changeMotionTo( newMotion )
        getMotionSpeed()
        updateShootsMotions()
        upDateSpeedMagnitude()
        val newShipPosition = getUpdatedShipPosition()
        moveShipTo( newShipPosition )
    }

    private fun getMotionSpeed() {
        val maxVector = accelerometerVM.maxZ * 0.78F
        speedF = when(xyz.z.absoluteValue) {
            in maxVector..accelerometerVM.maxZ -> { ((accelerometerVM.maxZ - xyz.z.absoluteValue) / (accelerometerVM.maxZ - maxVector)) * maxSpeed.value }
            in accelerometerVM.maxZ..42F -> { minSpeed.value }
            else -> { maxSpeed.value }
        }
        speedF = if (speedF < minSpeed.value) minSpeed.value else speedF
        deltaX = (xyz.y.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * speedF
        deltaY = (xyz.x.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * speedF
    }

    private var frameLoopState: Boolean = false
    private  fun startFrameLoop() {
        viewModelScope.launch(Dispatchers.Main) {
            frameLoopState = true
            frameLoop()
        }
    }

    private suspend fun frameLoop() {
        while (frameLoopState) {
            delay(frameInterval)
            updateFrame()
        }
    }

    private fun getMotion(): Motions {
        return if (xyz.x < 0) {
            if (xyz.y < 0) {
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.UpLeftNorth
                else Motions.UpLeftSouth
            } else {
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.UpRightNorth
                else Motions.UpRightSouth
            }
        } else {
            if (xyz.y < 0) {
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.DownLeftSouth
                else Motions.DownLeftNorth
            } else {
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.DownRightSouth
                else Motions.DownRightNorth
            }
        }
    }

    private fun getUpdatedShipPosition(): DpOffset {
        return getUpdatedDpOffsetByMotionsType(
            oldPlacementDp = shipPosition.value,
            motion = motion.value,
            dX = deltaX,
            dY = deltaY,
        )
    }

    private fun getUpdatedDpOffsetByMotionsType(
        oldPlacementDp: DpOffset,
        motion: Motions,
        dX: Float,
        dY: Float,
    ): DpOffset {
        val newX: Dp = when {
            motion.isRight() -> { (oldPlacementDp.x.value + dX).dp }
            motion.isLeft() -> { (oldPlacementDp.x.value - dX).dp }
            else -> oldPlacementDp.x.value.dp
        }
        val newY: Dp = when {
            motion.isUp() -> { (oldPlacementDp.y.value - dY).dp }
            motion.isDown() -> { (oldPlacementDp.y.value + dY).dp }
            else -> oldPlacementDp.x.value.dp
        }
        return DpOffset(newX, newY) or oldPlacementDp
    }

    private val _shootList = MutableStateFlow<List<Shoot>>(emptyList())
    val shootList: StateFlow<List<Shoot>> = _shootList.asStateFlow()
    fun addShoot(shoot: Shoot) {
        _shootList.value = _shootList.value.plus(shoot)
    }

    fun getShootVector(): Size {
        val x = when {
            motion.value.isRight() -> deltaX
            motion.value.isLeft() -> deltaX * -1F
            else -> 0F
        }
        val y = when {
            motion.value.isUp() -> maxSpeed.value + (deltaY / 2F)
            motion.value.isDown() -> maxSpeed.value - (deltaY / 2F)
            else -> maxSpeed.value
        }
        return Size(x, y)
    }

    private fun updateShootsListPositions(shootList: List<Shoot>) {
        for (i in shootList.indices) {
            if (shootList[i].offsetDp isInBoundsOf displaySizeDp)
                shootList[i].upDateDpOffset()
        }
    }

    private fun updateShootsMotions() {
        updateShootsListPositions( shootList.value )
        val newShootsList = shootList.value.cloneIfInBounds(displaySizeDp)
        _shootList.value = newShootsList
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
        accelerometerVM.stop()
        super.onCleared()
    }
}