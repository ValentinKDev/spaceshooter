package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize

interface HitBox {
    val size: Size
    val sizeDp: DpSize
}