package com.mobilegame.spaceshooter.logic.model.screen

sealed class Screens(override val route: String, val key: Int, val backNav: Screens): NavigationDestination {
    object  MenuScreen: Screens("menu_screen", 0, None)
    object  MainScreen: Screens("main_screen", 1, None)
    object  BluetoothScreen: Screens("bluetooth_screen", 2, MainScreen)
    object  WifiScreen: Screens("wifi_screen", 3, MainScreen)
    object  DuelTutoScreen: Screens("duel_tuto_screen", 4, MainScreen)
    object  Creator: Screens("creator_screen", 5, MainScreen)

//    object  Unknown: Screens("unknown_screen", 42)
//    object  Loading: Screens("loading_screen", 69)
    object  None: Screens("none_screen", -1, None)
    object  Test: Screens("test_screen", -42, None)
}