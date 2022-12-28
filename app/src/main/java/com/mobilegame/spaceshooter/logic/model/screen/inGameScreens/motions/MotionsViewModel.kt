package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.sensor.AccelerometerSensor
import com.mobilegame.spaceshooter.logic.model.sensor.AccelerometerViewModel
import com.mobilegame.spaceshooter.logic.model.sensor.notZero
import com.mobilegame.spaceshooter.utils.extensions.List.numberOf
import com.mobilegame.spaceshooter.utils.extensions.List.smooth
import com.mobilegame.spaceshooter.utils.extensions.not
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.absoluteValue

class MotionsViewModel(
    context: Context,
    startPosition: DpOffset,
    private val displaySizeDp: DpSize,
): ViewModel() {
    private val accelerometerVM = AccelerometerViewModel(AccelerometerSensor(context))
//    private val maxSpeed = 0.2.dp
//private val maxSpeed = 0.6.dp
    private val maxSpeed = 0.7.dp
    private val gravity = 9.81F
    private val maxAcceleration = 1F
    private var deltaX = 0.dp
    private var deltaY = 0.dp
    private val smooth = 4
    private val lastIndex = smooth - 1
    private var speedsF: MutableList<Float> = smooth numberOf 0F
    private fun resetSpeeds() {
        speedsF = smooth numberOf 0F
    }
    private fun shiftSpeeds(lastSpeed: Float) {
        for (i in lastIndex  downTo 0) {
            if (i == 0) speedsF[i] = lastSpeed
            else speedsF[i] = speedsF[i - 1]
        }
    }

    private var averageSpeed = 0F
    private fun setAverageSpeed() {
        averageSpeed = speedsF.sum() / smooth
    }

    private fun handleNewSpeed(speedF: Float) {
        shiftSpeeds(speedF)
        speedsF.smooth(10)
        setAverageSpeed()
    }

    private val _shipPosition = MutableStateFlow(startPosition)
    val shipPosition: StateFlow<DpOffset> = _shipPosition.asStateFlow()
    fun moveShipTo(newPCenter: DpOffset) { _shipPosition.value = newPCenter inBoundsOf displaySizeDp }

    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()
    fun changeMotionTo(motion: Motions) { _motion.value = motion }

    init { startFrameLoop() }

    fun updateFrame() {
        val newMotion = getMotion()
        upDateSpeed ( newMotion )
        changeMotionTo( newMotion )
        getMotionSpeed()
        val newShipPosition = getUpdatedShipPosition()
        moveShipTo( newShipPosition )
    }

    private fun upDateSpeed(newMotion: Motions) {
        if ( newMotion not motion.value) {
            resetSpeeds()
        }
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
            delay(1)
            if (accelerometerVM.averagePosition.notZero()) updateFrame()
        }
    }

    private fun getUpdatedShipPosition(): DpOffset {
        val oldPlacementDp = shipPosition.value
        val newX: Dp = when (motion.value) {
            Motions.DownRight, Motions.UpRight-> {
                (oldPlacementDp.x.value + deltaX.value).dp
            }
            Motions.DownLeft, Motions.UpLeft -> {
                (oldPlacementDp.x.value - deltaX.value).dp
            }
            Motions.None -> oldPlacementDp.x.value.dp
        }
        val newY: Dp = when (motion.value) {
            Motions.UpLeft, Motions.UpRight-> {
                (oldPlacementDp.y.value - deltaY.value).dp
            }
            Motions.DownLeft, Motions.DownRight -> {
                (oldPlacementDp.y.value + deltaY.value).dp
            }
            Motions.None -> oldPlacementDp.x.value.dp
        }
        return DpOffset(newX, newY)
    }

    private fun getMotion(): Motions {
        val xyz = accelerometerVM.averagePosition

        return if (xyz.x < 0) {
            if (xyz.y < 0) {
                Motions.UpLeft
            } else {
                Motions.UpRight
            }
        } else {
            if (xyz.y < 0) {
                Motions.DownLeft
            } else {
                Motions.DownRight
            }
        }
    }

    private fun getMotionSpeed() {
        val xyz = accelerometerVM.averagePosition
        val speedF: Float = if (gravity - xyz.z.absoluteValue >= maxAcceleration) maxSpeed.value else (((gravity - xyz.z.absoluteValue) / maxAcceleration ) * maxSpeed.value)
        handleNewSpeed( speedF )

//        deltaX = ((xyz.y.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * speedF).dp
//        deltaY = ((xyz.x.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * speedF).dp
        deltaX = ((xyz.y.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * averageSpeed).dp
        deltaY = ((xyz.x.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * averageSpeed).dp
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