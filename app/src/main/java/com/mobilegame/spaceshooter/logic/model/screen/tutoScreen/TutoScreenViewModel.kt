package com.mobilegame.spaceshooter.logic.model.screen.tutoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.data.AccelerometerListener
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreen.InGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.tutos.DuelTutoScreenUI
import kotlinx.coroutines.runBlocking

class TutoScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = DuelTutoScreenUI
    val gameVM = InGameViewModel(application, ui.smartphoneEmulator.sizes.screenInner)

    val backButtonPressureNavigationVM = PressureNavigationViewModel().create(Screens.MainScreen)
}