package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel

import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiListeners
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

class WifiChannels {
    val TAG = "WifiChannels"
    var serverSocket: ServerSocket? = null
//    var serverPrintWriter: InetAddress? = null
    val withClients: MutableList<WifiChannel> = mutableListOf()
    val withServer = WifiChannel()
    val listeners = WifiListeners()
}