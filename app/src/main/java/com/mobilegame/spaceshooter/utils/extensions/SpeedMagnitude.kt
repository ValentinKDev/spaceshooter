package com.mobilegame.spaceshooter.utils.extensions

import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.SpeedMagnitude

fun SpeedMagnitude.isSlow(): Boolean = this == SpeedMagnitude.Slow
fun SpeedMagnitude.isFast(): Boolean = this == SpeedMagnitude.Fast
