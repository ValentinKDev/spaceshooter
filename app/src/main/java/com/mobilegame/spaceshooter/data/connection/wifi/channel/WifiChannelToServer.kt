package com.mobilegame.spaceshooter.data.connection.wifi.channel

import android.util.Log
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.dto.EventMessageType
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.logic.model.screen.Events
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiConnectionInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket

class WifiChannelToServer(private val socket: Socket, private val info: WifiConnectionInfo) : WifiChannelService {
    private var isOpen = false
    val TAG = "ChannelToServer"

    private suspend fun read() = withContext(Dispatchers.IO) {
        var line: String?
        val reader: BufferedReader

        try {
            reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        } catch (e: IOException) {
            println("in or out failed")
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

                if (line.isEmpty()) {
                    Log.e(TAG, "read: ERROR line is empty")
                } else {

                    Log.d(TAG, "read: line $line")

                    val eventMessage = EventMessage.fromJson(line)
//                Log.d(TAG, "read: ${eventMessage.getType()}")

//                when (eventMessage.typeKey) {
                    when (EventMessageType.valueOf(eventMessage.type)) {
                        EventMessageType.DeviceLeaving, EventMessageType.SendGameData -> {
                            Events.new(eventMessage)
                        }
                        EventMessageType.ConnectedDevice -> {
                            info.newVisibleDevice(eventMessage.message)
                        }
                        EventMessageType.DisconnectedDevice -> {
                            info.removeVisibleDevice(eventMessage.message)
                        }
                        EventMessageType.Loose -> TODO()
                        EventMessageType.None -> TODO()
                        EventMessageType.SendDeviceName -> {
                            info.newVisibleDevice(eventMessage.sender)
                        }
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

    override suspend fun close() = withContext(Dispatchers.IO) {
        Log.e(TAG, "close")

        info.resetVisibleDevice()
        info.socket?.close()
        info.socket = null
        info.updateLinkStateTo(WifiLinkState.NotConnected)
        isOpen = false
    }
}