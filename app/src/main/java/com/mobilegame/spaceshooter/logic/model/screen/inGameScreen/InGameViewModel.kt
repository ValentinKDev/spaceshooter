package com.mobilegame.spaceshooter.logic.model.screen.inGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.data.sensor.AccelerometerSensor
import com.mobilegame.spaceshooter.logic.model.data.AccelerometerListener
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreen.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.games.DuelGameScreenUI

class InGameViewModel(application: Application, displaySize: Size): AndroidViewModel(application) {
    val ui = DuelGameScreenUI(displaySize)
    val shipVM = SpaceShipViewModel(ui.position.pCenterDp, ui.sizes.displayDpDeltaBox)
    val motionVM = MotionsViewModel(shipVM)

    val sensorVM = AccelerometerListener.initializeSensor(AccelerometerSensor(application), motionVM)
}