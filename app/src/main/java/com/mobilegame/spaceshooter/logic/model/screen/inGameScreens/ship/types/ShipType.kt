package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.ShipStatsIndicator
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI

sealed class ShipType(val name: String, val info: ShipInfo) {
    object Circle: ShipType("CIRCLE", SpaceShipRound() as ShipInfo)
    object Square: ShipType("SQUARE", SpaceShipSquare() as ShipInfo)

    companion object {
        val LIST: List<ShipType> = listOf(Circle, Square)
        fun getFromList(i: Int) = LIST.getOrNull(i) ?: let {Square}
//        fun getTypeShipType(i: Int): SpaceShipIconUIInterface = get
        fun getTypeShipUI(i: Int, shipBox: Size): SpaceShipIconUIInterface = when (getFromList(i)) {
            Circle -> CircleSpaceShipIconUI(shipBox)
            Square -> SquareSpaceShipIconUI(shipBox)
        }
        fun getTypeShipUI(type: ShipType, shipBox: Size): SpaceShipIconUIInterface = when (type) {
            Circle -> CircleSpaceShipIconUI(shipBox)
            Square -> SquareSpaceShipIconUI(shipBox)
        }
//        fun getStats(type: ShipType) = when (type) {
//            Circle -> type.info as SpaceShipSquare
//            Square -> type.info as SpaceShipRound
//        }
    }
}