package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.MainScreenUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel(): ViewModel() {
    val ui = MainScreenUI()

    val bluetoothPressure = PressureNavigationViewModel().create(Screens.BluetoothScreen)
    val wifiPressure = PressureNavigationViewModel().create(Screens.WifiScreen)

    fun handleTutoButtonClick(navigator: Navigator) {
        viewModelScope.launch(Dispatchers.IO) {
            navigator.navig(Screens.TutoScreen)
        }
    }
}
