package com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice

import android.app.Application
import androidx.compose.animation.core.MutableTransitionState
import androidx.lifecycle.AndroidViewModel
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.RegisterDeviceNameUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterDeviceViewModel(application: Application): AndroidViewModel(application) {
    val ui = RegisterDeviceNameUI()
//    val templateUI = TemplateUI()
    val deviceNameDatastore = DataStoreService.createDeviceName(application)

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
    fun registerAction() {

    }

    private val _visibleUnderscoreState = MutableStateFlow(MutableTransitionState(true))
    val visibleUnderscoreState = _visibleUnderscoreState.asStateFlow()
    fun setVisibleUnderscoreTargetTo(state: Boolean) {
        _visibleUnderscoreState.value.targetState = state
    }
    fun reinitialiseVisibleUnderscoreState() {
        _visibleUnderscoreState.value.targetState = !visibleUnderscoreState.value.currentState
    }
    fun getStringSpaces(length: Int): String {
        var str = ""
        for (i in 0 until length) {
           str += " "
        }
        return str
    }
}