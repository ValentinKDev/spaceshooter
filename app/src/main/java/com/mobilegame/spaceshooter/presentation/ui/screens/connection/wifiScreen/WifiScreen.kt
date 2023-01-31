package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.RegisterDeviceName
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun WifiScreen(navigator: Navigator, vm: WifiScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("WifiScreen", "WifiScreen start ${vm.deviceName}")
    }

    val visibleDeviceNameList by remember { vm.connectionInfo.visibleDeviceNameList }.collectAsState()

    if (visibleDeviceNameList.isEmpty()) {
        vm.deviceName?.run {
            TemplateWithoutBand(
                navigator = navigator,
                backNav = Screens.BluetoothScreen.backNav,
                ui = vm.ui.template,
                header = {},
                body = { List(vm) },
            )
        }
        vm.deviceName ?: run { RegisterDeviceName(navigator, vm.registerVM) }
    } else {
        val devicesNames by remember { vm.connectionInfo.visibleDeviceNameList }.collectAsState()

        TemplateWithBand(
            navigator = navigator,
            backNav = Screens.BluetoothScreen.backNav,
            ui = vm.ui.template,
            header = {},
            band = {},
            body = {
                Column() {
                    devicesNames.forEach {
                        Text(text = it)
                    }
                }
            }
        )
    }
}