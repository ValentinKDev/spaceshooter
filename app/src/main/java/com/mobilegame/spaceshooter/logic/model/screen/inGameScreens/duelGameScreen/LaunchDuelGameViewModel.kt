package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.presentation.ui.navigation.StrArgumentNav
import kotlinx.coroutines.launch

//class LaunchDuelGameViewModel(private val userShipType: ShipType, private var tryAgainStats: TryAgainStats, context: Context) {
//class LaunchDuelGameViewModel(private val userShipType: ShipType, private var tryAgainStats: TryAgainStats, context: Context) {
class LaunchDuelGameViewModel(application: Application): AndroidViewModel(application) {
    val infoList: Pair<ShipType, TryAgainStats> = StrArgumentNav.deserializeArgToInGame(Device.navigation.argStr)
    val userShipType: ShipType = infoList.first
    val tryAgainStats: TryAgainStats = infoList.second

    val TAG = "LaunchDuelGameViewModel"
    val gameVM = SpaceWarGameViewModel(
        userShipType = userShipType,
        tryAgainStats = tryAgainStats,
        display = Size(Device.metrics.width, Device.metrics.height),
//        context = context
        context = getApplication()
    )
    init { Log.e(TAG, "init: ")
        Log.i(TAG, "init: tryagainstats ${tryAgainStats.wins}, ${tryAgainStats.losses}, ${tryAgainStats.streak}")
    }
}