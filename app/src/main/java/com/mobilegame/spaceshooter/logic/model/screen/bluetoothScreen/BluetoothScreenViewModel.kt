package com.mobilegame.spaceshooter.logic.model.screen.bluetoothScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.BluetoothScreenUI

class BluetoothScreenViewModel(application: Application): AndroidViewModel(application) {

    val ui = BluetoothScreenUI()

    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}