package com.mobilegame.spaceshooter.logic.model.screen.uiHandler

import android.content.Context
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog

object Device {
    var width = 0F
    var height = 0F
    var density = 0F
    var initiated: Boolean? = null

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