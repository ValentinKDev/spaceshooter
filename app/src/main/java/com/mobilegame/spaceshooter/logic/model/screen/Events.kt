package com.mobilegame.spaceshooter.logic.model.screen

import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object Events {
    private val _pairedDeviceEvent = MutableStateFlow(EventMessage.NONE)
    val pairedDeviceEvent: StateFlow<EventMessage> = _pairedDeviceEvent.asStateFlow()
    fun new(eventMessage: EventMessage) {
        _pairedDeviceEvent.value = eventMessage
    }
}