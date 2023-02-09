package com.mobilegame.spaceshooter.data.device.structure

import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeviceEvent {
    val incoming = MutableStateFlow(EventMessage.NONE)
}