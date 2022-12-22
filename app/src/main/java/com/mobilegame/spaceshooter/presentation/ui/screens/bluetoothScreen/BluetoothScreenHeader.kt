package com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton.BackButton


@Composable
internal fun BlueToothScreenHeader(navigator: Navigator, vm: BluetoothScreenViewModel) {
    BackButton(
        vm = vm.backButtonPressureNavigationVM,
        navigator = navigator,
        ui = vm.ui.template.backButton
    )
}