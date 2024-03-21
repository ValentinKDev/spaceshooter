package com.mobilegame.spaceshooter.presentation.ui.navigation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.Creator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.bluetoothScreen.BluetoothScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.LaunchSpaceWarGameScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu.ShipMenuScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.MainScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.MenuScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.DuelTutoScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.WifiScreen
import com.mobilegame.spaceshooter.utils.analyze.verbalLog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(navigator: Navigator) {
    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(true) {
        verbalLog("Navigation", "Start")
        navigator.des.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route,
//        startDestination = Screens.MenuScreen.route,
//        startDestination = Screens.WifiScreen.route,
//        startDestination = Screens.DuelTutoScreen.route,
//        startDestination = Screens.SpaceWarScreen.route,
//        startDestination = Screens.Creator.route,
//        startDestination = Screens.Test.route,
    ) {
        composable(route = Screens.MenuScreen.route) { MenuScreen(navigator) }
        composable(route = Screens.MainScreen.route) { MainScreen(navigator) }
        composable(route = Screens.BluetoothScreen.route) { BluetoothScreen(navigator) }
        composable(route = Screens.ShipMenuScreen.route) { ShipMenuScreen(navigator) }
        composable(route = Screens.WifiScreen.route) { WifiScreen(navigator) }
        composable(route = Screens.DuelTutoScreen.route) { DuelTutoScreen(navigator) }
//        composable(route = Screens.SpaceWarScreen.route) { LaunchSpaceWarGameScreen(navigator) }
        composable(
            route = Screens.SpaceWarScreen.route .plus("/{${"ShipTypeName"}}"),
            arguments = listOf (navArgument("ShipTypeName") {type = NavType.StringType})
        ) {entry ->
            Log.v("Navigation", "to Screens.SpaceWarScreen.route")
            entry.arguments?.getString("ShipTypeName")?.let { arg ->
                Log.v("Navigation", "arg $arg")
//                LaunchSpaceWarGameScreen(navigator, arg)
                LaunchSpaceWarGameScreen(navigator, LaunchDuelGameViewModel(shipType = ShipType.getType(arg), context))
            }
        }
        composable(route = Screens.Creator.route) { Creator(navigator) }
//        composable(route = Screens.Test.route) { Test(navigator) }
    }
}