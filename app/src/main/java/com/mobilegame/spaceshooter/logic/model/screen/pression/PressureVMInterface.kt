package com.mobilegame.spaceshooter.logic.model.screen.pression

import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator

interface PressureVMInterface {
    val timerValidation: Long
    fun handlePressureStart(nav: Navigator)
    fun handlePressureRelease()
}