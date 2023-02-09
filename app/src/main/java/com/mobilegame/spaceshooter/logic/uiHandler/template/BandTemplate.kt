package com.mobilegame.spaceshooter.logic.uiHandler.template

import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.utils.extensions.toDp

class BandTemplate(percent: TemplateUI.TemplatePercents) {
    val id = "BandTemplate"
    val height: Float = percent.band * Device.metrics.height
    val heightDp: Dp = height.toDp()
}