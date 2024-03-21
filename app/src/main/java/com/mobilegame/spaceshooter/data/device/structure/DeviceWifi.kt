package com.mobilegame.spaceshooter.data.device.structure

import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.util.Log
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.connection.wifi.info.WifiInfoService
import com.mobilegame.spaceshooter.logic.model.screen.connection.ConnectedDevice
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.WifiListeners
import com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen.channel.WifiChannels
import com.mobilegame.spaceshooter.utils.analyze.iLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
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
    var linkState: MutableStateFlow<WifiLinkState> = MutableStateFlow(WifiLinkState.NotConnected)
    val visibleDevices: MutableStateFlow<List<ConnectedDevice>> = MutableStateFlow(listOf())
//    val test: <List<ConnectedDevice>> = flow {  }
//    val visibleDevices: Flow<List<ConnectedDevice>> = MutableStateFlow(listOf())
    var listConnectedDevice: List<ConnectedDevice> = listOf()
//    var connectionO

    val listVisibleDevicesFlow = flow {
//        while (linkState.value != WifiLinkState.NotConnected) {
        //todo : find a way to put a condition in while loop : refer to GravityDataProducer file
        while (true) {
//            iLog("DeviceWifi", "list $listConnectedDevice")
            emit(listConnectedDevice)
            //todo: find a way to temporarly augement the frequency of this
            delay(200L)
        }
    }
}
