package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.DevicesMenu
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.RegisterDeviceName
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.wifiScreen.WifiConnectingScreen
import com.mobilegame.spaceshooter.utils.analyze.eLog


@Composable
fun WifiScreen(navigator: Navigator, vm: WifiScreenViewModel = viewModel()) {
    val deviceName by remember { vm.deviceName }.collectAsState()
    //todo : remove de Device object acccess ??
    val visibleDeviceNameList by remember { Device.wifi.visibleDevices }.collectAsState()

    LaunchedEffect(visibleDeviceNameList) {
        eLog("WifiScreen", "visibleDeviceNameList ${visibleDeviceNameList.map { it.name }}")
    }
    LaunchedEffect(deviceName) {
        deviceName?.let { vm.nonNullNameTrigger() }
        eLog("WifiScreen", "launch device name : ${vm.deviceName.value}")
    }

    if (visibleDeviceNameList.isEmpty()) {
        deviceName?.run { WifiConnectingScreen(vm, navigator) }
        deviceName ?: run { RegisterDeviceName(navigator, vm.registerVM) }
    } else {
        DevicesMenu(vm, navigator, deviceName)
    }
}