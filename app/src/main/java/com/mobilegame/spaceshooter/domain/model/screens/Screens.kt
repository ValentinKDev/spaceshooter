package com.mobilegame.spaceshooter.domain.model.screens

class Screens {
    sealed class Screens(override val route: String, val key: Int): NavigationDestination {
        object  MainMenu: Screens("main_menu_screen", 0)
        object  Unknown: Screens("unknown_screen", 42)
        object  Loading: Screens("loading_screen", 69)
        object  None: Screens("none_screen", -1)
    }
}