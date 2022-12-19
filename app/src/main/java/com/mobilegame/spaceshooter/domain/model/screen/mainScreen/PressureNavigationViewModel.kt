package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PressureNavigationViewModel(): ViewModel() {

    private var screenNav: Screens? = null
    private var pressureState: Boolean = false
    private val timerValidation = 900L
    val timerValidationAnim = (timerValidation * 1.1F).toInt()

    fun setPressureStateTo(state: Boolean) { pressureState = state }

    fun handlePressureStart(navigator: Navigator) {
        setPressureStateTo(true)
        viewModelScope.launch(Dispatchers.IO) { timer(navigator) }
    }

    private suspend fun timer(navigator: Navigator) {
        delay(timerValidation.toLong())
        if (pressureState) {
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