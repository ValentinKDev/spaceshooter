package com.mobilegame.spaceshooter.logic.repository.connection

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiServer
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import java.io.IOException
import java.io.PrintWriter
import java.net.Socket
import java.net.UnknownHostException

class WifiChannelsWithServerRepo() {
    val TAG = "WifiChannelsToServerRepo"
    private val repo = DeviceWifiRepo()
    fun getChannel() = Device.wifi.channels.withServer
    fun getListeners() = Device.wifi.channels.listeners

    fun addServer(serviceInfo: NsdServiceInfo) = getChannel().info?.socket?.let {
        Log.e(TAG, "addServer: socket already connected")
    } ?: run {
        try {
            val socket = Socket(serviceInfo.host, serviceInfo.port)
            val writer = PrintWriter(socket.getOutputStream())
            val wifiServer = WifiServer("", PreparationState.Waiting, socket, writer)
            getChannel().info = wifiServer
            Log.i(TAG, "addServer: addServer")
            Log.i(TAG, "addServer: addServer inetAddress ${socket.inetAddress}")
            Log.i(TAG, "addServer: addServer serviceInfo.host ${serviceInfo.host}")
            Log.i(TAG, "addServer: addServer Device.wifi.inetAddress ${serviceInfo.host}")
//            repo.updateLinkStateTo(WifiLinkState.Connected)
            repo.updateLinkStateTo(WifiLinkState.ConnectedAsClient)
            DeviceEventRepo().sendNameToServer()
//            DeviceEventRepo().sendNameToServer(socket.localAddress)
        } catch (e: UnknownHostException) {
            Log.e(TAG, "addServer: Unknown host. ${e.localizedMessage}")
        } catch (e: IOException) {
            Log.e(TAG, "addServer: Failed to create writer. ${e.localizedMessage}")
        }
    }

    fun searchForServer()  {
        Log.i(TAG, "searchAllServers: ")
        repo.getNsdManager().discoverServices(
            Device.wifi.type,
            NsdManager.PROTOCOL_DNS_SD,
            getListeners().discoveryListener
        )
    }

    fun stopSearching() { getListeners().stopDiscovery() }

    suspend fun openChannel() {
        getChannel().info?.let { getChannel().open() }
    }
}