package com.mobilegame.spaceshooter.presentation.ui.screens.connection.wifiScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.background.BackgroundBanner
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand

@Composable
fun WifiConnectingScreen(vm: WifiScreenViewModel, navigator: Navigator) {

    Box (
        Modifier
            .fillMaxSize()
            .background(MyColor.applicationBackground)) {
        BackgroundBanner(vm.ui.banner)
        TemplateWithoutBand(
//            navigator = navigator,
//            backNav = Screens.WifiScreen.backNav ?: Screens.None,
            backNav = vm.backNavScreen,
            ui = vm.ui.template,
            header = {},
            body = { WifiConnectingBody(vm) },
        )
    }
}