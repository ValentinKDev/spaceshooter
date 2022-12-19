package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.domain.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PressureNavigationViewModel(): ViewModel() {

    private var screenNav: Screens? = null
    private var pressure: Boolean? = null
    private var timerStart = 0L
    private var timerEnd = 0L
    private var diff = 0L
    val timerValidation = 900

    fun setPressure() { pressure = true }
    fun setTimerStart() { timerStart = System.currentTimeMillis() }
    fun setTimerEnd() { timerEnd = System.currentTimeMillis() }
    fun setDiff() { diff = timerEnd - timerStart}
    fun resetTimerStart() { timerStart = 0L}
    fun resetTimerEnd() { timerEnd = 0L}
    fun resetDiff() { diff = 0L }

    fun handlePressureStart() {
        resetDiff()
        resetTimerStart()
        resetTimerEnd()
        setPressure()
        setTimerStart()
    }

    //todo: force the realese logic when diff >= timerValidation before the user release his finger
    fun handlePressureRelease(navigator: Navigator) {
        pressure?.let {
            vLog("PressureHandler::handlePressureRelease" , "start")
            setTimerEnd()
            setDiff()
            if (diff >= timerValidation) {
                screenNav?.let {
                    eLog("PressureHandler::handlePressureRelease" , "Go to ${it.route}")
                    viewModelScope.launch(Dispatchers.IO) {
                        navigator.navig(it)
                    }
                }
            }
        }
    }

    fun create(screen: Screens): PressureNavigationViewModel {
        screenNav = screen

        return this
    }
}