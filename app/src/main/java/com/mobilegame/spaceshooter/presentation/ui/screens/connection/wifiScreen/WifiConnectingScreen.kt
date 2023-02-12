package com.mobilegame.spaceshooter.presentation.ui.screens.connection.wifiScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand

@Composable
fun WifiConnectingScreen(vm: WifiScreenViewModel, navigator: Navigator) {
    val linkState by remember { Device.wifi.linkState }.collectAsState()

    TemplateWithoutBand(
        navigator = navigator,
        backNav = Screens.WifiScreen.backNav,
        ui = vm.ui.template,
        header = {},
        body = { WifiConnectingBody(vm) },
    )
}