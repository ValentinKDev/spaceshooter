package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.WifiIcon
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.banner.ConnectionScreenBannerUIService
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor

class WifiScreenUI {
    val color = ColorWifiScreen()
//    val banner = BannerWifiScreenAdapter()
    val template = TemplateUI()
    val deviceMenu = DevicesMenuUI()
    val banner = ConnectionScreenBannerUIService.createWifiScreenBannerUI()

    data class ColorWifiScreen (
        val contrast: Color = MyColor.applicationContrast,
    )
//    data class BannerWifiScreenAdapter (
//        val bannerID: String = "banner_ID",
//        val bannerTopPaddID: String = "banner_top_padd_ID",
//        val bannerEndPaddID: String = "banner_top_end_ID",
//        val topPadding: Float = 0.3F,
//        val endPadding: Float = 0.1F,
//        val heightBanner: Float = 1F,
//        var wifiIcon: WifiIcon = WifiIcon(Device.height * heightBanner, StrokeCap.Square),
//    df
}