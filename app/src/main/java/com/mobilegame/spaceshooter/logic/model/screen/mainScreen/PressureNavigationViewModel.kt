package com.mobilegame.spaceshooter.logic.model.screen.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.extensions.exist
import kotlinx.coroutines.*

class PressureNavigationViewModel(private val screenNav: Screens, val timerValidation: Long = 700L) : ViewModel() {
    private var pressureState: Boolean = false
    private var pressureNumber: Int = 0
//    val timerValidationAnim = (timerValidation * 1F).toInt()

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
        if (pressureState && pressureNumberAtStart == pressureNumber && screenNav.exist()) {
                navigator.navig(screenNav)
        }
    }

    fun handlePressureRelease() {
        vLog("PressureNavVM:handlePressureRelease" , "release")
        setPressureStateTo(false)
    }

    //todo : put screen in the class params
//    fun create(screen: Screens): PressureNavigationViewModel {
//        screenNav = screen
//        return this
//    }
}