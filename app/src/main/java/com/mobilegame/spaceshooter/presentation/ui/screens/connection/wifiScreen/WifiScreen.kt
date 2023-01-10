package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.RegisterDeviceName
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.wLog


@Composable
fun WifiScreen(navigator: Navigator, vm: WifiScreenViewModel = viewModel()) {

    LaunchedEffect(true) {
        eLog("WifiScreen", "start")
    }
    LaunchedEffect(vm.userName) {
        eLog("WifiScreen", "recomposition ${vm.userName}")
        eLog("WifiScreen", "recomposition ${vm.userName?.run { 1 } ?: run {2}}")
    }

    vm.userName?.run{
        eLog("WifiScreen", "let ${vm.userName}")
        TemplateWithoutBand(
            navigator = navigator,
            backNav = Screens.BluetoothScreen.backNav,
            ui = vm.ui.template,
            header = {
                     Text("du TExt")
            },
            emptySpace = { List(vm) },
        )
    }
    vm.userName ?: RegisterDeviceName(navigator)
}