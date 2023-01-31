package com.mobilegame.spaceshooter.logic.uiHandler.Icons

import androidx.compose.ui.graphics.StrokeCap

interface BtWifiIconsServiceUI {
    val id: String
    val type: BtWifiType

    companion object {
        fun createWifiIconUI(
            squareSize: Float,
            strokeCap: StrokeCap,
            outlined: Boolean? = null,
        ): BtWifiIconsServiceUI {
            return WifiIconUI(squareSize, strokeCap, outlined)
        }
        fun createBluetoothIconUI(
            squareSize: Float,
        ): BtWifiIconsServiceUI {
            return BluetoothIconUI(squareSize)
        }
    }
}