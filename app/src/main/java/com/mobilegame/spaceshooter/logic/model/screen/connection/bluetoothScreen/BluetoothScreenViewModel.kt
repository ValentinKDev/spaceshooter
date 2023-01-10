package com.mobilegame.spaceshooter.logic.model.screen.connection.bluetoothScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.BluetoothScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI

class BluetoothScreenViewModel(application: Application): AndroidViewModel(application) {

    val ui = BluetoothScreenUI()
//    val templateUI = TemplateUI()

//    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}