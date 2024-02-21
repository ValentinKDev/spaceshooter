package com.mobilegame.spaceshooter.logic.repository.device

import android.content.Context
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.connection.ConnectedDevice
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.*
import kotlinx.coroutines.flow.update
import java.math.BigInteger
import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface


class DeviceWifiRepo() {
    val TAG = "DeviceWifiRepo"

    fun initWifi(context: Context) {
        Device.wifi.wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }
    fun initNetworkSearchAndDiscovery(context: Context) {
        Device.wifi.nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    }
    fun getNsdManager(): NsdManager = Device.wifi.nsdManager
    fun getLinkState(): WifiLinkState = Device.wifi.linkState.value
    fun updateLinkStateTo(newState: WifiLinkState) {
        Device.wifi.linkState.update { newState }
        Log.w(TAG, "updateLinkStateTo: ${newState.name}")
    }
    fun addVisibleDevice(ip: InetAddress, name: String) {
        Log.w(TAG, "addVisibleDevice: ${name}")
        Device.wifi.visibleDevices.addToValue(ConnectedDevice(name, ip))
    }
    fun resetVisibleDevice() { Device.wifi.visibleDevices.value = listOf() }
    fun removeVisibleDevice(ip: InetAddress) = Device.wifi.visibleDevices.value.find { it.ip == ip }?.let {
        Device.wifi.visibleDevices.removeToValue(it)
    }
    fun getLocalIPAddress(): InetAddress? {
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val networkInterface = en.nextElement()
                val enu = networkInterface.inetAddresses
                while (enu.hasMoreElements()) {
                    val inetAddress = enu.nextElement()
                    return inetAddress
//                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
//                        return inetAddress.getHostAddress()
//                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
    fun setInetAddress() {
        Log.i(TAG, "setInetAddress()")
        val address: InetAddress? = getLocalIPAddress()
        address?.let {
            Device.wifi.inetAddress = it
        } ?: { eLog(TAG, "setInetAddress() ERROR inetAddress") }
        Log.i(TAG, "setInetAddress() ${Device.wifi.inetAddress}")
    }

    fun getLocalIp(): InetAddress = Device.wifi.inetAddress

//    var context = requireContext().applicationContext
//    var wm = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
//    var ip: String = Formatter.formatIpAddress(wm.connectionInfo.ipAddress)
//    fun updateConnectedClientName(inetAddress: InetAddress?, sender: String) {
//
//    }

//    fun newSocket(socket: Socket) = Device.wifi.socket?.let {
//        Log.e(TAG, "newSocket: ERROR socket already ${Device.wifi.socket}")
//    } ?: run { Device.wifi.socket = socket }
//    private fun closeSocket() = Device.wifi.socket?.let {
//        it.close()
//        Device.wifi.socket = null
//    }
//    fun newWriter() = Device.wifi.socket?.let { Device.wifi.writer = PrintWriter(it.getOutputStream()) }
//    fun getConnectedClient(name: String): WifiClient? = Device.wifi.connectedClients.find { it.name == name }
//    fun getConnectedClient(wifiClient: WifiClient): WifiClient? = Device.wifi.connectedClients.find { it == wifiClient }
//    fun removeAllConnectedClients() {
//        Device.wifi.connectedClients.forEach { removeConnectedClient(it) }
//        Device.wifi.connectedClients = listOf()
//    }
//    fun removeConnectedClient(wifiClient: WifiClient) {
//        getConnectedClient(wifiClient)?.let {
//            disconnectAndRemoveClient(it)
//        }
//    }
//    private fun disconnectAndRemoveClient(client: WifiClient) {
//        client.socket.close()
//        client.writer.close()
//        Device.wifi.connectedClients.remove(client)
//    }
//    fun newVisibleDevice(newDeviceName: String) {
//        Log.e(TAG, "newVisibleDevice: \"$newDeviceName\"")
//        Device.wifi.visibleClients.addToValue(newDeviceName)
//    }
//    fun removeVisibleDevice(deviceName: String) {
//        Log.e(TAG, "lostVisibleDevice: \"$deviceName\"")
//        Device.wifi.visibleClients.removeToValue(deviceName)
//    }
//    suspend fun openNewChannelToServer() = Device.wifi.socket?.let { _socket ->
//        val channel = WifiChannelService.createChannelToServer(_socket)
//        Device.wifi.channelToServer?.close() ?: let { Device.wifi.channelToServer = channel as WifiChannelToServer }
//        channel.open()
//    }
//    fun closeChannelToServer() {
//        Device.wifi.channelToServer?.close()
//        resetVisibleDevice()
//    }
//    fun closeServerConnection() {
//        closeChannelToServer()
//        resetVisibleDevice()
//        closeSocket()
//        updateLinkStateTo(WifiLinkState.NotConnected)
//    }
//    fun restart() {
//        Device.wifi.serverSocket?.let { stopHosting() }
//        updateLinkStateTo(WifiLinkState.NotConnected)
//    }
//    suspend fun startHosting() {
//        Device.wifi.channels.toClientsRepo.startHostingNewClients()
//    }

}