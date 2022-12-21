package com.mobilegame.spaceshooter.domain.model.screen.inGameScreen

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpaceShipViewModel(): ViewModel() {
    private val _munitions = MutableStateFlow<Int>(10)
    val munitions: StateFlow<Int> = _munitions.asStateFlow()


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
}