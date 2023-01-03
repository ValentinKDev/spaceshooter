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
import com.mobilegame.spaceshooter.logic.model.sensor.XYZ
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.extensions.List.numberOf
import com.mobilegame.spaceshooter.utils.extensions.List.smooth
import com.mobilegame.spaceshooter.utils.extensions.printTo
import com.mobilegame.spaceshooter.utils.extensions.toMotionLR
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

//    private val maxSpeed = 0.7.dp
//    private val frameInterval = 10L

    var gravity = 9.82F
//    private var gravity = accelerometerVMkkkkkkk.max

    private var noisyMaxSpeedTest = 0.15.dp
    private var noisyMaxSpeed = 0.22.dp
    private val maxSpeed = 0.32.dp
    private var frameInterval = 1L

    private val maxAcceleration = 1F
    private var deltaX = 0.dp
    private var deltaY = 0.dp
    private val speedArraySize = 3
    private val speedArrayLastIndex = speedArraySize - 1
    private var speedArray: MutableList<Float> = speedArraySize numberOf 0F
    private var xyz = XYZ.ZERO
    private fun upDateXYZ() {
        xyz = accelerometerVM.averagePosition
    }
    private fun resetSpeeds() {
        speedArray = speedArraySize numberOf 0F
    }
    private fun shiftSpeeds(lastSpeed: Float) {
        for (i in speedArrayLastIndex  downTo 0) {
            if (i == 0) speedArray[i] = lastSpeed
            else speedArray[i] = speedArray[i - 1]
        }
    }
    private var averageSpeed = 0F
    private fun setAverageSpeed() {
        averageSpeed = speedArray.sum()
    }

    private fun handleNewSpeed(speedF: Float) {
        shiftSpeeds(speedF)
        speedArray.smooth(100)
        setAverageSpeed()
    }

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
        val maxVector = 9.55F
        val noiseVector1 = 9.81F
        val noiseVector2 = 9.80F
        val noiseVector3 = 9.79F
        val noiseVector4 = 9.78F
        var enable2DMotions = true
        val speedMinF = 0.055F

        var speedF: Float = when(xyz.z.absoluteValue) {
            in gravity..42F -> {
                frameInterval = 1L
                enable2DMotions = false
                speedMinF
            }
            in noiseVector2..gravity -> {
//                eLog("MotionVM::getMotionSpeed", "...")
                enable2DMotions = false
                frameInterval = 1L
                val speed = ((gravity - xyz.z.absoluteValue) / (gravity - maxVector)) * noisyMaxSpeedTest.value
                if (speed < speedMinF) speedMinF else speed
            }
            in noiseVector2..noiseVector1 -> {
                enable2DMotions = true
                frameInterval = 1L
                val speed = ((gravity - xyz.z.absoluteValue) / (gravity - maxVector)) * noisyMaxSpeed.value
                if (speed < speedMinF) speedMinF else speed
            }
            in noiseVector3..noiseVector2 -> {
                enable2DMotions = true
                frameInterval = 1L
                val speed = ((gravity - xyz.z.absoluteValue) / (gravity - maxVector)) * noisyMaxSpeed.value
                if (speed < speedMinF) speedMinF else speed
            }
            in maxVector..noiseVector3 -> {
//                vLog("MotionVM::getMotionSpeed", "...")
                frameInterval = 1L
                enable2DMotions = true
                ((gravity - xyz.z.absoluteValue) / (gravity - maxVector)) * maxSpeed.value
            }
            else -> maxSpeed.value
        }
        if (speedF > maxSpeed.value) {
            eLog("MotionsVM::getMotionSpeed", "speed ${speedF.printTo(4)} xyz(${xyz.x.printTo(4)}, ${xyz.x.printTo(4)}, ${xyz.x.printTo(4)})")
            speedF = maxSpeed.value
        }

        if (enable2DMotions) {
            deltaX = ((xyz.y.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * averageSpeed).dp
            deltaY = ((xyz.x.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * averageSpeed).dp
        } else {
            deltaX = ((xyz.y.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * averageSpeed).dp
            deltaY = ((xyz.x.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * averageSpeed).dp
//            if (xyz.x > xyz.y) {
//                deltaX = (0.8F * averageSpeed).dp
//                deltaY = (0.2F * averageSpeed).dp
//            } else {
//                deltaX = (0.2F * averageSpeed).dp
//                deltaY = (0.8F * averageSpeed).dp
//            }
        }

        handleNewSpeed( speedF )
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
//            Motions.DownRight, Motions.UpRight-> {
            Motions.DownRightNorth, Motions.DownRightSouth,
            Motions.UpRightNorth, Motions.UpRightSouth-> {
                (oldPlacementDp.x.value + deltaX.value).dp
            }
//            Motions.DownLeft, Motions.UpLeft -> {
            Motions.DownLeftNorth, Motions.DownLeftSouth,
            Motions.UpLeftNorth, Motions.UpLeftSouth -> {
                (oldPlacementDp.x.value - deltaX.value).dp
            }
            Motions.None -> oldPlacementDp.x.value.dp
        }
        val newY: Dp = when (motion.value) {
//            Motions.UpLeft, Motions.UpRight-> {
            Motions.UpLeftNorth, Motions.UpLeftSouth,
            Motions.UpRightNorth, Motions.UpRightSouth-> {
                (oldPlacementDp.y.value - deltaY.value).dp
            }
//            Motions.DownLeft, Motions.DownRight -> {
            Motions.DownLeftNorth, Motions.DownLeftSouth,
            Motions.DownRightNorth, Motions.DownRightSouth -> {
                (oldPlacementDp.y.value + deltaY.value).dp
            }
            Motions.None -> oldPlacementDp.x.value.dp
        }
        return DpOffset(newX, newY)
    }

    private fun getMotion(): Motions {
        return if (xyz.x < 0) {
            if (xyz.y < 0) {
//                Motions.UpLeft
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.UpLeftNorth
                else Motions.UpLeftSouth
            } else {
//                Motions.UpRight
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.UpRightNorth
                else Motions.UpRightSouth
            }
        } else {
            if (xyz.y < 0) {
//                Motions.DownLeft
                if (xyz.x.absoluteValue > xyz.y.absoluteValue) Motions.DownLeftSouth
                else Motions.DownLeftNorth
            } else {
//                Motions.DownRight
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