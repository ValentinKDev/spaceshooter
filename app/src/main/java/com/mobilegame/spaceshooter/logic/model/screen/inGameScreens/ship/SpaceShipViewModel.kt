package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.app.Application
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpaceShipViewModel(application: Application, ui: SpaceWarGameScreenUI, val type: ShipType) : ViewModel() {
    val motionVM = MotionsViewModel(
        context = application,
        ui = ui,
    )
    val ammoVM = MunitionsViewModel(motionVM, type)
    val lifeVM = LifeViewModel(motionVM, type)

//    private val _life = MutableStateFlow(100)
//    val life: StateFlow<Int> = _life.asStateFlow()
}