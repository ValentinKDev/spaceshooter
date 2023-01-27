package com.mobilegame.spaceshooter.data.connection.wifi.channel

import com.mobilegame.spaceshooter.data.connection.wifi.WifiClient
import java.net.Socket

interface WifiChannelService {
    suspend fun open()
    suspend fun close()

    companion object {
        fun createChannelToServer(socket: Socket): WifiChannelService {
            return WifiChannelToServer(socket)
        }
        fun createChannelToClient(client: WifiClient, clientList: MutableList<WifiClient>): WifiChannelService {
            return WifiChannelToClients(client, clientList)
        }
    }
}