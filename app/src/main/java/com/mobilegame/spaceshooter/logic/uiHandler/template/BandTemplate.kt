package com.mobilegame.spaceshooter.logic.uiHandler.template

import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.utils.extensions.toDp

class BandTemplate {
    val id = "BandTemplate"
    val percent = PercentBandTemplate()
    data class PercentBandTemplate (
        val height: Float = MainTemplateUI.header.percent.height
    )
    data class SizesBandTemplate (
        val height: Float = MainTemplateUI.band.percent.height * Device.height,
        val heightDp: Dp = height.toDp()
    )
}