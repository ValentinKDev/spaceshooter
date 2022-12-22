package com.mobilegame.spaceshooter.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.screens.bluetoothScreen.BluetoothScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.MainScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.DuelTutoScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.WifiScreen
import com.mobilegame.spaceshooter.utils.analyze.verbalLog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(navigator: Navigator) {
    val navController = rememberNavController()

    LaunchedEffect(true) {
        verbalLog("Navigation", "Start")
        navigator.des.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
//        startDestination = Screens.MainScreen.route,
        startDestination = Screens.DuelTutoScreen.route,
    ) {
        composable(route = Screens.MainScreen.route) { MainScreen(navigator) }
        composable(route = Screens.BluetoothScreen.route) { BluetoothScreen(navigator) }
        composable(route = Screens.WifiScreen.route) { WifiScreen(navigator) }
        composable(route = Screens.DuelTutoScreen.route) { DuelTutoScreen(navigator) }
    }
}
