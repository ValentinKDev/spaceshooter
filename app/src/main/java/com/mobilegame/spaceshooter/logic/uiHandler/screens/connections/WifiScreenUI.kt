package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections

import androidx.compose.ui.graphics.Color
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
}