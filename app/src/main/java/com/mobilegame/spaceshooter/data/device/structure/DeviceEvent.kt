package com.mobilegame.spaceshooter.data.device.structure

import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DeviceEvent {
//    var incomingProjectile = MutableStateFlow(Shoot.UNDEFINED)
//    val incomingProjectile = SharedFlow<Shoot?>()

    var incomingProjectile: MutableSharedFlow<Shoot> = MutableSharedFlow()
    var dead: MutableSharedFlow<Boolean> = MutableSharedFlow()
    var gameOnPause: MutableSharedFlow<Boolean> = MutableSharedFlow()
}