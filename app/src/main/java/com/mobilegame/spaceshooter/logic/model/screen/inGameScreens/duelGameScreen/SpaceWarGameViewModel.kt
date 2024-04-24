package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.content.Context
import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.EndGameScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.StrArgumentNav
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpaceWarGameViewModel(
    val userShipType: ShipType,
    display: Size,
    context: Context,
    val tryAgainStats: TryAgainStats = TryAgainStats.EMPTY_TRY_AGAIN_STATS,
): ViewModel() {
    val TAG = "SpaceWarGameViewModel"

    var ui = SpaceWarGameScreenUI(display, userShipType)
    val endUi = EndGameScreenUI()
//    var shipVM = SpaceShipViewModel(context, ui, userShipType, tryAgainStats)
    var shipVM = SpaceShipViewModel(context, ui, userShipType)
    private var nav: Navigator? = null

    init {
//        Log.i(TAG, "init: ")
//        viewModelScope.launch(Dispatchers.Main) { listenToGameResult() }
    }
    fun initNav(navigator: Navigator) {
        nav = navigator
        shipVM.lifeVM.initNav(navigator)
    }
//    private suspend fun listenToGameResult() {
//        Device.event.gameResult.collect { gameResult ->
//            Log.i(TAG, "listenToGameResult: collect result : $gameResult")
//            tryAgainStats.updateWith(gameResult, userShipType.info.name)
//            //todo send the death to the enemie
//            nav?.navig(destination = Screens.TryAgainScreen, argumentStr = StrArgumentNav.serializeArgToTryAgain(tryAgainStats))
//        }
//    }

fun navigateToTryAgain(navigator: Navigator, gameResult: GameResult) = viewModelScope.launch {
    tryAgainStats.updateWith(gameResult, userShipType.info.name)
    //todo send the death to the enemie
    navigator.navig(destination = Screens.TryAgainScreen, argumentStr = StrArgumentNav.serializeArgToTryAgain(tryAgainStats))
    Device.event.gameResult.emit(GameResult.OnGoing)
    onCleared()
}

    override fun onCleared() {
        Log.w(TAG, "onCleared: ")
        super.onCleared()
    }
}