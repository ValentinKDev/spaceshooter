package com.mobilegame.spaceshooter.presentation.ui.screens.connection

import android.util.Log
import androidx.compose.runtime.*
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu.DeviceMenuBand
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.deviceMenu.DevicesMenuBody
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithBand
import kotlin.math.log

@Composable
fun DevicesMenu(vm: WifiScreenViewModel, navigator: Navigator, deviceName: String?) {
    val facingDevices by remember { Device.wifi.visibleDevices }.collectAsState()

    LaunchedEffect(facingDevices) {
        Log.i("DeviceMenu", "DevicesMenu: facingDevices list size ${facingDevices.size}")
//        vm.newVisibleDeviceTrigger()
    }

    deviceName?.let { _name ->
        if (facingDevices.isNotEmpty()) {
            TemplateWithBand(
                navigator = navigator,
                backNav = Screens.BluetoothScreen.backNav,
                ui = vm.ui.template,
                header = {},
                band = {
                    DeviceMenuBand(
                        vm.ui.deviceMenu.band,
                        _name,
                        facingDevices,
                        0
                    )
                },
                body = {
                    DevicesMenuBody(
                        ui = vm.ui.deviceMenu.body,
                        navigator = navigator,
                        pressureVM = vm.pressureVM,
                    )
                }
            )
        }
    }
}