package com.mobilegame.spaceshooter.logic.uiHandler.screens.games

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize

class SpaceWarGameScreenUI(displaySize: Size, val shipType: ShipType) {
    val TAG = "SpaceWarGameScreenUI"
    val position = PositionInGameScreen(displaySize)
    val sizes = SizesInGameScreen(displaySize)
//    private val list = ShipType.LIST
    val userSpaceShip: SpaceShipIconUIInterface = ShipType.getTypeShipUI(shipType, sizes.shipViewBox)
    val listShipUI: List<SpaceShipIconUIInterface> = ShipType.getIconUIList(sizes.shipViewBox)
    val backgroundUI = BackgroundUI(shipType)

    class SizesInGameScreen(val displaySize: Size) {
        var displayDp: DpSize = displaySize.toDpSize()
        var shipViewBox: Size = (displaySize.height * 0.13F).toDpSize()
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
//    init {
//        Log.i(TAG, "init: ")
//        Log.i(TAG, "sizeShipBox: ${sizes.shipViewBox}")
//    }
}
