package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device

fun Dp.fromDp(): Float = this.value * Device.density