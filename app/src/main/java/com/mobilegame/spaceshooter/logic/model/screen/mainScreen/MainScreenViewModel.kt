package com.mobilegame.spaceshooter.logic.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.uiHandler.MainScreenUI
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(): ViewModel() {
    val ui = MainScreenUI()

    val bluetoothPressure = PressureHandler(null)
//    val wifiPressure = PressureHandler()
    val wifiPressure = PressureHandler(Screens.WifiScreen)
    val backNavScreen = Screens.MenuScreen

    private val _warning = MutableStateFlow(0)
    val warning: StateFlow<Int> = _warning.asStateFlow()
    private fun updateWarningTo(n: Int) { _warning.value = n }

    fun handleBluetoothButton() = viewModelScope.launch(Dispatchers.IO) {
        updateWarningTo(1)
        delay(3500)
        updateWarningTo(0)
    }
    fun handleTutoButtonClick() = viewModelScope.launch(Dispatchers.IO) {
        updateWarningTo(2)
        delay(3500)
        updateWarningTo(0)
//            navigator.navig(Screens.DuelTutoScreen)
    }

    fun getInstruction(): String = when (warning.value) {
        1 -> ui.instruction.text.warningBluetooth
        2 -> ui.instruction.text.warningTuto
        else -> ui.instruction.text.principalMessage
    }
}
