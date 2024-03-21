package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.tutos.DuelTutoScreenUI

//class LaunchDuelGameViewModel(application: Application): AndroidViewModel(application) {
//class LaunchDuelGameViewModel(shipType: ShipType, application: Application): AndroidViewModel(application) {
//class LaunchDuelGameViewModel(shipType: ShipType): AppCompatActivity() {
//class LaunchDuelGameViewModel(shipType: ShipType): ComponentActivity() {
class LaunchDuelGameViewModel(shipType: ShipType, context: Context): ComponentActivity() {
    val TAG = "LaunchDuelGameViewModel"
//    val ui = DuelTutoScreenUI()
//    val shipMenuVM = ShipMenuViewModel()
//    val gameVM = SpaceWarGameViewModel(application, Size(Device.metrics.width, Device.metrics.height))
    val gameVM = SpaceWarGameViewModel(shipType, context, Size(Device.metrics.width, Device.metrics.height))
    init {
        Log.i(TAG, "init: ")
//        val test = applicationContext
//        if (application != null) Log.i(TAG, "init: app not null")
//        else Log.e(TAG, "init: app null ")
    }
}