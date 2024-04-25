package com.mobilegame.spaceshooter.logic.model.navigation

//sealed class Screens(override val route: String, val key: Int, val backNav: NavigationDestination, val forwardNav: NavigationDestination): NavigationDestination {
sealed class Screens(val route: String, val key: Int, val backNav: Screens?, val forwardNav: Screens?) {
    object  MenuScreen: Screens(
        route = "menu_screen",
        key = 0,
        backNav = MenuScreen,
        forwardNav = MainScreen
    )
    object  MainScreen: Screens(
        route = "main_screen",
        key = 1,
        backNav = MenuScreen,
        forwardNav = WifiScreen
    )
    object  BluetoothScreen: Screens(
        route = "bluetooth_screen",
        key = 2,
        backNav = MainScreen,
        forwardNav = ShipMenuScreen
    )
    object  WifiScreen: Screens(
        route = "wifi_screen",
        key = 3,
        backNav = MainScreen,
        forwardNav = ShipMenuScreen
    )
    object  ShipMenuScreen: Screens("space_ship_menu_screen", 4, MainScreen, SpaceWarScreen)
    object  TryAgainScreen: Screens("try_again_screen", 5, MainScreen, SpaceWarScreen)
    object  SpaceWarScreen: Screens("space_war_screen", 6, MainScreen, TryAgainScreen)
    object  DuelTutoScreen: Screens("duel_tuto_screen", 7, MainScreen, None)
    object  Creator: Screens("creator_screen", 8, MainScreen, None)
    object  StatsScreen: Screens("stats_screen", 9, MenuScreen, MenuScreen)
    object  AboutScreen: Screens("about_screen", 10, MenuScreen, MenuScreen)

    object  None: Screens("none_screen", -1, None, None)
    object  Test: Screens("test_screen", -42, None, None)

    companion object {
//        val MENU_SELECTION = listOf(MainScreen, StatsScreen, )
        fun find(routeToIdentify: String): Screens {
            return Screens::class.sealedSubclasses
                .firstOrNull { _it ->
                    _it.objectInstance?.route?.let { _route ->
                        routeToIdentify.contains(_route)
                    } == true
                }?.objectInstance ?: None
        }
    }
}