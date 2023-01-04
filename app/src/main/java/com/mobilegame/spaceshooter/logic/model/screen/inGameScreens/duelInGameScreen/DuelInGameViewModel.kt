package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelInGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.games.DuelGameScreenUI

class DuelInGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val ui = DuelGameScreenUI(displaySize)

    val shipVM = SpaceShipViewModel()

    val motionVM = MotionsViewModel(application, ui.position.pCenterDp, ui.sizes.displayDpDeltaBox)
}