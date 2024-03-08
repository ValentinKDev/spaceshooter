package com.mobilegame.spaceshooter.data.device.structure

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.utils.extensions.toDp

class DeviceMetrics {
    var initiated: Boolean? = null
    var width = 0F
    var height = 0F
    var sizeDp = DpSize.Unspecified
    var density = 0F
    var center = Offset.Zero

    fun getXRatio(withValue: Float): Float = withValue / sizeDp.width.value
    fun getYRatio(withValue: Float): Float = withValue / sizeDp.height.value
}
