package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
//    private val _goToShipMenu = MutableStateFlow(false)
//    val goToShipMenu: StateFlow<Boolean> = _goToShipMenu.asStateFlow()
    private var goToShipMenu: Boolean = false

    init {
        eLog(TAG, "init")
        repo.initWifi(application)
        repo.initNetworkSearchAndDiscovery(application)

        viewModelScope.launch { withContext(Dispatchers.IO) {
            Device.wifi.listVisibleDevicesFlow.map { it.firstOrNull() }.collect {visibleDeviceState ->
                visibleDeviceState?.let {
                    when (it.state) {
                        PreparationState.ReadyToChooseShip -> {
//                        PreparationState.ReadyToPlay -> {
//                        PreparationState.ReadyToPlay -> {
                            if (pressureVM.full.value) {
                                eLog(TAG, "SPACE SHIP MENU")
//                                DeviceEventRepo().sendReadyToChooseShip()
//                                DeviceEventRepo().sendReadyToPlay()
                                chooseSpaceShip()
                                this.coroutineContext.job.cancel()
                            }
                        }
                        else -> {
//                                eLog(TAG, "collecting  Device.wifi.listVisibleDevicesFlow ${it.state}")
                        }
                    }
                }
            }
        } }
        viewModelScope.launch {
            pressureVM.full.collect { _full ->
                eLog(TAG, "collecting pressureVM.full $_full")
                if (_full) { pressureReadyToChooseSpaceShip() }
                else { pressureReleaseReadyToChooseSpaceShip() }
            }
        }
    }
    suspend private fun chooseSpaceShip() {
        goToShipMenu = true
        nav?.let { it.navig(Screens.SpaceWarScreen) }
//        DeviceEventRepo().sendDeviceInGame()
//        _chooseShip.emit(true)
    }

    fun nonNullNameTrigger() {
        Log.i(TAG, "nonNullNameTrigger: ")
        viewModelScope.launch(Dispatchers.IO) {
            connectionVM.hostAndSearch()
        }
    }
    fun pressureReadyToChooseSpaceShip() = viewModelScope.launch {
//        if (!goToShipMenu) {
        Log.e(TAG, "pressureReadyToChooseSpaceShip: ")
//            DeviceEventRepo().sendReadyToPlay()
        DeviceEventRepo().sendReadyToChooseShip()
//        }
    }
    fun pressureReleaseReadyToChooseSpaceShip()  = viewModelScope.launch {
//        if (!goToShipMenu) {
            Log.e(TAG, "pressureReleaseReadyToChooseSpaceShip: ",)
//            DeviceEventRepo().sendNotReadyToPlay()
            DeviceEventRepo().sendNotReadyToChooseShip()
//        }
    }

    //todo : is this better to store the navigation in the Device Data obj ?
    fun initNavigation(navigator: Navigator) {
        nav = navigator
    }
}
