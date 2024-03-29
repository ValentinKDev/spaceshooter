package com.mobilegame.spaceshooter.logic.model.navigation

sealed class Screens(override val route: String, val key: Int, val backNav: Screens):
    NavigationDestination {
    object  MenuScreen: Screens("menu_screen", 0, None)
    object  MainScreen: Screens("main_screen", 1, MenuScreen)
    object  BluetoothScreen: Screens("bluetooth_screen", 2, MainScreen)
    object  WifiScreen: Screens("wifi_screen", 3, MainScreen)
    object  ShipMenuScreen: Screens("space_ship_menu_screen", 4, MainScreen)
    object  SpaceWarScreen: Screens("space_war_screen", 5, MainScreen)
    object  DuelTutoScreen: Screens("duel_tuto_screen", 6, MainScreen)
    object  Creator: Screens("creator_screen", 7, MainScreen)
    object  StatsScreen: Screens("stats_screen", 8, MenuScreen)

    object  None: Screens("none_screen", -1, None)
    object  Test: Screens("test_screen", -42, None)
}