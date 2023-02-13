package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel

import android.util.Log
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiClient
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiServer
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class WifiChannel(
    var info: WifiInfoService? = null
) {
    var isOpen = false
    val TAG = "WifiChannel"
    val eventRepo = DeviceEventRepo()


    private suspend fun read() = info?.let { _info ->
        withContext(Dispatchers.IO) {
            val reader: BufferedReader

            try {
                reader = BufferedReader(InputStreamReader(_info.socket.getInputStream()))
            } catch (e: IOException) {
                Log.e(TAG, "ERROR BufferedReader IO")
                close()
                return@withContext
            }

            while (isOpen) {
                try {
                    reader.readLine() ?.let { _line ->
                        handleLine(_line)
                    } ?: let {
                        Log.e(TAG, "read: ERROR line null")
                        close()
                    }
                } catch (e: IOException) {
                    close()
                    return@withContext
                }
            }
        }
    }

    private fun handleLine(line: String) {
        if (line.isBlank() || line.isEmpty()) {
            Log.e(TAG, "read: ERROR line is empty")
        } else {
            Log.d(TAG, "read: line $line")
            val eventMessage = EventMessage.fromJson(line)
            if (info is WifiServer)
                eventRepo.handleEventFromServer(eventMessage)
            else
                eventRepo.handleEventFromClient(eventMessage, info as WifiClient)
        }
    }

    suspend fun open() {
        Log.w(TAG, "open: ")
        isOpen = true
        read()
    }

    fun close() {
        Log.e(TAG, "close")
        isOpen = false
    }
}
