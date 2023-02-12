package com.mobilegame.spaceshooter.data.device.structure

import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.logic.model.screen.connection.ConnectedDevice
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiListeners
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel.WifiChannels
import kotlinx.coroutines.flow.MutableStateFlow
import java.net.InetAddress

class DeviceWifi {
    val networkSearchDiscoveryName = "SpaceShooterByWIFI"
    val type = "_SpaceShooterApp._tcp"
    lateinit var inetAddress: InetAddress
    lateinit var nsdManager: NsdManager
    lateinit var wifiManager: WifiManager
    var channels = WifiChannels()
    val listeners = WifiListeners()
    val playersNumber: MutableStateFlow<Int> = MutableStateFlow(2)
//    val visibleDevices: MutableStateFlow<List<WifiInfoService>> = MutableStateFlow(listOf())
//val visibleDevices: MutableStateFlow<Map<InetAddress, String>> = MutableStateFlow(mapOf())
//    val visibleDevices: MutableStateFlow<List<Pair<InetAddress, String>>> = MutableStateFlow(listOf())
    var linkState: MutableStateFlow<WifiLinkState> = MutableStateFlow(WifiLinkState.NotConnected)
    val visibleDevices: MutableStateFlow<List<ConnectedDevice>> = MutableStateFlow(listOf())
}
