package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import android.util.Log
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.CircleSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.LaserySpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.SquareSpaceShipIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background.BackgroundUI

sealed class ShipType(val id: Int, val info: ShipInfo) {
    object Circle: ShipType(1, SpaceShipRound() as ShipInfo)
    object Square: ShipType(2, SpaceShipSquare() as ShipInfo)
    object Lasery: ShipType(3, SpaceShipLasery() as ShipInfo)

    companion object {
        val DEFAULT = Circle
        private val TAG = "ShipType"
        fun getList(): List<ShipType> = listOf(Circle, Square, Lasery)
        fun getIconUIList(sizeShipBox: Size): List<SpaceShipIconUIInterface> {
            val list = getList()
            return list.map { getTypeShipUI(type = it, shipBox = sizeShipBox) }
        }
//        fun getBackGroundUIList(): List<BackgroundUI> = getList().map { BackgroundUI(it) }
        fun getFromList(i: Int) = getList().getOrNull(i) ?: let {Square}
        fun getType(name: String): ShipType {
            Log.v(TAG, "getType: name = $name")
            val type: ShipType = getList().first { it.info.name == name }
            Log.v(TAG, "getType: type = $type")
            return type
        }
        fun getTypeShipUI(i: Int, shipBox: Size): SpaceShipIconUIInterface = when (getFromList(i)) {
            Circle -> CircleSpaceShipIconUI(shipBox)
            Square -> SquareSpaceShipIconUI(shipBox)
            Lasery -> LaserySpaceShipIconUI(shipBox)
        }
        fun getTypeShipUI(type: ShipType, shipBox: Size): SpaceShipIconUIInterface = when (type) {
            Circle -> CircleSpaceShipIconUI(shipBox)
            Square -> SquareSpaceShipIconUI(shipBox)
            Lasery -> LaserySpaceShipIconUI(shipBox)
        }
    }
}