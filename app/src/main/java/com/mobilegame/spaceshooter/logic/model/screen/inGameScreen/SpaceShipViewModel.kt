package com.mobilegame.spaceshooter.logic.model.screen.inGameScreen

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpaceShipViewModel(pCenterStartDp: DpOffset, private val displaySizeDp: DpSize): ViewModel() {

    private val _life = MutableStateFlow(100)
    val life: StateFlow<Int> = _life.asStateFlow()

    private val _munitions = MutableStateFlow<Int>(10)
    val munitions: StateFlow<Int> = _munitions.asStateFlow()

    private val _pCenterDp = MutableStateFlow(pCenterStartDp)
    val pCenterDp: StateFlow<DpOffset> = _pCenterDp.asStateFlow()
    fun moveShipTo(newPCenter: DpOffset) {
        _pCenterDp.value = newPCenter inBoundsOf displaySizeDp
    }

    fun getMunitionOffset(ui: SpaceShipIconAdapter, n: Int): Offset = when (n) {
        1 -> ui.munitions.m1
        2 -> ui.munitions.m2
        3 -> ui.munitions.m3
        4 -> ui.munitions.m4
        5 -> ui.munitions.m5
        6 -> ui.munitions.m6
        7 -> ui.munitions.m7
        8 -> ui.munitions.m8
        9 -> ui.munitions.m9
        10 -> ui.munitions.m10
        else -> {
            eLog("SpaceShipVM::getMunitionOffset", "ERROR getMunition(ui, --> ${munitions.value})")
            Offset.Zero
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
}