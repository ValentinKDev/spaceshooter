package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize

interface HitBox {
    val ratio: Float
    val canvasSize: Size
    val boxDpOffset: DpOffset
    val boxDp: DpSize
    val ammoWidthDp: Dp
}