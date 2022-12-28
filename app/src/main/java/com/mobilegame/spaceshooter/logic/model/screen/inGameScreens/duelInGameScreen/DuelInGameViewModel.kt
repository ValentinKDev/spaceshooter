package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelInGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.data.sensor.AccelerometerSensor
import com.mobilegame.spaceshooter.logic.model.data.sensor.AccelerometerListener
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.games.DuelGameScreenUI
import com.mobilegame.spaceshooter.logic.model.sensor.AccelerometerViewModel

class DuelInGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val ui = DuelGameScreenUI(displaySize)
//    val shipVM = SpaceShipViewModel(ui.position.pCenterDp, ui.sizes.displayDpDeltaBox)
    val shipVM = SpaceShipViewModel()
//    private val motionVM = MotionsViewModel(shipVM, application)

//    val accelerometerVM = AccelerometerViewModel(AccelerometerSensor(application))
//val motionVM = MotionsViewModel(application, ui.position.pCenterDp, ui.sizes.displayDpDeltaBox, accelerometerVM)
    val motionVM = MotionsViewModel(application, ui.position.pCenterDp, ui.sizes.displayDpDeltaBox)
}