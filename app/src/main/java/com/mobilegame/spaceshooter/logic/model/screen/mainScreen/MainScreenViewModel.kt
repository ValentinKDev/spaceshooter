package com.mobilegame.spaceshooter.logic.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.pression.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.MainScreenUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel(): ViewModel() {
    val ui = MainScreenUI()

    val bluetoothPressure = PressureNavigationViewModel(Screens.BluetoothScreen)
    val wifiPressure = PressureNavigationViewModel(Screens.WifiScreen)

    fun handleTutoButtonClick(navigator: Navigator) {
        viewModelScope.launch(Dispatchers.IO) {
            navigator.navig(Screens.DuelTutoScreen)
        }
    }
}
