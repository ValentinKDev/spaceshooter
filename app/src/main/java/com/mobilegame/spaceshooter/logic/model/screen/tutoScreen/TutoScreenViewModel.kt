package com.mobilegame.spaceshooter.logic.model.screen.tutoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelInGameScreen.DuelInGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.tutos.DuelTutoScreenUI

class TutoScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = DuelTutoScreenUI()
    val gameVM = DuelInGameViewModel(application, ui.smartphoneEmulator.sizes.screenInner)

    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}