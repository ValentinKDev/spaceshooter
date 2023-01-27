package com.mobilegame.spaceshooter.logic.uiHandler

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

object Device {
    var width = 0F
    var height = 0F
    var sizeDp = DpSize.Unspecified
    var density = 0F
    var center = Offset.Zero
    var name: String? = null
    var initiated: Boolean? = null

    suspend fun initDatastore(context: Context) {
        val nameDatastore = DataStoreService.createDeviceName(context)
        name = nameDatastore.getString(DataStoreNameProvider.DeviceName.key)
    }

    fun initLayout(context: Context, layout: LayoutCoordinates) {
        initiated = true
        width = layout.size.width.toFloat()
        height = layout.size.height.toFloat()
        density = context.resources.displayMetrics.density
        sizeDp = DpSize(width.toDp(), height.toDp())
        displayDataUI.let {
            wLog("Device::initWith", "width = $width")
            wLog("Device::initWith", "height = $height")
            wLog("Device::initWith", "density = $density")
        }
    }
}