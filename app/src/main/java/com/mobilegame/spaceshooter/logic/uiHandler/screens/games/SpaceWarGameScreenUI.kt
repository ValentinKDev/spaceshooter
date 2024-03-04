package com.mobilegame.spaceshooter.logic.uiHandler.screens.games

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import com.mobilegame.spaceshooter.utils.extensions.toSize

class SpaceWarGameScreenUI(displaySize: Size, shipType: ShipType = ShipType.Square) {
    val position = PositionInGameScreen(displaySize)
    val sizes = SizesInGameScreen(displaySize)
    var spaceShip: SpaceShipIconUIInterface = when (shipType) {
        ShipType.Square -> SquareSpaceShipIconUI(shipViewBox = sizes.shipViewBox)
        ShipType.Circle -> CircleSpaceShipIconUI(shipBox = sizes.shipViewBox)
    }

    class SizesInGameScreen(displaySize: Size) {
        var displayDp: DpSize = displaySize.toDpSize()
        var shipViewBox: Size = (displaySize.height * 0.13F).toSize()
        var shipViewBoxDp: DpSize = shipViewBox.toDpSize()
        private val shipBoxCenter = shipViewBox.width / 2F
        val shipBoxCenterDp = shipBoxCenter.toDp()
        var displayDpDeltaBox: DpSize = DpSize(
            width = displayDp.width - shipViewBoxDp.width,
            height = displayDp.height - shipViewBoxDp.height,
        )
    }

    class PositionInGameScreen(displaySize: Size) {
        var pCenter: Offset = Offset(x = displaySize.width / 2F, y = displaySize.height / 2F)
        var pCenterDp: DpOffset =  pCenter.toDpOffset()
    }

    init {
        displayDataUI?.let {
            wLog("DuelGameScreenUI::init", "spaceShip")
            vLog("DuelGameScreenUI::init", "shipBox ${sizes.shipViewBox}")
            vLog("DuelGameScreenUI::init", "displaySize $displaySize")
            vLog("DuelGameScreenUI::init", "pCenter ${position.pCenter}")
        }

    }
}
