package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel

import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiListeners
import java.net.ServerSocket

class WifiChannels {
    val TAG = "WifiChannels"
    var serverSocket: ServerSocket? = null
    val withClients: MutableList<WifiChannel> = mutableListOf()
    val withServer = WifiChannel()
    val listeners = WifiListeners()
}