package com.mobilegame.spaceshooter.logic.uiHandler

import android.content.Context
import android.view.Window
import androidx.compose.ui.platform.LocalDensity
import com.mobilegame.spaceshooter.logic.model.sensor.XYZ
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog

object Device {
    var width = 0F
    var height = 0F
    var density = 0F
    var initiated: Boolean? = null
    var position: XYZ = XYZ(0F, 0F, 0F)

    fun initWith(context: Context) {
        initiated = true
        width = context.resources.displayMetrics.widthPixels.toFloat()
        height = context.resources.displayMetrics.heightPixels.toFloat()
        density = context.resources.displayMetrics.density
        displayDataUI.let {
            wLog("Device::initWith", "width = $width")
            wLog("Device::initWith", "height = $height")
            wLog("Device::initWith", "density = $density")
        }
    }
}