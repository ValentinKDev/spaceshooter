package com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice

import android.content.Context
import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.RegisterDeviceNameUI
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterDeviceViewModel(context: Context, val screenNav: Screens): ViewModel() {
    val ui = RegisterDeviceNameUI()
    val deviceNameDatastore = DataStoreService.deviceName(context)

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
    fun registerAction(navigator: Navigator) {
        eLog("RegisterDeviceVM::registerAction", "registerring name $input")

        viewModelScope.launch {
            deviceNameDatastore.putString(DataStoreNameProvider.DeviceName.key, input.value.trim())
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