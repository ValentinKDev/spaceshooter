package com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice

import android.content.Context
import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.RegisterDeviceNameUI
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.repository.device.DeviceDataRepo
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterDeviceViewModel(context: Context, val screenNav: Screens): ViewModel() {
    val ui = RegisterDeviceNameUI()
    val deviceNameDatastore = DataStoreService.deviceName(context)
    init { eLog("RegisterDeviceViewModel::init", "start VM") }

    private val _input = MutableStateFlow("")
    val input: StateFlow<String> = _input.asStateFlow()
    fun addCharToInput(c: Char) {
        if (input.value.length < 8) _input.value = input.value + c
    }
    fun deleteAction() {
        if (input.value.isNotEmpty())
            _input.value = input.value.substring(0, input.value.lastIndex)
    }
    fun spaceAction() {
        if (input.value.isNotEmpty()) addCharToInput(' ')
    }
    fun registerAction(navigator: Navigator, context: Context) {
        eLog("RegisterDeviceVM::registerAction", "registerring name $input")
        //todo : popup message in case of error at the first
        viewModelScope.launch {
            deviceNameDatastore.putString(DataStoreNameProvider.DeviceName.key, input.value.trim())
            DeviceDataRepo().updateName(context)
//            val testName = deviceNameDatastore.getString(DataStoreNameProvider.DeviceName.key)
//            testName?.let { eLog("RegisterDeviceVM::registerAction", testName) } ?: { eLog("RegisterDeviceVM::registerAction", "null") }
//            Device.data.name?.let { eLog("RegisterDeviceVM::registerAction", it) } ?: { eLog("RegisterDeviceVM::registerAction", "null") }
            navigator.navig(screenNav)
        }
    }

    private val _visibleUnderscoreState = MutableStateFlow(MutableTransitionState(true))
    val visibleUnderscoreState = _visibleUnderscoreState.asStateFlow()
    fun setVisibleUnderscoreTargetTo(state: Boolean) {
        _visibleUnderscoreState.value.targetState = state
    }

    fun isDeleteActionEnable(str: String): Boolean = str.isNotEmpty()
    fun isRegisteringActionEnable(str: String): Boolean = str.length > 1
}