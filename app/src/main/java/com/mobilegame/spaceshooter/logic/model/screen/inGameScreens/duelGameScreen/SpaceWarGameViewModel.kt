package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class SpaceWarGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val TAG = "SpaceWarGameViewModel"
    val shipType = ShipType.Square
    val ui = SpaceWarGameScreenUI(displaySize, shipType)
    val shipVM = SpaceShipViewModel(application, ui, shipType)

    val pauseStateFlow = MutableSharedFlow<Boolean>()
}