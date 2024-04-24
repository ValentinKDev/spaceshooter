package com.mobilegame.spaceshooter.logic.model.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//class PressureViewModel(loadingTime: Long = 500L) : ViewModel() {
class PressureViewModel() : ViewModel() {
    private val TAG = "PressureViewModel"
    var timerValidation: Long = 500L
    private val _full = MutableStateFlow(false)
    val full: StateFlow<Boolean> = _full.asStateFlow()
    fun setFullAt(bool: Boolean) { _full.update { bool } }

    private var pressureState: Boolean = false
    private var pressureNumber: Int = 0

    fun setPressureStateTo(state: Boolean) { pressureState = state }
    fun incrementPressureNumber() { pressureNumber += 1 }

    fun handlePressureStart() {
        vLog("PressureNavVM" , "handlePressureStart")
        setPressureStateTo(true)
        incrementPressureNumber()
        viewModelScope.launch(Dispatchers.IO) { timer() }
    }

    private suspend fun timer() {
        val pressureNumberAtStart = pressureNumber
        delay(timerValidation)
        if (pressureState && pressureNumberAtStart == pressureNumber) {
//            _full.update { true }
            setFullAt(true)
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

    override fun onCleared() {
        Log.i(TAG, "onCleared: ")
        super.onCleared()
    }
}