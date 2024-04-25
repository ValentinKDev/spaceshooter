package com.mobilegame.spaceshooter.logic.model.navigation

import android.util.Log
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//class PressureViewModel() : ViewModel() {
class PressureHandler(private val triggerNavTo: Screens?, private val arg: String = "") {
    private val TAG = "PressureViewModel"
    var timerValidation: Long = 400L
    private val _full = MutableStateFlow(false)
    val full: StateFlow<Boolean> = _full.asStateFlow()
    fun setFullAt(bool: Boolean) { _full.update { bool } }

    private var pressureState: Boolean = false
    private var pressureNumber: Int = 0

    fun setPressureStateTo(state: Boolean) { pressureState = state }
    fun incrementPressureNumber() { pressureNumber += 1 }

//    fun handlePressureStart() {
    suspend fun handlePressureStart() {
        vLog("PressureNavVM" , "handlePressureStart")
        setPressureStateTo(true)
        incrementPressureNumber()
//        viewModelScope.launch(Dispatchers.IO) { timer() }
        timer()
    }

    private suspend fun timer() {
        val pressureNumberAtStart = pressureNumber
        delay(timerValidation)
        if (pressureState && pressureNumberAtStart == pressureNumber) {
            Log.w(TAG, "timer: pression is full")
//            _full.update { true }
            setFullAt(true)
            triggerNavTo?.let { screen -> Device.navigation.nav.navig(screen, arg) }
        }
    }

    fun handlePressureRelease() {
        vLog("PressureNavVM" , "handlePressureRelease")
        if (pressureState) {
            setPressureStateTo(false)
            setFullAt(false)
//            _full.update { false }
        }
    }

    suspend fun navigateTo(screen: Screens, arg: String = "") {
        Device.navigation.nav.navig(screen, arg)
    }

//    fun clearVM() { onCleared() }
//    override fun onCleared() {
//        Log.i(TAG, "onCleared: ")
//        super.onCleared()
//    }
}