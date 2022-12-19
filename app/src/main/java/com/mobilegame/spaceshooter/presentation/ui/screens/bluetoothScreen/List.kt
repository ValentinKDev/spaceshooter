package com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.utils.extensions.alpha


@Composable
internal fun List(vm: BluetoothScreenViewModel) {
    CenterComposable {
        Box(
            Modifier
                .size(50.dp)
                .background(Color.Green.alpha(0.1F))
        ) {
            BluetoothIcon(ui = vm.ui.bluetoothIcon)
        }
    }
}