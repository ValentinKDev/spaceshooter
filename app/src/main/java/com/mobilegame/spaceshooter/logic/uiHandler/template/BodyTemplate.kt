package com.mobilegame.spaceshooter.logic.uiHandler.template

import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.data.device.Device

class BodyTemplate(percent: TemplateUI.TemplatePercents) {
    val id = "emptySpaceTemplate"
    val sizeWithBand = Size(Device.metrics.width, Device.metrics.height * percent.bodyWithBand)
}