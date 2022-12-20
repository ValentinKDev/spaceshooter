package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.MainScreenAdapter
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = MainScreenAdapter.create(application)

    val bluetoothPressure = PressureNavigationViewModel().create(Screens.BluetoothScreen)
    val wifiPressure = PressureNavigationViewModel().create(Screens.WifiScreen)

    fun handleTutoButtonClick(navigator: Navigator) {
        viewModelScope.launch(Dispatchers.IO) {
            navigator.navig(Screens.TutoScreen)
        }
    }
}
