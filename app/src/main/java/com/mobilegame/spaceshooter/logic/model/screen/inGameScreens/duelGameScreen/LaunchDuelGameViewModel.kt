package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType

class LaunchDuelGameViewModel(userShipType: ShipType, enemiesShipType: ShipType, context: Context): ComponentActivity() {
    val TAG = "LaunchDuelGameViewModel"
    val gameVM = SpaceWarGameViewModel(userShipType, enemiesShipType, Size(Device.metrics.width, Device.metrics.height), context)
    init { Log.i(TAG, "init: ") }
}