package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.*

class PressureNavigationViewModel(): ViewModel() {

    private var screenNav: Screens? = null
    private var pressureState: Boolean = false
    private var pressureNumber: Int = 0
    private val timerValidation = 900L
    val timerValidationAnim = (timerValidation * 1F).toInt()

    fun setPressureStateTo(state: Boolean) { pressureState = state }
    fun incrementPressureNumber() { pressureNumber += 1 }

    var nav: Navigator? = null
    fun handlePressureStart(navigator: Navigator) {
        vLog("PressureNavVM:handlePressureRelease" , "pressure")
        nav = navigator
        setPressureStateTo(true)
        incrementPressureNumber()
        viewModelScope.launch(Dispatchers.IO) { timer(navigator) }
    }

    private suspend fun timer(navigator: Navigator) {
        val pressureNumberAtStart = pressureNumber
        delay(timerValidation.toLong())
        if (pressureState && pressureNumberAtStart == pressureNumber) {
            screenNav?.let {
                eLog("PressureNavVM::startTimer", "Go to ${it.route}")
                navigator.navig(it)
            }
        } else {
            eLog("PressureNavVM::startTimer", "User release button before the end")
        }
    }

    fun handlePressureRelease() {
        vLog("PressureNavVM:handlePressureRelease" , "release")
        setPressureStateTo(false)
    }

    fun create(screen: Screens): PressureNavigationViewModel {
        screenNav = screen

        return this
    }
}