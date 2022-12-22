package com.mobilegame.spaceshooter.domain.model.screen.uiHandler

import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.Icons.BluetoothIcon
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.presentation.theme.MyColor

class BluetoothScreenUI {
    val colors = ColorsBluetoothScreen
    var banner = BannerBluetoothScreen()
    val template = MainTemplate

    data class BannerBluetoothScreen (
        val topPadding: Float = 0.3F,
        val heightBanner: Float = 1F,
        val bluetoothIcon: BluetoothIcon = BluetoothIcon(heightBanner * Device.height),
    )

    object ColorsBluetoothScreen {
        val contrast = MyColor.applicationContrast
    }
}
