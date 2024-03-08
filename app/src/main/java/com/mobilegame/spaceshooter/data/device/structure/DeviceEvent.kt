package com.mobilegame.spaceshooter.data.device.structure

import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.Shoot
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DeviceEvent {
//    var incomingProjectile = MutableStateFlow(Shoot.UNDEFINED)
//    val incomingProjectile = SharedFlow<Shoot?>()
    var incomingProjectile: MutableSharedFlow<Shoot> = MutableSharedFlow()
//    var isRunning = false
//    val refreshInterval = 500L

//    val eventsFlow = flow<> {
//        while (isRunning) {
////            emit(1)
//            delay(refreshInterval)
//        }
//    }
}