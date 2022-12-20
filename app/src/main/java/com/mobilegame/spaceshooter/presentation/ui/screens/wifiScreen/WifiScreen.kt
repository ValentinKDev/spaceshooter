package com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.domain.model.screen.wifiScreen.WifiScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen.Header
import com.mobilegame.spaceshooter.utils.analyze.wLog


@Composable
fun WifiScreen(navigator: Navigator, vm: WifiScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        wLog("WifiScreen", "start")
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.header.ratios.heightWeight)
        ) {
            Header(
                vm = vm,
                navigator = navigator,
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.delimiter.ratios.heightWeight)
                .background(vm.ui.color.contrast)
        ){ }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.list.ratios.heightWeight)
        ) {
            List()
        }
    }
}