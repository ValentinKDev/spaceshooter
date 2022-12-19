package com.mobilegame.spaceshooter.domain.model.screen.bluetoothScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.BluetoothScreenObj

class BluetoothScreenViewModel(application: Application): AndroidViewModel(application) {

    val ui = BluetoothScreenObj.create(application)

    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}