package com.mobilegame.spaceshooter.logic.model.screen.wifiScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.WifiScreenUI

class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = WifiScreenUI()
    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}