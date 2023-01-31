package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.wifi.WifiLinkState
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "WifiScreenVM"
    val registerVM = RegisterDeviceViewModel(application, Screens.WifiScreen)
    val ui = WifiScreenUI()
    private val deviceNameDataStore = DataStoreService.DeviceName(application)
    var connectionInfo = WifiConnectionInfo()
    private var connectionVM: WifiConnectionViewModel? = null
    var deviceName: String? = null

    init {
        deviceName = getDeviceNameFromDataStore()
        deviceName?.let {
            connectionInfo = WifiConnectionInfo()
            connectionInfo.init(application, it)
            connectionVM = WifiConnectionViewModel(connectionInfo)
            refreshButtonClick()
        } ?: let { Log.e(TAG, "init: ERROR deviceName Null") }
    }

    private fun getDeviceNameFromDataStore(): String? = runBlocking {
         deviceNameDataStore.getString(DataStoreNameProvider.DeviceName.key)
    }

    fun refreshButtonClick() {
        connectionVM?.let {
            when (connectionInfo.linkState.value) {
                WifiLinkState.Connected-> {
                    if (it.isHosting())
                        it.stopHosting()
                    else {
                        it.stopSearching()
                        connectionInfo.socket?.close()
                        connectionInfo.socket = null
                    }
                    connectionInfo.updateLinkStateTo(WifiLinkState.NotConnected)
                }
                WifiLinkState.NotConnected -> {
                    it.start()
                }
                WifiLinkState.Connecting -> {
                    Log.e(TAG , "ERROR linkState.value == LinkStates.Connecting")
                }
            }
        }
    }

    fun back(navigator: Navigator): Job = viewModelScope.launch(Dispatchers.IO) {
            navigator.navig(Screens.MainScreen)
    }
}
