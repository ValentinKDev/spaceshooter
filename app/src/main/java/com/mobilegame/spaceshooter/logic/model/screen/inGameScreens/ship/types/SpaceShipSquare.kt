package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

data class SpaceShipSquare (
    override val life: Float = ShipMaxStats.life * 1F,
    override val speed: Float = ShipMaxStats.speed * 1F,
    override val damage: Float = ShipMaxStats.damage * 1F,
    override val reloadTime: Float = ShipMaxStats.reload * 1F,
//    override val type: ShipType = ShipType.Square,
    override val name: String = "SQUARE",
    override val magazineSize: Int = 6,
): ShipInfo
