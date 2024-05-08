package com.mobilegame.spaceshooter.logic.uiHandler.template

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnimationSlideHandler {
    private val _visibleAnimation = MutableStateFlow(true)
    val visibleAnimation: StateFlow<Boolean> = _visibleAnimation.asStateFlow()
    fun updateVisibility() {_visibleAnimation.value = !visibleAnimation.value}
    var direction: LateralDirection = LateralDirection.Left
    fun upDateDirection(newDirection: LateralDirection) {
        direction = newDirection
    }
}