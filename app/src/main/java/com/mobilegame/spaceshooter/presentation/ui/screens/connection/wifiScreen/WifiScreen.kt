package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.RegisterDeviceName
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.toSquare


@Composable
fun WifiScreen(navigator: Navigator, vm: WifiScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("WifiScreen", "WifiScreen start ${vm.userName}")
    }

    vm.userName?.run{
        TemplateWithoutBand(
            navigator = navigator,
            backNav = Screens.BluetoothScreen.backNav,
            ui = vm.ui.template,
            header = {},
            emptySpace = { List(vm) },
        )
    }
    vm.userName ?: run { RegisterDeviceName(navigator, vm.registerVM) }

}