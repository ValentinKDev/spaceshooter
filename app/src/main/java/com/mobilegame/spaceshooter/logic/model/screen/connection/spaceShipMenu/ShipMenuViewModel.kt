package com.mobilegame.spaceshooter.logic.model.screen.connection.spaceShipMenu


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.connection.wifi.PreparationState
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.repository.device.DeviceEventRepo
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.ShipMenuUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShipMenuViewModel(): ViewModel() {
    val TAG = "ShipMenuViewModel"
    val templateUI = TemplateUI(instantNavBack = true)
    val shipMenuUI = ShipMenuUI()
    val pressureVM = PressureViewModel()

//    val shipVM = SpaceShipViewModel(application, ui, shipType)
//    private val _deviceName = MutableStateFlow(Device.data.name)
//    val deviceName: StateFlow<String?> = _deviceName.asStateFlow()
    private val _pickedShip = MutableStateFlow(false)
    val pickedShip: StateFlow<Boolean> = _pickedShip.asStateFlow()
    private val shipListSize = ShipType.LIST.size
    private val _shipListIndex = MutableStateFlow(0)
    val shipListIndex: StateFlow<Int> = _shipListIndex.asStateFlow()
    private val _shipType = MutableStateFlow(ShipType.getFromList(shipListIndex.value))
    val shipType: StateFlow<ShipType> = _shipType.asStateFlow()
    var shipUI: SpaceShipIconUIInterface = getCurrentShipUI()
    private suspend fun updateShipUI() { shipUI = getCurrentShipUI() }
    private fun getCurrentShipUI(): SpaceShipIconUIInterface = ShipType.getTypeShipUI(shipListIndex.value, shipMenuUI.body.sizes.shipViewBox)
    private suspend fun updateShipType() { _shipType.emit(ShipType.getFromList(shipListIndex.value)) }

    private fun decrementListIndex() {
        if (_shipListIndex.value == 0) _shipListIndex.value = shipListSize - 1
        else _shipListIndex.value = shipListIndex.value - 1
    }
    private fun incrementListIndex() {
        if (_shipListIndex.value == shipListSize - 1) _shipListIndex.value = 0
        else _shipListIndex.value = shipListIndex.value + 1
    }
    fun handleLeftArrowClick() = viewModelScope.launch {
        Log.i(TAG, "handleLeftArrowClick: ")
        decrementListIndex()
        updateShipType()
        updateShipUI()
        Log.i(TAG, "handleLeftArrowClick: listIndex ${shipListIndex.value} type ${shipType.value.name}")
    }
    fun handleRightArrowClick() = viewModelScope.launch {
        Log.i(TAG, "handleRightArrowClick: ")
        incrementListIndex()
        updateShipType()
        updateShipUI()
        Log.i(TAG, "handleRightArrowClick: listIndex ${shipListIndex.value} type ${shipType.value.name}")
    }

    init {
        viewModelScope.launch {
            val ltr = async {
                withContext(Dispatchers.IO) {
                    Device.wifi.listVisibleDevicesFlow.map { it.firstOrNull() }.collect {visibleDeviceState ->
                        visibleDeviceState?.let {
                            when (it.state) {
                                PreparationState.ReadyToPlay -> {
//                                PreparationState.ReadyToChooseShip -> {
                                    Log.e(TAG, "front device is ReadyToPlay")
                                    if (pressureVM.full.value) {
                                        Log.i(TAG, "ready to play and full is true: ")
//                                        val ev = async { DeviceEventRepo().sendReadyToChooseShip() }
//                                        val ev = async { DeviceEventRepo().sendReadyToPlay() }
                                        spaceShipPicked()
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
            val emr = async {
                pressureVM.full.collect { _full ->
                    eLog(TAG, "collecting pressureVM.full $_full")
                    if (_full) { pressureReadyToPlay() }
                    else { pressureReleaseToPlay() }
                }
            }
        }
    }
    private suspend fun spaceShipPicked() {
        Log.e(TAG, "spaceShipPicked: true \n\n\n\n\n true", )
//        =_pickedShip.emit(true)
        _pickedShip.value = true
    }
    fun pressureReadyToPlay() = viewModelScope.launch {
        Log.i(TAG, "pressureReadyToPlay: ", )
//        DeviceEventRepo().sendReadyToPlay()
        DeviceEventRepo().sendReadyToPlay()
//        DeviceEventRepo().sendReadyToChooseShip()
    }
    fun pressureReleaseToPlay()  = viewModelScope.launch {
        Log.i(TAG, "pressureReleaseToPlay: ")
//        DeviceEventRepo().sendNotReadyToPlay()
        DeviceEventRepo().sendNotReadyToPlay()
//        DeviceEventRepo().sendNotReadyToChooseShip()
    }
}