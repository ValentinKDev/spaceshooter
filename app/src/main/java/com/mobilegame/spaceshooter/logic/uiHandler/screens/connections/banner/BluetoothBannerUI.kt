package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.banner

import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiIconsServiceUI
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiType

class BluetoothBannerUI(
    squareSize: Float
): ConnectionScreenBannerUIService {
    override val bannerID: String = "banner_ID"
    override val bannerTopPadding: String = "banner_top_padding_ID"
    override val bannerEndPadding: String = "banner_end_padding_ID"
    override val topPaddingPercent: Float = 0.4F
    override val endPaddingPercent: Float = 0.2F
    override val heightBannerPercent: Float = 1F
    override val type: BtWifiType = BtWifiType.Bluetooth
    override val iconUI: BtWifiIconsServiceUI = BtWifiIconsServiceUI.createBluetoothIconUI(squareSize)
}