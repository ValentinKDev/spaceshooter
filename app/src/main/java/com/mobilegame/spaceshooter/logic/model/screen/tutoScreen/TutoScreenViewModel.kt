package com.mobilegame.spaceshooter.logic.model.screen.tutoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.tutos.DuelTutoScreenUI

class TutoScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = DuelTutoScreenUI()
    val gameVM = SpaceWarGameViewModel(application, ui.smartphoneEmulator.sizes.screenInner)

    val backButtonPressureNavigationVM = PressureViewModel()
}