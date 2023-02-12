package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

data class SpaceShipRound(
    override val life: Float = ShipMaxStats.life * 1F,
    override val speed: Float = ShipMaxStats.speed * 1F,
    override val damage: Float = ShipMaxStats.damage * 1F,
    override val reloadTime: Float = ShipMaxStats.reload * 1F,
//    override val type: ShipType = ShipType.Circle,
    override val name: String = "ROUND",
    override val magazineSize: Int = 10
): ShipInfo