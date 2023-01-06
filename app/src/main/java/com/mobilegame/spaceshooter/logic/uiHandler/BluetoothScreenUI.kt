package com.mobilegame.spaceshooter.logic.uiHandler

import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BluetoothIcon
import com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate.MainTemplateUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

class BluetoothScreenUI {
    val colors = ColorsBluetoothScreen
    var banner = BannerBluetoothScreen()
    val template = MainTemplateUI

    data class BannerBluetoothScreen (
        val topPadding: Float = 0.3F,
        val heightBanner: Float = 1F,
        val bluetoothIcon: BluetoothIcon = BluetoothIcon(heightBanner * Device.height),
    )

    object ColorsBluetoothScreen {
        val contrast = MyColor.applicationContrast
    }
}
