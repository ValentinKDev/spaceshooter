package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI

class SpaceWarGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val ui = SpaceWarGameScreenUI(displaySize)
    val shipVM = SpaceShipViewModel(application, ui, ShipType.Square)
//    val shipVM = SpaceShipViewModel(application, ui, ShipType.Circle)
}