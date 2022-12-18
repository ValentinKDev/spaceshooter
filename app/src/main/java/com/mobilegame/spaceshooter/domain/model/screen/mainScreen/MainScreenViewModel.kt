package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = MainScreenObj.create(application)

    val bluetoothPressure = PressureHandler().create()
    val wifiPressure = PressureHandler().create()
}