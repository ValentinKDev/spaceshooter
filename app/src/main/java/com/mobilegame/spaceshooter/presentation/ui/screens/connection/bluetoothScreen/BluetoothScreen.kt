package com.mobilegame.spaceshooter.presentation.ui.screens.connection.bluetoothScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand
import com.mobilegame.spaceshooter.utils.analyze.verbalLog


@Composable
fun BluetoothScreen(navigator: Navigator, vm: BluetoothScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        verbalLog("BluetoothScreen", "start")
    }

    TemplateWithoutBand(
        navigator = navigator,
        backNav = Screens.BluetoothScreen.backNav,
        header = {
//            BlueToothScreenHeader(navigator, vm)
        },
        emptySpace = {
            BluetoothScreenBody(vm)
        }
    )
}