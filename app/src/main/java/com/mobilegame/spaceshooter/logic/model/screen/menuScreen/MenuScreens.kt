package com.mobilegame.spaceshooter.logic.model.screen.menuScreen

import com.mobilegame.spaceshooter.logic.model.navigation.Screens

enum class MenuScreens(val titleText: String, val screen: Screens) {
    Spacewars( "SPACEWARS", Screens.MainScreen),
    Data( "DATA", Screens.StatsScreen),
    AboutUs( "ABOUT US", Screens.AboutScreen),
}
