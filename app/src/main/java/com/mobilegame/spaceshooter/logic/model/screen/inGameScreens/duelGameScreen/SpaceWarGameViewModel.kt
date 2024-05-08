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
import com.mobilegame.spaceshooter.logic.repository.gameStats.GameStatsRepo
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
//): ViewModel() {
): ViewModel() {
    val TAG = "SpaceWarGameViewModel"

    var ui = SpaceWarGameScreenUI(display, userShipType)
    val endUi = EndGameScreenUI()
//    var shipVM = SpaceShipViewModel(context, ui, userShipType, tryAgainStats)
    var shipVM = SpaceShipViewModel(context, ui, userShipType)
    private val statsRepo = GameStatsRepo(context)
    private var nav: Navigator? = null

    init {
        Log.e(TAG, "init vm")
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

    fun navigateToTryAgain(gameResult: GameResult) = viewModelScope.launch {
        Log.v(TAG, "navigateToTryAgain: facingDevice name 0 : ${Device.wifi.visibleDevices.value[0].name}")
        Log.v(TAG, "navigateToTryAgain: facingDevice name : ${Device.wifi.visibleDevices.value.map { it.name }}")
        tryAgainStats.updateWith(gameResult, userShipType.info.name, Device.wifi.visibleDevices.value[0].name)
        statsRepo.addGameResult(gameStats = tryAgainStats)
        //todo send the death to the enemie
        Device.event.gameResult.emit(GameResult.OnGoing)
        val argStr = StrArgumentNav.serializeArgToTryAgain(tryAgainStats)
//        Device.navigation.argStr = StrArgumentNav.serializeArgToTryAgain(tryAgainStats)
        Device.navigation.nav. navig(toScreen = Screens.TryAgainScreen, argumentStr = argStr)
        onCleared()
    }

    override fun onCleared() {
        Log.w(TAG, "onCleared: ")
        super.onCleared()
    }
}