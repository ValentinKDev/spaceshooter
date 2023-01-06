package com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToStart
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable


@Composable
internal fun BluetoothScreenBody(vm: BluetoothScreenViewModel) {
    PaddingComposable(
        topPaddingRatio = vm.ui.banner.topPadding,
    ) {
        AlignComposableToStart {
            Box( Modifier.size(1.dp) ) {
                BluetoothBanner(ui = vm.ui.banner.bluetoothIcon)
            }
        }
    }
}