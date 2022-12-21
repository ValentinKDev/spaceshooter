package com.mobilegame.spaceshooter.domain.model.screen.inGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.domain.model.screen.inGameScreen.motions.Motions
import com.mobilegame.spaceshooter.domain.model.screen.inGameScreen.motions.MotionsViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.InGameScreenAdapter

class InGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {

    val ui = InGameScreenAdapter.create(application, displaySize)
    val shipVM = SpaceShipViewModel(ui.position.pCenterDp)
    val motionVM = MotionsViewModel(shipVM)

}