package com.mobilegame.spaceshooter.presentation.ui.screens.connection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu.DeviceMenuBand
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu.DevicesMenuBody
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand

@Composable
fun DevicesMenu(vm: WifiScreenViewModel, navigator: Navigator, deviceName: String?) {
    val facingDevices by remember { vm.connectionInfo.visibleDeviceNameList }.collectAsState()

    deviceName?.let { _name ->
        if (facingDevices.isNotEmpty()) {
            TemplateWithBand(
                navigator = navigator,
                backNav = Screens.BluetoothScreen.backNav,
                ui = vm.ui.template,
                header = {},
                band = { DeviceMenuBand(vm.ui.deviceMenu.band, _name, facingDevices, 0) },
                body = { DevicesMenuBody(ui = vm.ui.deviceMenu.body) }
            )
        }
    }
}