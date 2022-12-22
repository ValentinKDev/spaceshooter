package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun WifiButton(vm: MainScreenViewModel, navigator: Navigator) {
    ChargingButton(
        handler = vm.wifiPressure,
        sizeDp = vm.ui.buttonWifi.sizes.squareHeightDp,
        navigator = navigator,
    ) {
        WifiSquare(vm) {
            WifiIcon( ui = vm.ui.buttonWifi )
        }
    }
}