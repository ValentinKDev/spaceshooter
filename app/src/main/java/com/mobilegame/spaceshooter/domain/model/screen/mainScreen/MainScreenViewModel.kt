package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.MainScreenObj

class MainScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = MainScreenObj.create(application)

    val bluetoothPressure = PressureNavigationViewModel().create(Screens.BluetoothScreen)
    val wifiPressure = PressureNavigationViewModel().create(Screens.WifiScreen)
}