package com.mobilegame.spaceshooter.logic.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.MainScreenUI
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel(): ViewModel() {
    val ui = MainScreenUI()

    val bluetoothPressure = PressureViewModel()
    val wifiPressure = PressureViewModel()

    fun handleTutoButtonClick(navigator: Navigator) {
        viewModelScope.launch(Dispatchers.IO) {
            navigator.navig(Screens.DuelTutoScreen)
        }
    }
}
