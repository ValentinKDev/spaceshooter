package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.banner

import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiIconsServiceUI
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiType

interface ConnectionScreenBannerUIService {
    val bannerID: String
    val bannerTopPadding: String
    val bannerEndPadding: String
    val topPaddingPercent: Float
    val endPaddingPercent: Float
    val heightBannerPercent: Float
    val type: BtWifiType
    val iconUI: BtWifiIconsServiceUI

    companion object {
        fun createWifiScreenBannerUI(): ConnectionScreenBannerUIService {
            return WifiScreenBannerUI()
        }
        fun createBtScreenBannerUI(
            squareSize: Float,
        ): ConnectionScreenBannerUIService {
            return BluetoothBannerUI(squareSize)
        }
    }
}