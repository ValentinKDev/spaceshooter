package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton.BluetoothIcon.BluetoothSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun BlueToothButton(navigator: Navigator, vm: MainScreenViewModel) {
    ChargingButton(
        handler = vm.bluetoothPressure,
        sizeDp = vm.ui.buttonBluetooth.sizes.squareSizeDp,
        navigator = navigator,
    ) {
        BluetoothSquare(vm) {
            BluetoothIcon(vm.ui.buttonBluetooth)
        }
    }
}

