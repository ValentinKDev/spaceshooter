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
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.repository.device.DeviceWifiRepo
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import com.mobilegame.spaceshooter.presentation.ui.navigation.StrArgumentNav
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map


class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val TAG = "WifiScreenVM"
    val ui = WifiScreenUI()
    val pressureHandler = PressureHandler(null)
    val registerVM = RegisterDeviceViewModel(application, Screens.WifiScreen)
    val repo = DeviceWifiRepo()
    private var connectionVM: WifiConnectionViewModel = WifiConnectionViewModel()
    val backNavScreen: Screens = Screens.MenuScreen
    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean> = _navigate.asStateFlow()

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
            Device.wifi.listVisibleDevicesFlow.map {
                Log.i(TAG, "list collected $it")
                it.firstOrNull() }.collect { visibleDeviceState ->
                visibleDeviceState?.let {
                    when (it.state) {
                        PreparationState.ReadyToChooseShip -> {
//                        PreparationState.ReadyToPlay -> {
//                        PreparationState.ReadyToPlay -> {
                            if (pressureHandler.full.value) {
//                                eLog(TAG, "SPACE SHIP MENU")
//                                DeviceEventRepo().sendReadyToChooseShip()
//                                DeviceEventRepo().sendReadyToPlay()
//                                chooseSpaceShip()
                                _navigate.value = true
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
            pressureHandler.full.collect { _full ->
                eLog(TAG, "collecting pressureVM.full $_full")
                if (_full) { pressureReadyToChooseSpaceShip() }
                else { pressureReleaseReadyToChooseSpaceShip() }
            }
        }
    }
    private fun chooseSpaceShip() {
        goToShipMenu = true
//        nav?.let { it.navig(Screens.SpaceWarScreen) }
    }
    suspend fun navigateToShipMenuScreen(navigator: Navigator) {
        Device.navigation.argStr = StrArgumentNav.serializeArgToShipMenu(TryAgainStats.EMPTY_TRY_AGAIN_STATS)
        navigator.navig(Screens.ShipMenuScreen)
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
//        nav = navigator
    }
    init {
        Log.i(TAG, "init: ")
//        Log.e(TAG, "ini: \n\n\n\n\n\n test", )
    }
}
