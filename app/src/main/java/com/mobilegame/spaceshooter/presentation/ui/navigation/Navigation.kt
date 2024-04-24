package com.mobilegame.spaceshooter.presentation.ui.navigation

import ShipMenuViewModel
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.Screens.*
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainViewModel
import com.mobilegame.spaceshooter.presentation.ui.screens.Creator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.bluetoothScreen.BluetoothScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.LaunchSpaceWarGameScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen.ShipMenuScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.MainScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.menuScreen.MenuScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.stats.StatsScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.tryAgainScreen.TryAgainScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.tutoScreens.duelTutoScreen.DuelTutoScreen
import com.mobilegame.spaceshooter.presentation.ui.screens.wifiScreen.WifiScreen
import com.mobilegame.spaceshooter.utils.analyze.verbalLog
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onSubscription

@Composable
fun Navigation(navigator: Navigator) {
    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(true) {
        Log.e("Navigation", "Start")
//        navigator.des.onEach {
//        navigator.des.onEach {
        navigator.des.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
//        startDestination = Screens.MainScreen.route,
        startDestination = Screens.MenuScreen.route,
//        startDestination = Screens.StatsScreen.route,
//        startDestination = Screens.WifiScreen.route,
//        startDestination = Screens.DuelTutoScreen.route,
//        startDestination = Screens.SpaceWarScreen.route,
//        startDestination = Screens.Creator.route,
//        startDestination = Screens.Test.route,
    ) {
        composable(route = Screens.MenuScreen.route) { MenuScreen(navigator) }
        composable(route = Screens.MainScreen.route) { MainScreen(navigator) }
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
            StatsScreen(navigator) }
        composable(
            route = Screens.TryAgainScreen.route.plus("/{${StrArgumentNav.ARG_KEY_TRY_AGAIN}}"),
            arguments = listOf( navArgument(StrArgumentNav.ARG_KEY_TRY_AGAIN) {type = NavType.StringType})
        ) { entry ->
            Log.v("Navigation", "to Screens.TryAgain.route")
            entry.arguments?.getString(StrArgumentNav.ARG_KEY_TRY_AGAIN)?.let { arg ->
                val stats = StrArgumentNav.deserializeArgToTryAgain(arg)
                TryAgainScreen( stats, navigator )
//                TryAgainScreen( stats, navigator, ShipMenuViewModel())
            }
        }
        composable(
            route = Screens.SpaceWarScreen.route .plus("/{${StrArgumentNav.ARG_KEY_IN_GAME}}"),
            arguments = listOf (navArgument(StrArgumentNav.ARG_KEY_IN_GAME) {type = NavType.StringType})
        ) {entry ->
            Log.v("Navigation", "to Screens.SpaceWarScreen.route")
            entry.arguments?.getString(StrArgumentNav.ARG_KEY_IN_GAME)?.let { arg ->
                Log.v("Navigation", "arg $arg")
                val infoList: Pair<ShipType, TryAgainStats> = StrArgumentNav.deserializeArgToInGame(arg)
                LaunchSpaceWarGameScreen(
                    LaunchDuelGameViewModel(
                        userShipType = infoList.first,
                        tryAgainStats = infoList.second,
                        context = context,
                    ),
                    navigator
                )
            }
        }
        composable(route = Screens.Creator.route) { Creator(navigator) }
        composable(route = Screens.Test.route) {
            val stats = TryAgainStats(
                wins = 2,
                losses = 1,
                streak = 1,
//                lastGame = GameResult.VICTORY,
                lastGame = GameResult.DEFEAT,
                lastShipName = ShipType.Square.info.name
            )
//            TryAgainScreen( stats, navigator)
            TryAgainScreen( stats, navigator, ShipMenuViewModel())
//            LaunchSpaceWarGameScreen(
//                LaunchDuelGameViewModel(
////                    userShipType = ShipType.Circle,
//                    userShipType = ShipType.Square,
//                    tryAgainStats = TryAgainStats.EMPTY_TRY_AGAIN_STATS,
//                    context = context),
//                navigator
//            )
        }
    }
}
//@Composable
//fun Navigation(navigator: Navigator) {
//    val context = LocalContext.current
//    var repeatNavigation = true
//    val route: String by remember { navigator.des }.collectAsState(initial = MainScreen.route)
//    var screen: Screens = None
//    LaunchedEffect(route) {
//        Log.e("Navigation", "Navigation: route $route")
//        screen = when (route) {
//            MainScreen.route -> {MainScreen}
//            MenuScreen.route -> {MenuScreen}
//            WifiScreen.route -> {WifiScreen}
//            else -> {Test}
//        }
//    }
//    when (screen) {
//        MainScreen -> { MainScreen(navigator = navigator) }
//        MenuScreen-> { MenuScreen(navigator = navigator) }
//        WifiScreen-> { WifiScreen(navigator = navigator) }
//        BluetoothScreen -> TODO()
//        Creator -> TODO()
//        DuelTutoScreen -> TODO()
//        None -> TODO()
//        ShipMenuScreen -> TODO()
//        SpaceWarScreen -> TODO()
//        StatsScreen -> TODO()
//        Test -> TODO()
//        TryAgainScreen -> TODO()
//    }
//
////    LaunchedEffect(true) {
////        Log.e("Navigation", "Start")
////        navigator.des.onEach {
////            navController.navigate(it)
////            repeatNavigation = false
////            Log.e("Navigation", "$repeatNavigation")
////        }.launchIn(this)
////    }
//
////    NavHost(
////        navController = navController,
////        startDestination = Screens.MainScreen.route,
////    ) {
//}