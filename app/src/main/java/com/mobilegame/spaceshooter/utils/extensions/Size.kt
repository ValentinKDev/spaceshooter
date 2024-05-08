package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.SpaceWarGameViewModel

fun Size.toDpSize(): DpSize = DpSize(
    width = this.width.toDp(),
    height = this.height.toDp()
)

fun squareSize(p: Float): Size = Size(p, p)

inline fun Size.putOnTopOfDisplay() = Size(width = this.width, Device.metrics.height)
//val gameVM = SpaceWarGameViewModel(application, Size(Device.metrics.width, Device.metrics.height))
infix fun Size.timeS(f: Float) = Size(width = this.width * f, height = this.height * f)