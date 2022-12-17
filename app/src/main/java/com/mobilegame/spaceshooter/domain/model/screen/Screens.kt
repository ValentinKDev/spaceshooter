package com.mobilegame.spaceshooter.domain.model.screen

sealed class Screens(override val route: String, val key: Int): NavigationDestination {
    object  MainScreen: Screens("main_screen", 0)
    object  Unknown: Screens("unknown_screen", 42)
    object  Loading: Screens("loading_screen", 69)
    object  None: Screens("none_screen", -1)
}