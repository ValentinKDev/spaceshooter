package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.theme.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.utils.analyze.wLog


@Composable
fun WifiScreen(navigator: Navigator, vm: WifiScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        wLog("WifiScreen", "start")
    }

    MainTemplate(
        header = {
            Header( vm = vm, navigator = navigator, )
        },
        emptySpace = {
            List(vm)
        },
    )
}