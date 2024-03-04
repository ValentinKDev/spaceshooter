package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

data class SpaceShipSquare (
    override val life: Float = ShipMaxStats.life * 1F,
    override val speed: Float = ShipMaxStats.speed * 1F,
    override val damage: Float = ShipMaxStats.damage.toFloat(),
    override val reloadTime: Float = ShipMaxStats.reload * 1F,
    override val damageChargeRatio: Float = 1.18F,
    override val name: String = "SQUARE",
    override val magazineSize: Int = 6,
    override val shootingTimeInterval: Long = 120L,
    override val ammoRecoveryTime: Long = 450L,
    override val chargedProjectileType: ChargedProjectileType = ChargedProjectileType.Instant,
): ShipInfo
