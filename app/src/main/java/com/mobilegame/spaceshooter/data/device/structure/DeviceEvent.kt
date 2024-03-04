package com.mobilegame.spaceshooter.data.device.structure

import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow

class DeviceEvent {
//    val incoming = MutableStateFlow(EventMessage.NONE)
    var isRunning = false
    val refreshInterval = 500L

//    val eventsFlow = flow<> {
//        while (isRunning) {
////            emit(1)
//            delay(refreshInterval)
//        }
//    }
}