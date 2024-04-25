package com.mobilegame.spaceshooter.logic.model.screen.aboutScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.screens.aboutScreen.AboutUsScreenUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackButtonUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI

class AboutScreenViewModel(): ViewModel() {
    private val TAG = "AboutScreenViewModel"
    val ui = AboutUsScreenUI()
    val backNavHandler = PressureHandler(Screens.MenuScreen)
    val templateUI = TemplateUI()
    val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    val text2 = "This game is based on the game \"DUAL\" available on the PlayStore and the AppStore\n"

    init { Log.i(TAG, "init: ") }
}