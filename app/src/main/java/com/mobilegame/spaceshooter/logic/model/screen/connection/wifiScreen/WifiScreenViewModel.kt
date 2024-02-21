package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.getConnectivityManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.Inet4Address
import java.net.NetworkInterface
import java.util.Formatter


class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "WifiScreenVM"
    val ui = WifiScreenUI()
    val pressureVM = PressureViewModel()
    val registerVM = RegisterDeviceViewModel(application, Screens.WifiScreen)
    val repo = DeviceWifiRepo()
    private var connectionVM: WifiConnectionViewModel = WifiConnectionViewModel()
//    private val deviceNameDataStore: String? = Device.data.name

    private val _deviceName = MutableStateFlow(Device.data.name)
    val deviceName: StateFlow<String?> = _deviceName.asStateFlow()

    init {
        eLog("WifiScreenVM", "init")
        repo.initWifi(application)
        repo.initNetworkSearchAndDiscovery(application)
//        deviceName.value?.let { nonNullNameTrigger() }
    }


    fun nonNullNameTrigger() {
        Log.i(TAG, "nonNullNameTrigger: ")
        viewModelScope.launch(Dispatchers.IO) {
            connectionVM.hostAndSearch()

//            connectionVM.start()
//            connectionVM.host()
//            connectionVM.searchForServers()
        }
    }
//    fun startConnecting() { connectionVM.host() }
//    fun newVisibleDeviceTrigger() {
//    Log.i(TAG, "newVisibleDeviceTrigger: ")
//    connectionVM.searchForServers()
//    }

//    fun refreshButtonClick() { connectionVM.refresh() }
    fun pressureReadyToChooseSpaceShip(navigator: Navigator) = viewModelScope.launch {
//    Log.e(TAG, "pressureReadyToChooseSpaceShip: ")
    }

//    fun back(navigator: Navigator): Job = viewModelScope.launch(Dispatchers.IO) {
//            navigator.navig(Screens.MainScreen)
//    }

//    fun playerReadyToChooseSpaceShip(): Job = viewModelScope.launch(Dispatchers.IO) {
//        if (connectionInfo.linkState.value == WifiLinkState.Connected) {
//            deviceName?.let { _name -> connectionVM?.let { _connectionVM ->
//                if (_connectionVM.isHosting()) {
//                    val launchGameEvenMessage = EventMessage(EventMessageType.LaunchGame.name, _name, "")
//                    SendEvent(connectionInfo, launchGameEvenMessage).toAllClients()
//                } else {
//                    val readyToChooseSapceShip = EventMessage(EventMessageType.ReadyToChooseSpaceShip.name, _name, "")
//                    SendEvent(connectionInfo, readyToChooseSapceShip).toServer()
//                    Communications
//                }
//            }}
//        }
//    }
}
