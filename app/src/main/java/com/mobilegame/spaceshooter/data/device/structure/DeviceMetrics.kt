package com.mobilegame.spaceshooter.data.device.structure

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

class DeviceMetrics {
//    var initiated: Boolean? = null
    var width = 0F
    var height = 0F
    var size = Size.Unspecified
    var sizeDp = DpSize.Unspecified
    var density = 0F
    var center = Offset.Zero

    //todo : remove xratio and resize the display to the smaller screen between the 2 devices
    fun getXRatioWithDp(withValue: Dp): Float = withValue.value / sizeDp.width.value
    fun getYRatioWithDp(withValue: Dp): Float = withValue.value / sizeDp.height.value

    fun getXRatioWithPx(withValue: Float): Float = withValue / size.width
    fun getYRatioWithPx(withValue: Float): Float = withValue / size.height
}
