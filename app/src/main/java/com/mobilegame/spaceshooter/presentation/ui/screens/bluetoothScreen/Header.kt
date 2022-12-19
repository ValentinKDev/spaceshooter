package com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton.BackButton


@Composable
internal fun Header(navigator: Navigator, vm: BluetoothScreenViewModel) {

    PaddingComposable(
        startPaddingRatio = 0.01F
    ) {
        BackButton(vm = vm.backButtonPressureNavigationVM, navigator = navigator, vm.ui.backButton)
    }

}