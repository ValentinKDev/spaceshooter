package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.games.DuelGameScreenUI
import com.mobilegame.spaceshooter.utils.analyze.eLog

class DuelGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val ui = DuelGameScreenUI(displaySize)

    val shipVM = SpaceShipViewModel()


    val motionVM = MotionsViewModel(
        context = application,
        startPosition = ui.position.pCenterDp,
        displaySizeDp = ui.sizes.displayDpDeltaBox,
    )
}