package com.mobilegame.spaceshooter.logic.model.screen

sealed class Screens(override val route: String, val key: Int): NavigationDestination {
    object  MainScreen: Screens("main_screen", 0)
    object  BluetoothScreen: Screens("bluetooth_screen", 1)
    object  WifiScreen: Screens("wifi_screen", 2)
    object  DuelTutoScreen: Screens("duel_tuto_screen", 3)

//    object  Unknown: Screens("unknown_screen", 42)
//    object  Loading: Screens("loading_screen", 69)
//    object  None: Screens("none_screen", -1)
}