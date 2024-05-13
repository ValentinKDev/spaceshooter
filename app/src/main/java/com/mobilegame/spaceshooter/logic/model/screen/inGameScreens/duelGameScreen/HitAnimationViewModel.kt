package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.data.device.structure.DeviceEvent
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HitAnimationViewModel(private val type: ShipType): ViewModel() {
    private val TAG = "HitAnimationViewModel"

    private val angleDelayTime = 20L
    private val colorDelayTime = 15L * angleDelayTime
    private val anglesArray: Array<Float> = arrayOf(-8F, -4F, -2F, 2F, 4F, 8F)
    private val halfAnglesArrayIndex: Int = (anglesArray.size / 2) - 1
    private val maxAnglesArrayIndex: Int = anglesArray.size - 1
    private val _angleHitAnimation = MutableStateFlow(0F)
    val angleHitAnimation: StateFlow<Float> = _angleHitAnimation.asStateFlow()
    private fun updateAngleOnHit(newVal: Float) { _angleHitAnimation.value = newVal }
    private val _visibleOpponentColor = MutableStateFlow(Pair(false, type.info.color))
    val visibleOpponentColor: StateFlow<Pair<Boolean, Color>> = _visibleOpponentColor.asStateFlow()
    private fun updateVisibleColor(new: Pair<Boolean, Color>) { _visibleOpponentColor.value = new}

    init {
        viewModelScope.launch {
            val hitsJob = async { listenToTheHits() }
        }
    }

    private suspend fun listenToTheHits() {
        Device.event.hitStateFlow.collect {
            angleAnimationOnHitStart()
            visibleOpponentOnHitStart(it)
        }
    }

    private fun visibleOpponentOnHitStart(shoot: Shoot) = viewModelScope.launch {
        updateVisibleColor(Pair(true, shoot.type.info.color))
        delay(colorDelayTime)
        updateVisibleColor(Pair(false, shoot.type.info.color))
    }

    private fun angleAnimationOnHitStart() = viewModelScope.launch {
        var i: Int = halfAnglesArrayIndex
        var j = 0
        while (i >= 0) {
            updateAngleOnHit(anglesArray[i])
            delay(angleDelayTime)
            i--
        }
        while (j < maxAnglesArrayIndex) {
            updateAngleOnHit(anglesArray[j])
            delay(angleDelayTime)
            j++
        }
        while (j >= halfAnglesArrayIndex) {
            updateAngleOnHit(anglesArray[j])
            delay(angleDelayTime)
            j--
        }
    }

}