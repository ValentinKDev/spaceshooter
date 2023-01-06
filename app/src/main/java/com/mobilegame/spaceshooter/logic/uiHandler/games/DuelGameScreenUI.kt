package com.mobilegame.spaceshooter.logic.uiHandler.games

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUI
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize

class DuelGameScreenUI(displaySize: Size) {
    val position = PositionInGameScreen(displaySize)
    val sizes = SizesInGameScreen(displaySize)
    var spaceShip: SpaceShipIconUI = SpaceShipIconUI(shipBox = sizes.shipBox)

    class SizesInGameScreen(displaySize: Size) {
        var displayDp: DpSize = displaySize.toDpSize()
        var shipBox: Float = displaySize.height * 0.11F
        var shipBoxDp: Dp = shipBox.toDp()
        private val shipBoxCenter = shipBox / 2F
        val shipBoxCenterDp = shipBoxCenter.toDp()
        var displayDpDeltaBox: DpSize = DpSize(
            width = displayDp.width - shipBoxDp,
            height = displayDp.height - shipBoxDp,
        )
    }

    class PositionInGameScreen(displaySize: Size) {
        var pCenter: Offset = Offset(x = displaySize.width / 2F, y = displaySize.height / 2F)
        var pCenterDp: DpOffset =  pCenter.toDpOffset()
    }

    init {
        displayDataUI?.let {
            wLog("DuelGameScreenUI::init", "spaceShip")
            vLog("DuelGameScreenUI::init", "shipBox ${sizes.shipBox}")
            vLog("DuelGameScreenUI::init", "pCenter ${position.pCenter}")
        }

    }
}
