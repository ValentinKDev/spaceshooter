package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton.BluetoothIcon.BluetoothSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun BlueToothButton(navigator: Navigator, vm: MainScreenViewModel) {
    val startNavigationJob by remember { vm.bluetoothPressure.full }.collectAsState()
    LaunchedEffect(startNavigationJob) { if (startNavigationJob) navigator.navig(Screens.BluetoothScreen)}

    ChargingButton(
        handler = vm.bluetoothPressure,
        sizeDp = vm.ui.buttonBluetooth.sizes.squareSizeDp,
        content = {
            BluetoothSquare(vm) {
                BluetoothIcon(vm.ui.buttonBluetooth)
            }
        },
    )
}

