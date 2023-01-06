package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.games.DuelGameScreenUI

class DuelGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val ui = DuelGameScreenUI(displaySize)
    val shipVM = SpaceShipViewModel(application, ui)
}