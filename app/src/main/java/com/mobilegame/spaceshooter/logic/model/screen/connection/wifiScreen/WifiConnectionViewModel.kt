package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithClientRepo
import com.mobilegame.spaceshooter.logic.repository.connection.WifiChannelsWithServerRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WifiConnectionViewModel(): ViewModel() {
    private val TAG = "WifiConnectionVM"
    val repo = DeviceWifiRepo()
    private val withClientRepo = WifiChannelsWithClientRepo()
    private val withServerRepo = WifiChannelsWithServerRepo()

    suspend fun hostAndSearch() {
        Log.i(TAG, "hostAndSearch: setInetAddress")
//        repo
        repo.setIpAddress()
        Log.i(TAG, "hostAndSearch: searchForServer")
        withServerRepo.searchForServer()
        Log.i(TAG, "hostAndSearch: updateLinkStateTo")
        repo.updateLinkStateTo(WifiLinkState.Connecting)
        delayUntilConnected()
        Log.i(TAG, "hostAndSearch: stopSearching")
        withServerRepo.stopSearching()

        Log.i(TAG, "hostAndSearch: getLinkState ${repo.getLinkState()}")
        when (repo.getLinkState()) {
            WifiLinkState.ConnectedAsClient -> withServerRepo.openChannel()
//            WifiLinkState.Connecting -> withClientRepo.startHostingNewClients()
            WifiLinkState.Connecting -> withClientRepo.startHostingNewClients()
            WifiLinkState.NotConnected -> Log.e(TAG, "hostAndSearch: ${repo.getLinkState().name}", )
            WifiLinkState.NoConnection -> Log.e(TAG, "hostAndSearch: ${repo.getLinkState().name}", )
        }
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
}