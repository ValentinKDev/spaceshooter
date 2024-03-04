package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

data class SpaceShipRound(
    override val life: Float = ShipMaxStats.life * 1F,
    override val speed: Float = ShipMaxStats.speed * 1F,
    override val damage: Float = ShipMaxStats.damage.toFloat(),
    override val damageChargeRatio: Float = 1F,
    override val reloadTime: Float = ShipMaxStats.reload * 1F,
//    override val type: ShipType = ShipType.Circle,
    override val name: String = "ROUND",
    override val magazineSize: Int = 10,
    override val shootingTimeInterval: Long = 120L,
    override val ammoRecoveryTime: Long = 450L,
    override val chargedProjectileType: ChargedProjectileType = ChargedProjectileType.Rafal
): ShipInfo