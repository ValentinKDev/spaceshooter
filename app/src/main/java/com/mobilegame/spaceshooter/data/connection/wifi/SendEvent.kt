package com.mobilegame.spaceshooter.data.connection.wifi

import android.util.Log
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.iLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import java.io.PrintWriter

class SendEvent() {
    private val TAG: String = "SendEvent"

    fun toAll(infoList: List<WifiInfoService>, message: EventMessage, exception: WifiInfoService? = null) {
        infoList.forEach { _info ->
            exception?.let { _exception ->
                if (_exception != _info)
                    to(_info, message)
            } ?: let { to(_info, message) }
        }
    }

    fun to(info:WifiInfoService, message: EventMessage) {
        vLog(TAG, "to ${info.name}")
        send(info.writer, message)
    }


    private fun send(printWriter: PrintWriter, message: EventMessage) {
        Log.e(TAG, "send: ${message.toJson()}")
        printWriter.print(message.toJson() + EventMessage.MESSAGE_TERMINATOR)
        printWriter.flush()
    }
}
