package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton

import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon.WifiSquare
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
fun WifiButton(vm: MainScreenViewModel, navigator: Navigator) {
    val startNavigation by remember { vm.wifiPressure.full }.collectAsState()
    LaunchedEffect(startNavigation) { if (startNavigation) navigator.navig(Screens.WifiScreen) }

    ChargingButton(
        handler = vm.wifiPressure,
        sizeDp = vm.ui.buttonWifi.sizes.squareSizeDp,
        content = {
            WifiSquare(vm) {
                WifiIcon( ui = vm.ui.buttonWifi )
            }
        },
    )
}