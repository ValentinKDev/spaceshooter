package com.mobilegame.spaceshooter.logic.repository.device

import android.content.Context
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.connection.ConnectedDevice
import com.mobilegame.spaceshooter.utils.extensions.*
import kotlinx.coroutines.flow.update
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Collections


class DeviceWifiRepo() {
    val TAG = "DeviceWifiRepo"

//    fun getFlowListVisibleDevice(): MutableStateFlow<List<ConnectedDevice>> = Device.wifi.visibleDevices
    fun initWifi(context: Context) {
        Device.wifi.wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }
    fun initNetworkSearchAndDiscovery(context: Context) {
        Device.wifi.nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
    }
    fun getNsdManager(): NsdManager = Device.wifi.nsdManager
    fun getLinkState(): WifiLinkState = Device.wifi.linkState.value
//    fun isDeviceServer(): Boolean = Device.wifi.linkState.value == WifiLinkState.ConnectedAsServer
    fun isDeviceClient(): Boolean? = if ( Device.wifi.linkState.value == WifiLinkState.ConnectedAsClient ) true else null
    fun isDeviceServer(): Boolean? = if ( Device.wifi.channels.withServer.info != null ) true else null
    fun updateLinkStateTo(newState: WifiLinkState) {
        Device.wifi.linkState.update { newState }
        Log.w(TAG, "updateLinkStateTo: ${newState.name}")
    }
    fun addVisibleDevice(ip: InetAddress, name: String) {
        Log.w(TAG, "addVisibleDevice: name $name $ip", )
        Device.wifi.visibleDevices.addToValue(ConnectedDevice(name, ip))
    }
    fun addConnectedDevice(ip: InetAddress, name: String) {
        val tmpList = Device.wifi.listConnectedDevice.toMutableList()
        tmpList.add(ConnectedDevice(name, ip))
        Device.wifi.listConnectedDevice = tmpList.toList()
        Log.e(TAG, "addVisibleDevice: ${Device.wifi.listConnectedDevice}")
    }
    fun changeVisibleDevicePreparationStateTo(state: PreparationState) {
        Log.w(TAG, "changeVisibleDevicePreparationStateTo: ${state}")
        Device.wifi.visibleDevices.value.first().state = state
        val tmpList = Device.wifi.listConnectedDevice.toMutableList()
        tmpList.first().state = state
        Device.wifi.visibleDevices.value = tmpList.toList()
//        Device.wifi.listConnectedDevice.first().state = state
    }
    fun isVisibleDeviceReadyToPlay(): Boolean = Device.wifi.visibleDevices.value.first().state == PreparationState.ReadyToPlay
    fun resetVisibleDevice() { Device.wifi.visibleDevices.value = listOf() }
    fun removeVisibleDevice(ip: InetAddress) = Device.wifi.visibleDevices.value.find { it.ip == ip }?.let {
        Device.wifi.visibleDevices.removeToValue(it)
    }
//    fun getLocalIPAddress(): InetAddress? {
//        try {
//            val en = NetworkInterface.getNetworkInterfaces()
//            while (en.hasMoreElements()) {
//                val networkInterface = en.nextElement()
//                val enu = networkInterface.inetAddresses
//                while (enu.hasMoreElements()) {
//                    val inetAddress = enu.nextElement()
//                    return inetAddress
////                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
////                        return inetAddress.getHostAddress()
////                    }
//                }
//            }
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
//        return null
//    }
//    fun setInetAddress() {
//        Log.i(TAG, "setInetAddress()")
//        try {
//            val en = NetworkInterface.getNetworkInterfaces()
//            while (en.hasMoreElements()) {
//                val networkInterface = en.nextElement()
//                val enu = networkInterface.inetAddresses
//                while (enu.hasMoreElements()) {
//                    val inetAddress = enu.nextElement()
//                    Device.wifi.inetAddress = inetAddress
//                }
//            }
//        } catch (ex: Exception) {
//            Log.e(TAG, "setInetAddress() ${ex.message}")
//            ex.printStackTrace()
//        }

//        Device.wifi.wifiManager
//        val ip = Device.wifi.wifiManager.connectionInfo.ipAddress
//        val bytes: ByteArray = BigInteger.valueOf(ip.toLong()).toByteArray()
//        bytes.reverse()
//        val address = InetAddress.getByAddress(bytes)
//        Log.i(TAG, "setInetAddressTo: $address")
//        Device.wifi.inetAddress = address
//        Log.i(TAG, "setInetAddress() inet Address ${Device.wifi.inetAddress}")
//    }

    fun getLocalIp(): InetAddress = Device.wifi.inetAddress

    fun setIpAddress() {
        getIPAddress()?.let { Device.wifi.inetAddress = it }
    }
    fun getIPAddress(): InetAddress? {
        val fTAG = "getIpAddress"
        try {
            Log.i(TAG, fTAG)
            val interfaces: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs: List<InetAddress> = Collections.list(intf.inetAddresses)
                for (addr in addrs) {
                    Log.i(TAG, "$fTAG ${addr.hostAddress}")
                    if (!addr.isLoopbackAddress && addr.hostAddress?.indexOf(':')!! < 0) {
                        Log.i(TAG, "$fTAG Device.wifi.inetAddress ${addr}")
//                        Device.wifi.inetAddress = addr
                        return addr
                    }
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "$fTAG ERROR ${ex.message}")
        }
        return null
    }
}