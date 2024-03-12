package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.connection.wifi.SendEvent
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.iLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "WifiScreenVM"
    val ui = WifiScreenUI()
    val pressureVM = PressureViewModel()
    val registerVM = RegisterDeviceViewModel(application, Screens.WifiScreen)
    val repo = DeviceWifiRepo()
    private var connectionVM: WifiConnectionViewModel = WifiConnectionViewModel()
    var nav: Navigator? = null

    private val _deviceName = MutableStateFlow(Device.data.name)
    val deviceName: StateFlow<String?> = _deviceName.asStateFlow()

    init {
        eLog(TAG, "init")
        repo.initWifi(application)
//        repo.setIpAddress()
        repo.initNetworkSearchAndDiscovery(application)
//        deviceName.value?.let { nonNullNameTrigger() }
//        viewModelScope.launch {
//            val checkVisibleDevice = flow {
//                while (test) {
//                    emit(Device.wifi.visibleDevices.value)
//                    delay(200)
//                }
//            }
//        }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Device.wifi.listVisibleDevicesFlow.map { it.firstOrNull() }.collect {visibleDeviceState ->
//                    eLog(TAG, "collecting  Device.wifi.listVisibleDevicesFlow ${it.firstOrNull()?.state}")
                    visibleDeviceState?.let {
//                        pressureVM.full.collect { _full ->
//                            eLog(TAG, "collecting pressureVM.full $_full")
//                        }
                        when (it.state) {
//                            PreparationState.Waiting -> {
////                                iLog(TAG, "collecting  Device.wifi.listVisibleDevicesFlow ${it.state}")
//                            }
                            PreparationState.ReadyToPlay -> {
                                if (pressureVM.full.value) {
                                    eLog(TAG, "START GAME")
//                                    startGame()
                                    DeviceEventRepo().sendReadyToPlay()
                                    startGame()
                                    this.coroutineContext.job.cancel()
                                }
                            }
                            else -> {
//                                eLog(TAG, "collecting  Device.wifi.listVisibleDevicesFlow ${it.state}")
                            }
                        }
                    }
                }
            }
        }
        viewModelScope.launch {
            pressureVM.full.collect { _full ->
                eLog(TAG, "collecting pressureVM.full $_full")
                if (_full) {
                    pressureReadyToChooseSpaceShip()
                }
                else {
                    pressureReleaseReadyToChooseSpaceShip()
                }
            }
//            checkVisibleDevice
//            Device.wifi.visibleDevices.collect {
//                eLog(TAG, "Device.wifi.visibleDevices.collect $it")
//            }
//            repo.getFlowListVisibleDevice().collect {
//
//            }
        }
    }

    fun nonNullNameTrigger() {
        Log.i(TAG, "nonNullNameTrigger: ")
        viewModelScope.launch(Dispatchers.IO) {
            connectionVM.hostAndSearch()
        }
    }
//    fun startConnecting() { connectionVM.host() }

//    fun refreshButtonClick() { connectionVM.refresh() }
    fun pressureReadyToChooseSpaceShip() = viewModelScope.launch {
        val fTAG = "pressureReadyToChooseSpaceShip"
        Log.e(TAG, "$fTAG: ")
//        if (repo.isVisibleDeviceReadyToPlay()) {
//            Log.e(TAG, "$fTAG: is Visible Device Ready To Play")
//        } else {
            DeviceEventRepo().sendReadyToPlay()
//        }
    }
    fun pressureReleaseReadyToChooseSpaceShip()  = viewModelScope.launch {
        val fTAG = "pressureReleaseReadyToChooseSpaceShip"
        Log.e(TAG, "$fTAG: ")
        DeviceEventRepo().sendNotReadyToPlay()
    }
    suspend private fun startGame() {
        DeviceEventRepo().sendDeviceInGame()
        nav?.navig(Screens.SpaceWarScreen)
    }

    //todo : is this better to store the navigation in the Device Data obj ?
    fun initNavigation(navigator: Navigator) {
        nav = navigator
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
