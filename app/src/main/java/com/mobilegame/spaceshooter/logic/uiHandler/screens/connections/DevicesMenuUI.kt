package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections

import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.utils.extensions.toDpSize


class DevicesMenuUI {
    val template = TemplateUI()
    val band = BandDeviceMenu()
    val body = BodyDeviceMenu(template.body.sizeWithBand)

    class BandDeviceMenu {
        val ids = BandDeviceMenuID()
        data class BandDeviceMenuID(
            val myNameText: String = "my_name_ID",
            val connectedDeviceText: String = "connected_name_ID",
            val titleBox: String = "title_box_ID",
            val winText: String = "WINS",
            val losesText: String = "LOSSES",
            val streakText: String = "STREAK",
            val winNumber: String = "WINS_NB",
            val losesNumber: String = "LOSES_NB",
            val streakNumber: String = "STREAK_NB",
        )
    }

    class BodyDeviceMenu(sizeWithBand: Size) {
        val ids = BodyDeviceMenuID()
        data class BodyDeviceMenuID (
            val instruction: String = "HOLD TO CONNECT",
            val smartphoneIcon: String = "smartphone_icon_ID",
            val navBar: String = "navigation_bar_ID"
        )
        val size = sizeWithBand
        val sizeDp = sizeWithBand.toDpSize()
    }
}