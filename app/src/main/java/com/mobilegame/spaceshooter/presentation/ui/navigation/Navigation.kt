package com.mobilegame.spaceshooter.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
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
//        startDestination = Test.route,
//        startDestination = MainScreen.route,
        startDestination = MenuScreen.route,
//        startDestination = StatsScreen.route,
//        startDestination = WifiScreen.route,
//        startDestination = DuelTutoScreen.route,
//        startDestination = SpaceWarScreen.route,
//        startDestination = AboutScreen.route,
//        startDestination = Creator.route,
//        startDestination = StatsScreen.route,
    ) {
        composable(route = MenuScreen.route) {
            Log.v("Navigation", "to MenuScreen.route")
            MenuScreen(navigator) }
        composable(route = MainScreen.route) {
            Log.v("Navigation", "to MainScreen.route")
            MainScreen(navigator) }
        composable(route = BluetoothScreen.route) { BluetoothScreen(navigator) }
        composable(route = ShipMenuScreen.route) {
            Log.v("Navigation", "to ShipMenuScreen.route")
            ShipMenuScreen(navigator) }
//        composable(route = ShipMenuScreen.route) { ShipMenuScreen(navigator, ShipMenuViewModel()) }
        composable(route = WifiScreen.route) {
            Log.v("Navigation", "to WifiScreen.route")
            WifiScreen(navigator) }
        composable(route = DuelTutoScreen.route) { DuelTutoScreen(navigator) }
        composable(route = StatsScreen.route) {
            Log.v("Navigation", "to StatsScreen.route")
            StatsScreen() }
        composable(route = AboutScreen.route) {
            AboutUsScreen() }
        composable(
            route = TryAgainScreen.route
        ) {
            TryAgainScreen( navigator )
        }
        composable(
            route = SpaceWarScreen.route
        ) {
            Log.v("Navigation", "to SpaceWarScreen.route")
            LaunchSpaceWarGameScreen()
        }
        composable(route = Creator.route) { Creator(navigator) }
        composable(route = None.route) { Creator(navigator) }
        composable(route = Test.route) {
            Device.navigation.argStr = StrArgumentNav.serializeArgToInGame(
//                userShipTypeName = ShipType.Circle.info.name,
                userShipTypeName = ShipType.Square.info.name,
//                userShipTypeName = ShipType.Lasery.info.name,
                tryAgainStats = TryAgainStats.EMPTY_TRY_AGAIN_STATS,
            )

            LaunchSpaceWarGameScreen()
//            val stats = TryAgainStats(
//                wins = 2,
//                losses = 1,
//                streak = 1,
////                lastGame = GameResult.VICTORY,
//                gameResult = GameResult.DEFEAT,
//                shipName = ShipType.Square.info.name,
//                enemiesName = "unknown",
//                currentDate = MyDate.currentStr(),
//            )
//            TryAgainScreen( navigator)
        }
    }
}