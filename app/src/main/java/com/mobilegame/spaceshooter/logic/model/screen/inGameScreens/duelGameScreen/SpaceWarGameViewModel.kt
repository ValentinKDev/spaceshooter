package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.content.Context
import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.EndGameScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI

//class SpaceWarGameViewModel(val shipType: ShipType,  application: Application, display: Size): AndroidViewModel(application) {
class SpaceWarGameViewModel(val userShipType: ShipType, enemiesShipType: ShipType, display: Size, context: Context): ViewModel() {
    val TAG = "SpaceWarGameViewModel"
    private var nav: Navigator? = null
//    var shipType: ShipType = ShipType.Square
    var ui = SpaceWarGameScreenUI(display, userShipType, enemiesShipType)
//    var shipVM = SpaceShipViewModel(application, ui, shipType)
    var shipVM = SpaceShipViewModel(context, ui, userShipType)
//    lateinit var ui: SpaceWarGameScreenUI
//    lateinit var shipVM: SpaceShipViewModel
    val endUi = EndGameScreenUI()

    fun initNav(navigator: Navigator) {
        nav = navigator
    }
    init { Log.i(TAG, "init: ") }
//    fun updateShipType(ship: ShipType) {
//        shipType = ship
//        ui = SpaceWarGameScreenUI(display, shipType)
//        shipVM = SpaceShipViewModel(application, ui, shipType)
//    }
//    val pauseStateFlow = MutableSharedFlow<Boolean>()
}