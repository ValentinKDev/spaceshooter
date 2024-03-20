package com.mobilegame.spaceshooter.data.device

import android.content.Context
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.data.device.structure.DeviceData
import com.mobilegame.spaceshooter.data.device.structure.DeviceEvent
import com.mobilegame.spaceshooter.data.device.structure.DeviceMetrics
import com.mobilegame.spaceshooter.data.device.structure.DeviceWifi
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

object Device {
    val data = DeviceData()
    val metrics = DeviceMetrics()
    val wifi = DeviceWifi()
    val event = DeviceEvent()
}