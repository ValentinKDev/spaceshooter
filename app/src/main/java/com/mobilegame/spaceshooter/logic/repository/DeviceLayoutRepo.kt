package com.mobilegame.spaceshooter.logic.repository

import android.content.Context
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

class DeviceLayoutRepo {
    fun init(context: Context, layout: LayoutCoordinates) {
        Device.metrics.initiated = true
        Device.metrics.width = layout.size.width.toFloat()
        Device.metrics.height = layout.size.height.toFloat()
        Device.metrics.density = context.resources.displayMetrics.density
        Device.metrics.sizeDp = DpSize(Device.metrics.width.toDp(), Device.metrics.height.toDp())
        displayDataUI.let {
            wLog("Device::initWith", "width = ${Device.metrics.width}")
            wLog("Device::initWith", "height = ${Device.metrics.height}")
            wLog("Device::initWith", "density = ${Device.metrics.density}")
        }
    }
}