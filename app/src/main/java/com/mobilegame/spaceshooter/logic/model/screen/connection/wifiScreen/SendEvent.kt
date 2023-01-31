package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.util.Log
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.wifi.WifiClient
import java.io.PrintWriter

class SendEvent(private val info: WifiConnectionInfo, private val message: EventMessage) {
    private val TAG: String = "SendEvent"

    fun toAllClients(exception: WifiClient? = null) {
        info.connectedClients.forEach { _client ->
            exception?.let { _exception ->
                if (_exception != _client) {
                    send(_client.writer)
                }
            } ?: let {
                send(_client.writer)
            }
        }
    }

    fun toServer() {
        info.socket?.let {
            info.writer?.let { _writer -> send(_writer) }
        }
    }

    fun toClient(client: WifiClient) {
        send(client.writer)
    }

    private fun send(printWriter: PrintWriter) {
        Log.e(TAG, "send: ${message.toJson()}")
        printWriter.print(message.toJson() + EventMessage.MESSAGE_TERMINATOR)
        printWriter.flush()
    }
}
