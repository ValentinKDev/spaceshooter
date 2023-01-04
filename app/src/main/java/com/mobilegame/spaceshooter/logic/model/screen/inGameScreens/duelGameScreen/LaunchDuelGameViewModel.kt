package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.tutos.DuelTutoScreenUI

class LaunchDuelGameViewModel(application: Application): AndroidViewModel(application) {
    val ui = DuelTutoScreenUI()
    val gameVM = DuelGameViewModel(application, Size(Device.width, Device.height))
}