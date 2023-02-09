package com.mobilegame.spaceshooter.data.device.structure

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.DpSize

class DeviceMetrics {
    var initiated: Boolean? = null
    var width = 0F
    var height = 0F
    var sizeDp = DpSize.Unspecified
    var density = 0F
    var center = Offset.Zero
}
