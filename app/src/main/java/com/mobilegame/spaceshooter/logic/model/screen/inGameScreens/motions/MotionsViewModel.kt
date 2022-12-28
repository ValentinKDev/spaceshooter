package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.content.Context
import android.util.Log
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.sensor.AccelerometerSensor
import com.mobilegame.spaceshooter.logic.model.data.sensor.AccelerometerListener
import com.mobilegame.spaceshooter.logic.model.data.sensor.notZero
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.Device
import com.mobilegame.spaceshooter.logic.model.sensor.AccelerometerViewModel
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.prettyPrint
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.extensions.printTo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.absoluteValue

class MotionsViewModel(
  context: Context,
//    private val context: Context,
    startPosition: DpOffset,
    private val displaySizeDp: DpSize,
//  val accelerometerVM : AccelerometerViewModel
): ViewModel() {
//class MotionsViewModel(private val shipVM: SpaceShipViewModel, context: Context): ViewModel() {
//class MotionsViewModel(private val shipVM: SpaceShipViewModel, application: Application): AndroidViewModel(application) {
    private val maxSpeed = 2.dp
    private val gravity = 9.81F
    private val maxAcceleration = 1F
    private var deltaX = 0.dp
    private var deltaY = 0.dp

    private val _shipPosition = MutableStateFlow(startPosition)
    val shipPosition: StateFlow<DpOffset> = _shipPosition.asStateFlow()
    fun moveShipTo(newPCenter: DpOffset) {
//        _shipPosition.value = newPCenter inBoundsOf displaySizeDp
        _shipPosition.value = newPCenter
    }

    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()
    fun motionChange(motion: Motions) {
        _motion.value = motion

    }

    val accelerometerVM = AccelerometerViewModel(AccelerometerSensor(context))

    init {
        eLog("MotionsVM::init", "start $startPosition")
        eLog("MotionsVM::init", "ship ${shipPosition.value}")
//        AccelerometerListener.initializeSensor(AccelerometerSensor(context))
        viewModelScope.launch(Dispatchers.Main) {
            frameLoop = true
            startFrameLoop()
        }
    }

    fun updateFrame() {
        val newMotion = getMotion()
        motionChange(newMotion)
        getMotionSpeed()
        val newShipPosition = getUpdatedShipPosition()
        moveShipTo( newShipPosition )
//        shipVM.moveShipTo( newShipPosition )

//        vLog("MotionsVM::updatePlayerPosition", "Motion ${motion.value.name}")
//        vLog("MotionsVM::updatePlayerPosition", "position $newShipPosition")
    }

//    private val frameLoop = viewModelScope.async(Dispatchers.IO) { startFrameLoop() }
//    fun stopFrameLoop() { frameLoop.cancel() }
    private var frameLoop: Boolean = false

    private suspend fun startFrameLoop() {
        eLog("MotionsVM::frameLoop", "start")
//        if (AccelerometerListener.isNotGoingOn())
//            AccelerometerListener.initializeSensor(AccelerometerSensor(context))
//        while (AccelerometerListener.isGoingOn()) {
        while (frameLoop) {
            delay(10)
            if (accelerometerVM.devicePosition.notZero()) updateFrame()
        }
        eLog("MotionsVM::frameLoop", "stop")
    }


    private fun getUpdatedShipPosition(): DpOffset {
//        val oldPlacement = shipVM.pCenterDp.value
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
        val xyz = accelerometerVM.devicePosition

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
        val xyz = accelerometerVM.devicePosition
        val speed = if (gravity - xyz.z.absoluteValue >= maxAcceleration) maxSpeed else (((gravity - xyz.z.absoluteValue) / maxAcceleration ) * maxSpeed.value).dp

        deltaX = ((xyz.y.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * speed.value).dp
        deltaY = ((xyz.x.absoluteValue / (xyz.x.absoluteValue + xyz.y.absoluteValue)) * speed.value).dp
    }

    private infix fun Motions.not(motions: Motions): Boolean = this != motions

    private infix fun DpOffset.inBoundsOf(sizeDp: DpSize): DpOffset {
//        return if (this.x < 0.dp)
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

//class FrameHandler() {
//    init {
//
//        viewModelScope.launch() {
//            frameLoop()
//        }
//    }
//    suspend fun truc() {
//        coroutineScope {
//
//        }
//    }
//
//    private suspend fun frameLoop() {
//
//        eLog("MotionsVM::frameLoop", "start")
//        while (motion.value not Motions.None && AccelerometerListener.isGoingOn()) {
////            delay(10)
//            delay(100)
//            updateFrame()
//        }
//        eLog("MotionsVM::frameLoop", "stop")
//    }
//}