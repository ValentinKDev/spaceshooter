package com.mobilegame.spaceshooter.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.Screens.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.repository.gameStats.MyDate
import com.mobilegame.spaceshooter.presentation.ui.screens.Creator
import com.mobilegame.spaceshooter.presentation.ui.screens.aboutUsScreen.AboutUsScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.bluetoothScreen.BluetoothScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.LaunchSpaceWarGameScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen.ShipMenuScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.MainScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.MenuScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.stats.StatsScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.tryAgainScreen.TryAgainScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.DuelTutoScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.WifiScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Navigation(navigator: Navigator) {
    val navController = rememberNavController()

    LaunchedEffect("navigation") {
        Log.e("Navigation", "Start")
        navigator.des.onEach {
            Log.e("Navigation", "Navigation: Previous ${navController.previousBackStackEntry?.destination?.route}")
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route,
//        startDestination = Screens.MenuScreen.route,
//        startDestination = Screens.StatsScreen.route,
//        startDestination = Screens.WifiScreen.route,
//        startDestination = Screens.DuelTutoScreen.route,
//        startDestination = Screens.SpaceWarScreen.route,
//        startDestination = AboutScreen.route,
//        startDestination = Screens.Creator.route,
//        startDestination = Screens.Test.route,
//        startDestination = StatsScreen.route,
    ) {
        composable(route = Screens.MenuScreen.route) {
            Log.v("Navigation", "to Screens.MenuScreen.route")
            MenuScreen(navigator) }
        composable(route = Screens.MainScreen.route) {
            Log.v("Navigation", "to Screens.MainScreen.route")
            MainScreen(navigator) }
        composable(route = Screens.BluetoothScreen.route) { BluetoothScreen(navigator) }
        composable(route = Screens.ShipMenuScreen.route) {
            Log.v("Navigation", "to Screens.ShipMenuScreen.route")
            ShipMenuScreen(navigator) }
//        composable(route = Screens.ShipMenuScreen.route) { ShipMenuScreen(navigator, ShipMenuViewModel()) }
        composable(route = Screens.WifiScreen.route) {
            Log.v("Navigation", "to Screens.WifiScreen.route")
            WifiScreen(navigator) }
        composable(route = Screens.DuelTutoScreen.route) { DuelTutoScreen(navigator) }
        composable(route = Screens.StatsScreen.route) {
            Log.v("Navigation", "to Screens.StatsScreen.route")
            StatsScreen() }
        composable(route = AboutScreen.route) {
            AboutUsScreen() }
        composable(
            route = Screens.TryAgainScreen.route
        ) {
            TryAgainScreen( navigator )
        }
        composable(
            route = SpaceWarScreen.route
        ) {
            Log.v("Navigation", "to Screens.SpaceWarScreen.route")
            LaunchSpaceWarGameScreen( navigator )
        }
        composable(route = Screens.Creator.route) { Creator(navigator) }
        composable(route = Screens.None.route) { Creator(navigator) }
        composable(route = Screens.Test.route) {
            val stats = TryAgainStats(
                wins = 2,
                losses = 1,
                streak = 1,
//                lastGame = GameResult.VICTORY,
                gameResult = GameResult.DEFEAT,
                shipName = ShipType.Square.info.name,
                enemiesName = "unknown",
                currentDate = MyDate.currentStr(),
            )
            TryAgainScreen( navigator)
        }
    }
}