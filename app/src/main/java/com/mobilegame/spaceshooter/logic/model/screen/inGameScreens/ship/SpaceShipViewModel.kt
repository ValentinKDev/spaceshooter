package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//class SpaceShipViewModel(application: Application, ui: SpaceWarGameScreenUI, var type: ShipType) : ViewModel() {
class SpaceShipViewModel(context: Context, ui: SpaceWarGameScreenUI, var type: ShipType) : ViewModel() {
    val motionVM = MotionsViewModel(
//        context = application,
        context = context,
        ui = ui,
    )
    val ammoVM = MunitionsViewModel(motionVM, type)
    val lifeVM = LifeViewModel(motionVM, type)
}