package com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.spaceShipMenuScreen.ShipPicking
import com.mobilegame.spaceshooter.logic.uiHandler.screens.tryAgainScreen.TryAgainUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TryAgainViewModel(val stats: TryAgainStats, val navigator: Navigator): ViewModel() {
    private val TAG = "TryAgainViewModel"

    private var nav: Navigator? = null

    val templateUI = TemplateUI(instantNavBack = true)
    val tryAgainUI = TryAgainUI()
    val shipPicking = ShipPicking(tryAgainUI.body.sizes.shipViewBox, ShipType.getType(stats.lastShipName))
    private val _stats = MutableStateFlow(TryAgainStats.EMPTY_TRY_AGAIN_STATS)
//    val stats: StateFlow<TryAgainStats> = _stats.asStateFlow()
//    var stats = TryAgainStats.EMPTY_TRY_AGAIN_STATS

    fun initNav(navigator: Navigator) {
        nav = navigator
    }
    fun initStats(tryAgainStats: TryAgainStats) { _stats.value = tryAgainStats }
    fun handleLeftArrowClick() = viewModelScope.launch { shipPicking.handleLeftArrowClick() }
    fun handleRightArrowClick() = viewModelScope.launch { shipPicking.handleRightArrowClick() }
}