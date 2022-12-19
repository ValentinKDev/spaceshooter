package com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.domain.model.screen.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.verbalLog


@Composable
fun BluetoothScreen(navigator: Navigator, vm: BluetoothScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        verbalLog("BluetoothScreen", "start")
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.header.ratios.heightWeight)
        ) {
            Header(navigator, vm)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.delimiter.ratios.heightWeight)
                .background(vm.ui.colors.contrast)
        ) {
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(vm.ui.list.ratios.heightWeight)
        ) {
            List(vm)
        }
    }
}