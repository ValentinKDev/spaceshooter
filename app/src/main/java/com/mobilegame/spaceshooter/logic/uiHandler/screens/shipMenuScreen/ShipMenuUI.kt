package com.mobilegame.spaceshooter.logic.uiHandler.screens.shipMenuScreen

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

class ShipMenuUI {
    val template = TemplateUI(instantNavBack = true)
    val band = BandShipMenu(template.band.height)
    val body = BodyShipMenu(template.body.sizeWithBand)
//    val gameUI = SpaceWarGameScreenUI(displaySize = Device.metrics.size, val shipType: ShipType) {

    class BandShipMenu(bandHeight: Float) {
        val ids = BandShipMenuID()
        val sizes = BandShipMenuSizes(bandHeight)
        class BandShipMenuSizes(bandHeight: Float) {
            private val bandHeightDp = bandHeight.toDp()
            private val shipNameText: Float = bandHeight * 0.95F
            private val shipNameTextSp: TextUnit = shipNameText.toSp()
            val shipNameTextStyle: TextStyle = TextStyle( fontSize = shipNameTextSp, fontWeight = FontWeight.Bold, )
            private val shipStatText: Float = bandHeight * 0.15F
            private val shipStatTextDp: TextUnit = shipStatText.toSp()
            val shipStatTextPadDp: Dp = (shipStatText * 0.3F).toDp()
            val shipStatStyle: TextStyle = TextStyle( fontSize = shipStatTextDp, fontWeight = FontWeight.SemiBold, )
            private val statsBoxBar: Size = (bandHeight * 0.15F).toSize()
            val statsBoxBarDp: DpSize = statsBoxBar.toDpSize()
            val statsBoxBarPadDp: Dp = (statsBoxBarDp.width.value * 0.35F).toDp()
            val statHeight: Dp = ((bandHeight / StatsIndication.values().size.toFloat()) * 0.85F).toDp()

            private val gameStatsText: Float = bandHeight * 0.28F
            private val gameStatsTextSp: TextUnit = gameStatsText.toSp()
            val gameStatTextStyle: TextStyle = TextStyle(fontSize = gameStatsTextSp, fontWeight = FontWeight.Bold)
            private val gameStatBoxHeight: Float = bandHeight * 0.30F
            val gameStatBoxHeightDp: Dp = gameStatBoxHeight.toDp()
        }
        data class BandShipMenuID(
            val shipName: String = "ship_name_id",
            val statsBox: String = "ship_stats_box_id",
            val statsTextBox: String = "ship_stats_text_id",
            val statsBarBox: String = "ship_stats_bar_id",
            val healthText: String = "HEALTH",
            val healthBar: String = "health_bar_id",
            val speedText: String = "SPEED",
            val speedBar: String = "speed_bar_id",
            val damageText: String = "DAMAGE",
            val damageBar: String = "damage_bar_id",
            val reloadText: String = "RELOAD",
            val reloadBar: String = "reload_bar_id",
            val gameStatsBarBox: String = "game_stats_id",
            val wins: String = "WINS",
            val loses: String = "LOSSES",
            val streak: String = "STREAK",
//            val statsText: String = "statText",
        )
    }
    class BodyShipMenu(sizeWithBand: Size) {
        val ids = BandShipBodyID()
        val sizes = BandShipMenuSizes(sizeWithBand)
        class BandShipMenuSizes(sizeWithBand: Size) {
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