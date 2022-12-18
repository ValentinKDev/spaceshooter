package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothButton

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothButton.BluetoothIcon.BluetoothSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun BlueToothButton(navigator: Navigator, vm: MainScreenViewModel) {
    ChargingButton(vm.bluetoothPressure, vm.ui.buttonBluetooth.sizes.squareHeightDp) {
        BluetoothSquare(vm) {
            BluetoothIcon(vm)
        }
    }
}

