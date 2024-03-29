package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI

sealed class ShipType(val id: String, val info: ShipInfo) {
    object Circle: ShipType("CIRCLE", SpaceShipRound() as ShipInfo)
    object Square: ShipType("SQUARE", SpaceShipSquare() as ShipInfo)

    companion object {
        fun getList(): List<ShipType> = listOf(Circle, Square)
        fun getUiList(sizeShipBox: Size): List<SpaceShipIconUIInterface> {
            val list = getList()
            return list.map { getTypeShipUI(type = it, shipBox = sizeShipBox) }
        }
        fun getFromList(i: Int) = getList().getOrNull(i) ?: let {Square}
        fun getType(name: String): ShipType = getList().first { it.info.name == name }
        fun getTypeShipUI(i: Int, shipBox: Size): SpaceShipIconUIInterface = when (getFromList(i)) {
            Circle -> CircleSpaceShipIconUI(shipBox)
            Square -> SquareSpaceShipIconUI(shipBox)
        }
        fun getTypeShipUI(type: ShipType, shipBox: Size): SpaceShipIconUIInterface = when (type) {
            Circle -> CircleSpaceShipIconUI(shipBox)
            Square -> SquareSpaceShipIconUI(shipBox)
        }
    }
}