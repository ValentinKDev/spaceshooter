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
import com.mobilegame.spaceshooter.logic.repository.gameStats.GameStatsRepo
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
    val historyStatsRepo = GameStatsRepo(getApplication())
    val pressureHandler = PressureHandler(null)
    val registerVM = RegisterDeviceViewModel(application, Screens.WifiScreen)
    val repo = DeviceWifiRepo()
    private var connectionVM: WifiConnectionViewModel = WifiConnectionViewModel()
    private val backNavScreen: Screens = Screens.MainScreen
    val backPressureHandler = PressureHandler(null)
    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean> = _navigate.asStateFlow()

    private val _allOpponentHistories = MutableStateFlow<List<TryAgainStats>>(emptyList())
    val allOpponentHistories: StateFlow<List<TryAgainStats>> = _allOpponentHistories.asStateFlow()
    private val _deviceName = MutableStateFlow(Device.data.name)
    val deviceName: StateFlow<String?> = _deviceName.asStateFlow()
    private var goToShipMenu: Boolean = false

    init {
        eLog(TAG, "init")
        repo.initWifi(application)
        repo.initNetworkSearchAndDiscovery(application)

        viewModelScope.launch { withContext(Dispatchers.IO) {
            _allOpponentHistories.value = historyStatsRepo.getAllTheHistoryAgainstOpponent()
            Device.wifi.listVisibleDevicesFlow.map {
                //todo: Log.i(TAG, "list collected $it")
                //todo: just to check for open flows
//                Log.i(TAG, "list collected $it")
                it.firstOrNull() }.collect { visibleDeviceState ->
                visibleDeviceState?.let {
                    when (it.state) {
                        PreparationState.ReadyToChooseShip -> {
//                        PreparationState.ReadyToPlay -> {
//                        PreparationState.ReadyToPlay -> {
                            if (pressureHandler.full.value) {
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

        viewModelScope.launch {
            backPressureHandler.full.collect { _full ->
                eLog(TAG, "collecting pressureVM.full $_full")
                if (_full) { backNavigation() }
            }
        }
    }
    suspend fun navigateToShipMenuScreen(navigator: Navigator) {
        navigator.navig(Screens.ShipMenuScreen, StrArgumentNav.serializeArgToShipMenu(TryAgainStats.EMPTY_TRY_AGAIN_STATS))
        Log.e(TAG, "navigateToShipMenuScreen: Device.nav.arg ${Device.navigation.argStr}")
    }

    fun nonNullNameTrigger() {
        Log.i(TAG, "nonNullNameTrigger: ")
        viewModelScope.launch(Dispatchers.IO) {
            connectionVM.hostAndSearch()
        }
    }
    fun pressureReadyToChooseSpaceShip() = viewModelScope.launch {
        Log.e(TAG, "pressureReadyToChooseSpaceShip: ")
        DeviceEventRepo().sendReadyToChooseShip()
    }
    fun pressureReleaseReadyToChooseSpaceShip()  = viewModelScope.launch {
        Log.e(TAG, "pressureReleaseReadyToChooseSpaceShip: ",)
        DeviceEventRepo().sendNotReadyToChooseShip()
    }

    fun backNavigation() = viewModelScope.launch {
        Device.navigation.nav.navig(backNavScreen)
//        connectionVM.clear()
//        onCleared()
    }

    //todo : is this better to store the navigation in the Device Data obj ?
    init {
        Log.i(TAG, "init: ")
    }
}
