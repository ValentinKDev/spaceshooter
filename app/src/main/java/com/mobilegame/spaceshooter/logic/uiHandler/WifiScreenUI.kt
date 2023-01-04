package com.mobilegame.spaceshooter.logic.uiHandler

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.WifiIcon
import com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.presentation.theme.MyColor

class WifiScreenUI {
    val color = ColorWifiScreen()
    val banner = BannerWifiScreenAdapter()
    val template = MainTemplate

    data class ColorWifiScreen (
        val contrast: Color = MyColor.applicationContrast,
    )
    data class BannerWifiScreenAdapter (
        val heightBanner: Float = 1F,
        val topPadding: Float = 0.3F,
        var wifiIcon: WifiIcon = WifiIcon(Device.height * heightBanner, StrokeCap.Square),
    )
}