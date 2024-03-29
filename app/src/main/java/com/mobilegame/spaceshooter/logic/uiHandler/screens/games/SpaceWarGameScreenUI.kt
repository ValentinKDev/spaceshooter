package com.mobilegame.spaceshooter.logic.uiHandler.screens.games

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import com.mobilegame.spaceshooter.utils.extensions.toSize

class SpaceWarGameScreenUI(displaySize: Size, shipType: ShipType, enemiesShipType: ShipType) {
    val TAG = "SpaceWarGameScreenUI"
    val position = PositionInGameScreen(displaySize)
    val sizes = SizesInGameScreen(displaySize)
//    private val list = ShipType.LIST
    val userSpaceShip: SpaceShipIconUIInterface = ShipType.getTypeShipUI(shipType, sizes.shipViewBox)
    val enemiesSpaceShip: MutableList<Pair<ShipType ,SpaceShipIconUIInterface>> = mutableListOf(Pair(enemiesShipType, ShipType.getTypeShipUI(enemiesShipType, sizes.shipViewBox)))
    val listShipUI: List<SpaceShipIconUIInterface> = ShipType.getUiList(sizes.shipViewBox)

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
        Log.i(TAG, "init: ")
        Log.i(TAG, "sizeShipBox: ${sizes.shipViewBox}")
    }
}
