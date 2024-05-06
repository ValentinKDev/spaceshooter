package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithClientRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithServerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

//class WifiConnectionViewModel(): ViewModel() {
//class WifiConnection(): ViewModel() {
class WifiConnection() {
    private val TAG = "WifiConnectionVM"
    val repo = DeviceWifiRepo()
    private val withClientRepo = WifiChannelsWithClientRepo()
    private val withServerRepo = WifiChannelsWithServerRepo()

    fun hostAndSearch() = runBlocking(Dispatchers.IO) {
//    fun hostAndSearch() = viewModelScope.launch(Dispatchers.IO) {
        Log.i(TAG, "hostAndSearch: setInetAddress")
        repo.setIpAddress()
        repo.setLinkState(WifiLinkState.ConnectionStart)
        val handle = async { Device.wifi.linkState.collect {
            Log.d(TAG, "hostAndSearch: collect \n\n $it")
            when (it) {
                WifiLinkState.ConnectionStart -> {
                    withServerRepo.searchForServer()
//                    repo.setLinkState(WifiLinkState.RegisteredAsServer)
                }
                WifiLinkState.RegisteredAsServer -> {
//                    while (Device.wifi.visibleDevices.value.isEmpty()) {
                    withClientRepo.startHostingNewClients()
//                    }
//                    repo.setLinkState(WifiLinkState.RegisteredAsServerAndClient)
                }
                WifiLinkState.RegisteredAsServerAndClient -> {
                    Log.e(TAG, "hostAndSearch: $it")
                }
                WifiLinkState.ConnectedAsServer -> {}
                WifiLinkState.ConnectedAsClient -> {}
                WifiLinkState.Connecting -> {}
                WifiLinkState.NotConnected -> {}
                WifiLinkState.NoConnection -> {}
            }
        }}
        delay(3000)
        Log.e(TAG, "hostAndSearch: client list ${Device.wifi.visibleDevices.value.size}")
        if (Device.wifi.visibleDevices.value.isEmpty()) {
            withClientRepo.startHostingNewClients()
        }


//        while (repo.getLinkState() != WifiLinkState.RegisteredAsServerAndClient) {
//            when (repo.getLinkState()) {
//                WifiLinkState.ConnectionStart -> {
//                    withClientRepo.startHostingNewClients()
//                    repo.setLinkState(WifiLinkState.RegisteredAsServer)
//                }
//                WifiLinkState.RegisteredAsServer -> {
//                    withServerRepo.searchForServer()
//                    repo.setLinkState(WifiLinkState.RegisteredAsServer)
//                }
//                WifiLinkState.RegisteredAsServerAndClient -> TODO()
//
//                WifiLinkState.ConnectedAsServer -> TODO()
//                WifiLinkState.ConnectedAsClient -> TODO()
//                WifiLinkState.Connecting -> TODO()
//                WifiLinkState.NotConnected -> TODO()
//                WifiLinkState.NoConnection -> TODO()
//            }
//            delay(delayNumber)
//        }
//        val server = async { withClientRepo.startHostingNewClients() }
//        val client = async {
//            val timerMax = 40000L
//            val delayNumber = 1000L
//            var count = 0L
//             while (count < timerMax) {
//                 Log.i(TAG, "hostAndSearch: loop $count")
////            withServerRepo.stopSearching()
//                 withServerRepo.searchForServer()
//                 delay(delayNumber)
//                 count += delayNumber
//             }
//        }

//        val loop = async { withServerRepo.searchForServer() }
//        while (count > timerMax) {
//
//        }
//        if (Device.data.name == "MITEN") withClientRepo.startHostingNewClients()
//        else withServerRepo.searchForServer()

//        repo.setIpAddress()
//        Log.i(TAG, "hostAndSearch: searchForServer")
//        withServerRepo.searchForServer()
//        Log.i(TAG, "hostAndSearch: updateLinkStateTo")
//        repo.updateLinkStateTo(WifiLinkState.Connecting)
//        delayUntilConnected()
//        Log.i(TAG, "hostAndSearch: stopSearching")
//        withServerRepo.stopSearching()
//
//        Log.i(TAG, "hostAndSearch: getLinkState ${repo.getLinkState()}")
//        when (repo.getLinkState()) {
//            WifiLinkState.ConnectedAsClient -> withServerRepo.openChannel()
//            WifiLinkState.Connecting -> { withClientRepo.startHostingNewClients() }
//            WifiLinkState.NotConnected -> Log.e(TAG, "hostAndSearch: ${repo.getLinkState().name}", )
//            WifiLinkState.NoConnection -> Log.e(TAG, "hostAndSearch: ${repo.getLinkState().name}", )
//            else -> { Log.e(TAG, "hostAndSearch: ERROR else branch") }
//        }
    }

    private suspend fun delayUntilConnected() {
        Log.i(TAG, "delayUntilConnected")
        var count = 0
//        while (Device.wifi.linkState == WifiLinkState.Connecting && count < 3) {
        while (repo.getLinkState() == WifiLinkState.Connecting && count < 3) {
            Log.i(TAG, "delayUntilConnected $count")
            delay(250L)
            count += 1
        }
    }

//    private fun startSearching() {
//        Log.i(TAG, "startSearching")
//        Device.wifi.nsdManager.discoverServices(
//            Device.wifi.type,
//            NsdManager.PROTOCOL_DNS_SD,
//            Device.wifi.listeners.discoveryListener
//        )
//    }

//    private suspend fun startListenServer() {
//        Log.e(TAG, "start listen server ")
//        //todo: restart if error ? Important bug connection
//        Device.wifi.socket?.let {
//            repo.openNewChannelToServer()
//        } ?: Log.e(TAG, "startListenServer : ERROR info.socket null cannot create a channel to server")
//    }
//
//    fun stopSearching() {
//        Log.i(TAG, "stopSearching")
//        try { repo.stopDiscovery() }
//        catch (e: IllegalArgumentException) {
//            Log.i("nsdManager", "stopSearching: discoveryListener not registered")
//        }
//    }
//    fun clear() {onCleared()}
//    override fun onCleared() {
//        Log.i(TAG, "onCleared: ")
//        super.onCleared()
//    }
}