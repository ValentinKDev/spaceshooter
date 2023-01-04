package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions

import android.content.Context
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.sensor.GravitySensor
import com.mobilegame.spaceshooter.logic.model.sensor.AccelerometerViewModel
import com.mobilegame.spaceshooter.logic.model.sensor.XYZ
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
    startPosition: DpOffset,
    private val displaySizeDp: DpSize,
): ViewModel() {
    private val accelerometerVM = AccelerometerViewModel(GravitySensor(context))
    private val sensitivity = 1f/64F
    private val maxSpeed = 17.dp * sensitivity
    private var minSpeed = (maxSpeed.value * 0.15F).dp
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
    fun changeMotionTo(motion: Motions) { _motion.value = motion }

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
        val maxVector = accelerometerVM.maxZ * 0.78F

        speedF = when(xyz.z.absoluteValue) {
            in accelerometerVM.maxZ..42F -> {
                frameInterval = 1L
                minSpeed.value
            }
            in maxVector..accelerometerVM.maxZ -> {
                frameInterval = 1L
                ((accelerometerVM.maxZ - xyz.z.absoluteValue) / (accelerometerVM.maxZ - maxVector)) * maxSpeed.value
            }
            else -> {
                maxSpeed.value
            }
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

    private fun getUpdatedShipPosition(): DpOffset {
        val oldPlacementDp = shipPosition.value
        val newX: Dp = when {

            motion.value.isRight() -> { (oldPlacementDp.x.value + deltaX).dp }
            motion.value.isLeft() -> { (oldPlacementDp.x.value - deltaX).dp }
            else -> { oldPlacementDp.x.value.dp }
        }
        val newY: Dp = when {
            motion.value.isUp() -> { (oldPlacementDp.y.value - deltaY).dp }
            motion.value.isDown() -> { (oldPlacementDp.y.value + deltaY).dp }
            else -> oldPlacementDp.x.value.dp
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
            this.x < 0.dp -> this xStartVerticalBounds sizeDp
            this.x > sizeDp.width -> this xEndVerticalBounds sizeDp
            this.y < 0.dp -> this yStartHorizontalBounds sizeDp
            this.y > sizeDp.height ->  this yEndHorizontalBounds sizeDp
            else -> this
        }
    }

    private infix fun DpOffset.xStartVerticalBounds(sizeDp: DpSize): DpOffset =
        this.verticalBounds( x = 0.dp, verticalBounds = 0.dp..sizeDp.height)
    private infix fun DpOffset.xEndVerticalBounds(sizeDp: DpSize): DpOffset =
        this.verticalBounds( x = sizeDp.width, verticalBounds = 0.dp..sizeDp.height)
    private infix fun DpOffset.yStartHorizontalBounds(sizeDp: DpSize): DpOffset =
        this.horizontalBounds( y = 0.dp, horizontalBounds = 0.dp..sizeDp.width)
    private infix fun DpOffset.yEndHorizontalBounds(sizeDp: DpSize): DpOffset =
        this.horizontalBounds( y = sizeDp.height, horizontalBounds = 0.dp..sizeDp.width)

    private fun DpOffset.verticalBounds(x: Dp, verticalBounds: ClosedRange<Dp>): DpOffset = when {
        this.y < verticalBounds.start -> DpOffset(x, verticalBounds.start)
        this.y > verticalBounds.endInclusive -> DpOffset(x, verticalBounds.endInclusive)
        else -> DpOffset(x, this.y)
    }

    private fun DpOffset.horizontalBounds(y: Dp, horizontalBounds: ClosedRange<Dp>): DpOffset = when {
        this.x < horizontalBounds.start -> DpOffset(horizontalBounds.start, y)
        this.x > horizontalBounds.endInclusive -> DpOffset(horizontalBounds.endInclusive, y)
        else -> DpOffset(this.x, y)
    }

    override fun onCleared() {
        accelerometerVM.stop()
        super.onCleared()
    }
}