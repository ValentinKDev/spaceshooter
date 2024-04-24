package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.BluetoothButton.BluetoothIcon.BluetoothSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun BlueToothButton(navigator: Navigator, vm: MainScreenViewModel) {
    val chargedButtonIsFull by remember { vm.bluetoothPressure.full }.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(chargedButtonIsFull) {
        if (chargedButtonIsFull) vm.handleBluetoothButton()
//            Toast.makeText(context, "Bluetooth is not yet available", Toast.LENGTH_SHORT)
//            navigator.navig(Screens.BluetoothScreen)
    }

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

