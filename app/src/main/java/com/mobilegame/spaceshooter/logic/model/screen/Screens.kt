package com.mobilegame.spaceshooter.logic.model.screen

sealed class Screens(override val route: String, val key: Int, val backNav: Screens): NavigationDestination {
    object  MainScreen: Screens("main_screen", 0, None)
    object  BluetoothScreen: Screens("bluetooth_screen", 1, MainScreen)
    object  WifiScreen: Screens("wifi_screen", 2, MainScreen)
    object  DuelTutoScreen: Screens("duel_tuto_screen", 3, MainScreen)
    object  Creator: Screens("creator_screen", 4, MainScreen)

//    object  Unknown: Screens("unknown_screen", 42)
//    object  Loading: Screens("loading_screen", 69)
    object  None: Screens("none_screen", -1, None)
}