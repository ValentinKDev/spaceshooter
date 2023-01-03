package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.sensor.AccelerometerSensor
import com.mobilegame.spaceshooter.logic.model.sensor.AccelerometerViewModel2
import com.mobilegame.spaceshooter.logic.model.sensor.XYZ
import com.mobilegame.spaceshooter.utils.extensions.or
import com.mobilegame.spaceshooter.utils.extensions.toMotionLR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class MotionsViewModel2(
    context: Context,
    startPosition: DpOffset,
    private val displaySizeDp: DpSize,
): ViewModel() {
    private val accelerometerVM = AccelerometerViewModel2(AccelerometerSensor(context))
    private val maxSpeed = 20.dp
    private val sensitivity = 1f/128
    private var frameInterval = 1L

    private var speedF = 0F
    private var deltaX = 0F
    private var deltaY = 0F
    private var xyz = XYZ.ZERO
    private fun upDateXYZ() { xyz = accelerometerVM.averagePosition }

    private val _shipPosition = MutableStateFlow(startPosition)
    val shipPosition: StateFlow<DpOffset> = _shipPosition.asStateFlow()
    fun moveShipTo(newPCenter: DpOffset) { _shipPosition.value = newPCenter inBoundsOf displaySizeDp }

    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()
    private val _motionLR = MutableStateFlow(MotionLR.None)
    val motionLR: StateFlow<MotionLR> = _motionLR.asStateFlow()
    fun changeMotionTo(motion: Motions) {
        _motion.value = motion
        _motionLR.value = motion.toMotionLR()
    }

    init { startFrameLoop() }

    fun updateFrame() {
        upDateXYZ()
        val newMotion = getMotion()
        changeMotionTo( newMotion )
        getMotionSpeed()
        val newShipPosition = getUpdatedShipPosition()
        moveShipTo( newShipPosition )
    }

    private fun getMotionSpeed() {
        val maxVector = accelerometerVM.maxZ * 0.8F
        val speedMinF = 0.065F

        speedF = when(xyz.z.absoluteValue) {
            in accelerometerVM.maxZ..42F -> {
                frameInterval = 1L
                speedMinF
            }
            in maxVector..accelerometerVM.maxZ -> {
                frameInterval = 1L
                ((accelerometerVM.maxZ - xyz.z.absoluteValue) / (accelerometerVM.maxZ - maxVector)) * maxSpeed.value * sensitivity
            }
            else -> {
                maxSpeed.value
            }
        }
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

    private fun getUpdatedShipPosition(): DpOffset {
        val oldPlacementDp = shipPosition.value
        val newX: Dp = when (motion.value) {
            Motions.DownRightNorth, Motions.DownRightSouth,
            Motions.UpRightNorth, Motions.UpRightSouth-> {
                (oldPlacementDp.x.value + deltaX).dp
            }
            Motions.DownLeftNorth, Motions.DownLeftSouth,
            Motions.UpLeftNorth, Motions.UpLeftSouth -> {
                (oldPlacementDp.x.value - deltaX).dp
            }
            Motions.None -> oldPlacementDp.x.value.dp
        }
        val newY: Dp = when (motion.value) {
            Motions.UpLeftNorth, Motions.UpLeftSouth,
            Motions.UpRightNorth, Motions.UpRightSouth-> {
                (oldPlacementDp.y.value - deltaY).dp
            }
            Motions.DownLeftNorth, Motions.DownLeftSouth,
            Motions.DownRightNorth, Motions.DownRightSouth -> {
                (oldPlacementDp.y.value + deltaY).dp
            }
            Motions.None -> oldPlacementDp.x.value.dp
        }
        return DpOffset(newX, newY) or oldPlacementDp
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


    private infix fun DpOffset.inBoundsOf(sizeDp: DpSize): DpOffset {
        return when {
            this.x < 0.dp -> DpOffset(0.dp, this.y)
            this.y < 0.dp -> DpOffset(this.x, 0.dp)
            this.x > sizeDp.width -> DpOffset(sizeDp.width, this.y)
            this.y > sizeDp.height -> DpOffset(this.x, sizeDp.height)
            else -> this
        }
    }

    override fun onCleared() {
        accelerometerVM.stop()
        super.onCleared()
    }
}