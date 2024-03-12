package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

class SpaceShipRound: ShipInfo {
    override val life: Float = ShipBasicStats.life * 1F
    override val speed: Float = ShipBasicStats.speed * 1F
    override val damage: Float = ShipBasicStats.damage.toFloat()
    override val damageChargeRatio: Float = 1F
    override val reloadTime: Float = ShipBasicStats.reload * 1F
    override val name: String = "ROUND"
    override val magazineSize: Int = 10
    override val shootingTimeInterval: Long = 120L
    override val ammoRecoveryTime: Long = 450L
    override val chargedProjectileType: ChargedProjectileType = ChargedProjectileType.Rafal
}