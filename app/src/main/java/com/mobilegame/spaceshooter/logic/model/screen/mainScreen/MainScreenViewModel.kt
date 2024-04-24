package com.mobilegame.spaceshooter.logic.model.screen.mainScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
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

    val bluetoothPressure = PressureViewModel()
    val wifiPressure = PressureViewModel()

    private val _warning = MutableStateFlow(false)
    val warning: StateFlow<Boolean> = _warning.asStateFlow()
    private fun updateWarningTo(bool: Boolean) { _warning.value = bool }

    fun handleBluetoothButton() = viewModelScope.launch(Dispatchers.IO) {
        updateWarningTo(true)
        delay(3500)
        updateWarningTo(false)
    }
    fun handleTutoButtonClick(navigator: Navigator) = viewModelScope.launch(Dispatchers.IO) {
            navigator.navig(Screens.DuelTutoScreen)
    }
}
