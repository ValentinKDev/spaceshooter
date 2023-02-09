package com.mobilegame.spaceshooter.logic.model.screen.pression

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.extensions.exist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PressureNavigationViewModel(
    private val screenNav: Screens,
    override val timerValidation: Long = 700L
) : ViewModel(), PressureVMInterface {
    private var pressureState: Boolean = false
    private var pressureNumber: Int = 0
    private var navigator: Navigator? = null

    fun setPressureStateTo(state: Boolean) { pressureState = state }
    fun incrementPressureNumber() { pressureNumber += 1 }

    override fun handlePressureStart(nav: Navigator) {
        vLog("PressureNavVM" , "handlePressureStart")
        navigator = nav
        setPressureStateTo(true)
        incrementPressureNumber()
        viewModelScope.launch(Dispatchers.IO) { timer() }
    }

    private suspend fun timer() {
        val pressureNumberAtStart = pressureNumber
        delay(timerValidation)
        if (pressureState && pressureNumberAtStart == pressureNumber && screenNav.exist()) {
            navigator?.navig(screenNav)
        }
    }

    override fun handlePressureRelease() {
        if (pressureState) {
            vLog("PressureNavVM" , "handlePressureRelease: release")
            setPressureStateTo(false)
        }
    }
}