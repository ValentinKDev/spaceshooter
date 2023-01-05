package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.MutableList.cancelAll
import com.mobilegame.spaceshooter.utils.extensions.notZero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceShipViewModel(): ViewModel() {

    private val _life = MutableStateFlow(100)
    val life: StateFlow<Int> = _life.asStateFlow()

    val ammoVM = AmmunitionViewModel()
}