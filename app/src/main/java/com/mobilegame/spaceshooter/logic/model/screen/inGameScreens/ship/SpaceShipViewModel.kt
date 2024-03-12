package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpaceShipViewModel(application: Application, val ui: SpaceWarGameScreenUI, val type: ShipType) : ViewModel() {
    val TAG = "SpaceShipViewModel"
    val motionVM = MotionsViewModel(
        context = application,
        ui = ui,
    )
    val ammoVM = MunitionsViewModel(motionVM, type)
    val lifeVM = LifeViewModel(motionVM, type)

    //todo create a flow in the motionVM to listen to in the spaceshipVM to change the life j
//    private val _life = MutableStateFlow(100)
    //todo create a VM to handle life
}