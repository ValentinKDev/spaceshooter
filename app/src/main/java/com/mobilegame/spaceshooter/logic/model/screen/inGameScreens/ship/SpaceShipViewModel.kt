package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI

//class SpaceShipViewModel(application: Application, ui: SpaceWarGameScreenUI, var type: ShipType) : ViewModel() {
class SpaceShipViewModel(
//    context: Context,
    application: Application,
    ui: SpaceWarGameScreenUI,
    var type: ShipType,
//    tryAgainStats: TryAgainStats
//) : ViewModel() {
) : AndroidViewModel(application) {
    private val TAG = "SpaceShipViewModel"
    val motionVM = MotionsViewModel(
//        context = application,
//        context = context,
        application = application,
        ui = ui,
    )
    val ammoVM = MunitionsViewModel(motionVM, type)
//    val lifeVM = LifeViewModel(motionVM, type, tryAgainStats)
    val lifeVM = LifeViewModel(motionVM, type)
    fun clear() {onCleared()}
    override fun onCleared() {
        Log.w(TAG, "onCleared: ")
        super.onCleared()
    }
}