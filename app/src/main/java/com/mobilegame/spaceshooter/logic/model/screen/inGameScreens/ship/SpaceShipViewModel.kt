package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.app.Application
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.games.DuelGameScreenUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpaceShipViewModel(application: Application, ui: DuelGameScreenUI) : ViewModel() {
    val type = ShipType.Default
    val motionVM = MotionsViewModel(
        context = application,
        ui = ui,
//        startPosition = ui.position.pCenterDp,
//        displaySizeDp = ui.sizes.displayDpDeltaBox,
    )
    val ammoVM = MunitionsViewModel(motionVM, type)

    private val _life = MutableStateFlow(100)
    val life: StateFlow<Int> = _life.asStateFlow()
}