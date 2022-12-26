package com.mobilegame.spaceshooter.logic.model.screen.inGameScreen.motions

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.data.AccelerometerListener
import com.mobilegame.spaceshooter.logic.model.data.XYZ
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreen.SpaceShipViewModel
import com.mobilegame.spaceshooter.utils.analyze.iLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MotionsViewModel(val shipVM: SpaceShipViewModel): ViewModel() {
    private val _motion = MutableStateFlow(Motions.None)
    val motion: StateFlow<Motions> = _motion.asStateFlow()
    fun motionChange(motion: Motions) {
        changeMotionTo(motion)
        startMoving()
    }

    fun updatePlayerPosition() {
        getMotionFrom( AccelerometerListener.devicePosition )
    }
    private fun getMotionFrom(xyz: XYZ) {

    }

    private fun changeMotionTo(motion: Motions) {
        vLog("MotionsVM::changeMotionTo", motion.name)
        _motion.value = motion
    }

    private fun startMoving() {
        viewModelScope.launch(Dispatchers.IO) {
            move()
        }
    }

    private suspend fun move() {
        iLog("MotionsVM::move", "position ${shipVM.pCenterDp.value}")
        while (motion.value not Motions.None) {
            shipVM.moveShipTo( getNewPlacement(motion.value) )
            delay(10)
        }
    }

    private fun getNewPlacement(motionCall: Motions): DpOffset {
        val oldPlacement = shipVM.pCenterDp.value
        val newX = when (motionCall) {
            Motions.Right -> oldPlacement.x + 1.dp
            Motions.Left -> oldPlacement.x - 1.dp
            Motions.Up -> oldPlacement.x
            Motions.Down -> oldPlacement.x
            Motions.None -> oldPlacement.x
        }
        val newY = when (motionCall) {
            Motions.Right -> oldPlacement.y
            Motions.Left -> oldPlacement.y
            Motions.Up -> oldPlacement.y - 1.dp
            Motions.Down -> oldPlacement.y + 1.dp
            Motions.None -> oldPlacement.y
        }
        return DpOffset(x = newX, y = newY)
    }

    infix fun Motions.not(motions: Motions): Boolean = this != motions
}