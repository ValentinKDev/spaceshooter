package com.mobilegame.spaceshooter.logic.uiHandler.screens.tryAgainScreen

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen.StatsIndication
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import com.mobilegame.spaceshooter.utils.extensions.toSize
import com.mobilegame.spaceshooter.utils.extensions.toSp

class TryAgainUI {
    val template = TemplateUI(instantNavBack = true)
    val band = BandTryAgainUI(template.band.height)
    val body = BodyShipMenu(template.body.sizeWithBand)

    class BandTryAgainUI(bandHeight: Float) {
        val ids = BandTryAgainID()
        val sizes = BandTryAgainSizes(bandHeight)
        class BandTryAgainSizes(bandHeight: Float) {
            private val bandHeightDp = bandHeight.toDp()
            private val shipNameText: Float = bandHeight * 0.95F
            private val shipNameTextSp: TextUnit = shipNameText.toSp()
            val shipNameTextStyle: TextStyle = TextStyle( fontSize = shipNameTextSp, fontWeight = FontWeight.Bold, )
            private val shipStatText: Float = bandHeight * 0.25F
            private val shipStatTextDp: TextUnit = shipStatText.toSp()
            val shipStatTextPadDp: Dp = (shipStatText * 0.25F).toDp()
            val shipStatStyle: TextStyle = TextStyle( fontSize = shipStatTextDp, fontWeight = FontWeight.Bold, )
            private val statsBoxBar: Size = (bandHeight * 0.25F).toSize()
            val statsBoxBarDp: DpSize = statsBoxBar.toDpSize()
            val statsBoxBarPadDp: Dp = (statsBoxBarDp.width.value * 0.35F).toDp()
            val statHeight: Dp = ((bandHeight / StatsIndication.values().size.toFloat()) * 0.85F).toDp()
        }
        data class BandTryAgainID(
            val shipName: String = "ship_name_id",
            val resultText: String = "result_text_id",
            val statsBox: String = "ship_stats_box_id",
            val statsTextBox: String = "ship_stats_text_id",
            val statsBarBox: String = "ship_stats_bar_id",
            val wins: String = "WINS",
            val loses: String = "LOSSES",
            val streak: String = "STREAK",
        )
    }

    class BodyShipMenu(sizeWithBand: Size) {
        val ids = BandShipBodyID()
        val sizes = BandTryAgainSizes(sizeWithBand)
        class BandTryAgainSizes(sizeWithBand: Size) {
            val sizeWithBand = sizeWithBand.toDpSize()
            val shipViewBox: Size = (Device.metrics.height * 0.15F).toSize()
            val indicatorBox: Size = (Device.metrics.width * 0.05F).toSize()
            val indicatorBoxDp: DpSize = indicatorBox.toDpSize()
            val indicatorBoxPadDp: Dp = (Device.metrics.width * 0.015F).toDp()
            val indicatorBoxBorderWidth: Dp = (Device.metrics.width * 0.0025F).toDp()
        }
        data class BandShipBodyID(
            val leftArrow: String = "left_arrow",
            val rightArrow: String = "right_arrow",
            val shipNumberListIndicator: String = "ship_number_indicator",
            val shipPresentation: String = "ship_presentation",
        )
    }
}