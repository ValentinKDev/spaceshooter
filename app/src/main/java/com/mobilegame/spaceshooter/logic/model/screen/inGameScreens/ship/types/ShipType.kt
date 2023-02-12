package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

sealed class ShipType(val name: String, val info: ShipInfo) {
    object Circle: ShipType("CIRCLE", SpaceShipRound() as ShipInfo)
    object Square: ShipType("SQUARE", SpaceShipSquare() as ShipInfo)
}