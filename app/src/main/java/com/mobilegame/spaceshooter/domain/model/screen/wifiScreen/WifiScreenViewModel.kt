package com.mobilegame.spaceshooter.domain.model.screen.wifiScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.WifiScreenUI

class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = WifiScreenUI()
    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}