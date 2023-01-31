package com.mobilegame.spaceshooter.data.connection.wifi.channel

import com.mobilegame.spaceshooter.data.connection.wifi.WifiClient
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiConnectionInfo
import java.net.Socket

interface WifiChannelService {
    suspend fun open()
    suspend fun close()

    companion object {
        fun createChannelToServer(info: WifiConnectionInfo): WifiChannelService {
            return WifiChannelToServer(info.socket!!, info)
        }
        fun createChannelToClient(client: WifiClient, info: WifiConnectionInfo): WifiChannelService {
            return WifiChannelToClients(client, info)
        }
    }
}