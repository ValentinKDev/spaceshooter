package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import android.icu.number.NumberFormatter.DecimalSeparatorDisplay
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.EndGameScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI

class SpaceWarGameViewModel(private val application: Application, private val display: Size): AndroidViewModel(application) {
    val TAG = "SpaceWarGameViewModel"
    private var nav: Navigator? = null
    var shipType: ShipType = ShipType.Square
//    var ui = SpaceWarGameScreenUI(display, shipType)
//    var shipVM = SpaceShipViewModel(application, ui, shipType)
    lateinit var ui: SpaceWarGameScreenUI
    lateinit var shipVM: SpaceShipViewModel
    val endUi = EndGameScreenUI()

    fun initNav(navigator: Navigator) {
        nav = navigator
    }
    fun updateShipType(ship: ShipType) {
        shipType = ship
        ui = SpaceWarGameScreenUI(display, shipType)
        shipVM = SpaceShipViewModel(application, ui, shipType)
    }
//    val pauseStateFlow = MutableSharedFlow<Boolean>()
}