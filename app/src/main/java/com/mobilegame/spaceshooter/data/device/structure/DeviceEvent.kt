package com.mobilegame.spaceshooter.data.device.structure

import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import kotlinx.coroutines.flow.MutableSharedFlow

class DeviceEvent {
    var projectileFlow: MutableSharedFlow<Shoot> = MutableSharedFlow()
//    var producingProjectile: MutableSharedFlow<Shoot> = MutableSharedFlow()
    var dead: MutableSharedFlow<Boolean> = MutableSharedFlow()
//    var gameOnPause: MutableSharedFlow<Boolean> = MutableSharedFlow()
//    var gameOnPause: MutableStateFlow<Boolean> = mutableSharedFlow(false)
}
