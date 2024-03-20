package com.mobilegame.spaceshooter.data.device.structure

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpSize
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DeviceMetrics {
//    var initiated: Boolean? = null
    var width = 0F
    var height = 0F
    var size = Size.Unspecified
    var sizeDp = DpSize.Unspecified
    var density = 0F
    var center = Offset.Zero

    //todo : remove xratio and resize the display to the smaller screen between the 2 devices
    fun getXRatio(withValue: Float): Float = withValue / sizeDp.width.value
    fun getYRatio(withValue: Float): Float = withValue / sizeDp.height.value
}
