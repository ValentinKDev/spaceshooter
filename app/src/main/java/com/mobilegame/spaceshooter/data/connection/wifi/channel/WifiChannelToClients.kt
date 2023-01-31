package com.mobilegame.spaceshooter.data.connection.wifi.channel

import android.util.Log
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.data.connection.wifi.WifiClient
import com.mobilegame.spaceshooter.logic.model.screen.Events
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.SendEvent
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiConnectionInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class WifiChannelToClients(private val client: WifiClient, private val info: WifiConnectionInfo) : WifiChannelService {
    var isOpen = false
    val TAG = "ChannelToClient"

    private suspend fun read() = withContext(Dispatchers.IO) {
        var line: String?
        val reader: BufferedReader

        try {
            reader = BufferedReader(InputStreamReader(client.socket.getInputStream()))
        } catch (e: IOException) {
            Log.e(TAG, "ERROR BufferedReader IO")
            close()
            return@withContext
        }

        while (isOpen) {
            try {
                line = reader.readLine()

                line ?: let {
                    Log.e(TAG, "read: ERROR line null")
                    close()
                }

                if (line.isBlank() || line.isEmpty()) {
                    Log.e(TAG, "read: ERROR line is empty")
                } else {
                    Log.d(TAG, "read: line $line")
                    val eventMessage = EventMessage.fromJson(line)

                    when (EventMessageType.valueOf(eventMessage.type)) {
                        EventMessageType.SendDeviceName -> {
                            info.connectedClients.find { it.socket.inetAddress == client.socket.inetAddress }?.name = eventMessage.sender
                            info.newVisibleDevice(eventMessage.sender)
                            Log.d(TAG, "read: list ${info.connectedClients.size} ${info.connectedClients.map { it.name }} / list ${info.visibleDeviceNameList.value.size}")

                            val serverNameEventMessage = EventMessage(EventMessageType.SendDeviceName.name, info.deviceName, "serverName")
                            SendEvent(info, serverNameEventMessage).toClient(client)

                            val connectedDeviceEventMessage = EventMessage(
                                type = EventMessageType.ConnectedDevice.name,
                                sender = info.deviceName,
                                message = eventMessage.sender
                            )
                            SendEvent(info, connectedDeviceEventMessage).toAllClients(exception = client)
                        }
                        EventMessageType.DeviceLeaving -> {
                            Events.new(eventMessage)
                            SendEvent(info, eventMessage).toAllClients(exception = client)
                        }
                        EventMessageType.SendGameData -> {
                            Events.new(eventMessage)
                            SendEvent(info, eventMessage).toAllClients(exception = client)
                        }
                        EventMessageType.ConnectedDevice -> { }
                        EventMessageType.DisconnectedDevice -> { }
                        EventMessageType.Loose -> { }
                        EventMessageType.None -> { }
                    }
                }
            } catch (e: IOException) {
                close()
                return@withContext
            }
        }
    }

    override suspend fun open() {
        withContext(Dispatchers.IO) {
            isOpen = true
            read()
        }
    }

    override suspend fun close() {
        Log.e(TAG, "close")
        if (info.connectedClients.remove(client)) {
            val disconnectedDeviceEventMessage = EventMessage(
                type = EventMessageType.DisconnectedDevice.name,
                sender = info.deviceName,
                message = client.name
            )
            SendEvent(info, disconnectedDeviceEventMessage).toAllClients(exception = client)
            info.removeVisibleDevice(client.name)
        } else { Log.e(TAG, "ERROR : close a channel to an non connected client") }
        isOpen = false
    }
}
