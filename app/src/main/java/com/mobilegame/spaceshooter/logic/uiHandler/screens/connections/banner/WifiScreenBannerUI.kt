package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.banner

import androidx.compose.ui.graphics.StrokeCap
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiIconsServiceUI
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiType

class WifiScreenBannerUI( ): ConnectionScreenBannerUIService {
    override val bannerID: String = "banner_ID"
    override val bannerTopPadding: String = "banner_top_padding_ID"
    override val bannerEndPadding: String = "banner_end_padding_ID"
    override val topPaddingPercent: Float = 0.5F
    override val endPaddingPercent: Float = 0.12F
    override val heightBannerPercent: Float = 1F
    override val type: BtWifiType = BtWifiType.Wifi
    override val iconUI: BtWifiIconsServiceUI = BtWifiIconsServiceUI.createWifiIconUI(Device.height * heightBannerPercent, StrokeCap.Square)
}