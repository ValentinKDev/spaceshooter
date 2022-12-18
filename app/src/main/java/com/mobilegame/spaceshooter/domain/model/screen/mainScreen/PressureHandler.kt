package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog

class PressureHandler() {
    private var timerStart = 0L
    private var timerEnd = 0L
    private var diff = 0L
    val timerValidation = 1200

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
        setTimerStart()
    }

    fun handlePressureRelease() {
        vLog("PressureHandler::handlePressureRelease" , "start")
        setTimerEnd()
        setDiff()
        if (diff >= timerValidation) {
            eLog("PressureHandler::handlePressureRelease" , "Go to ")
        }
    }
    fun create(): PressureHandler {
        return this
    }
}