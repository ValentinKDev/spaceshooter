package com.mobilegame.spaceshooter.presentation.ui.screens.connection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.repository.gameStats.GameStatsRepo
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu.DeviceMenuBand
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu.DevicesMenuBody
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.background.BackgroundBanner
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand

@Composable
fun DevicesMenu(vm: WifiScreenViewModel, navigator: Navigator, deviceName: String?) {
    val facingDevices by remember { Device.wifi.visibleDevices }.collectAsState()

    val navigate by remember { vm.navigate }.collectAsState()
    LaunchedEffect(navigate) {
        if (navigate) vm.navigateToShipMenuScreen(navigator)
    }

    Box (Modifier.fillMaxSize().background(MyColor.applicationBackground)) {
        BackgroundBanner(vm.ui.banner)
        deviceName?.let { _name ->
            if (facingDevices.isNotEmpty()) {
                TemplateWithBand(
                    backPressureHandler = vm.backPressureHandler,
                    ui = vm.ui.template,
                    header = {},
                    band = {
                        DeviceMenuBand(
                            vm,
                            vm.ui.deviceMenu.band,
                            _name,
                            facingDevices,
                            0
                        )
                    },
                    body = {
                        DevicesMenuBody(
                            ui = vm.ui.deviceMenu.body,
                            pressureVM = vm.pressureHandler,
                        )
                    }
                )
            }
        }
    }
}