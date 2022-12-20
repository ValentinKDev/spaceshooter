package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen.BluetoothBanner
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.AlignComposableToStart
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.PaddingComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.elements.WifiBanner

@Composable
internal fun List(vm: WifiScreenViewModel) {
    PaddingComposable(
        topPaddingRatio = vm.ui.banner.ratios.topPadding,
    ) {
        AlignComposableToStart {
            Box( Modifier.size(1.dp) ) {
//                BluetoothBanner(ui = vm.ui.bluetoothIcon)
                WifiBanner(vm.ui.banner.wifiIcon)
            }
        }
    }
}