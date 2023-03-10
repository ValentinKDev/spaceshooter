package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.tutos.DuelTutoScreenUI

class LaunchDuelGameViewModel(application: Application): AndroidViewModel(application) {
    val ui = DuelTutoScreenUI()
    val gameVM = SpaceWarGameViewModel(application, Size(Device.metrics.width, Device.metrics.height))
}