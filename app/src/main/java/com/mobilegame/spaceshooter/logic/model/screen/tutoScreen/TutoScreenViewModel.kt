package com.mobilegame.spaceshooter.logic.model.screen.tutoScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.tutos.DuelTutoScreenUI

class TutoScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = DuelTutoScreenUI()
//    val gameVM = SpaceWarGameViewModel(application, ui.smartphoneEmulator.sizes.screenInner)
    val gameVM = SpaceWarGameViewModel(ShipType.Square, ShipType.Square,ui.smartphoneEmulator.sizes.screenInner, application)

    val backButtonPressureNavigationVM = PressureViewModel()
}