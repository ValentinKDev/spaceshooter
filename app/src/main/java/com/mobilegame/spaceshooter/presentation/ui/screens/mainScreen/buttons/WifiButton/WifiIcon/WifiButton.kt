package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiButton.WifiIcon

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun WifiButton(vm: MainScreenViewModel) {
    ChargingButton(handler = vm.wifiPressure, size = vm.ui.buttonWifi.sizes.squareHeightDp) {
        WifiSquare(vm)
    }
}