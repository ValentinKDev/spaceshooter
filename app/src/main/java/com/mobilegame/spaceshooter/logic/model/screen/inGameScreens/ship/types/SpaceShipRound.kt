package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import android.util.Log
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.ShipStatsIndicator

class SpaceShipRound: ShipInfo {
    val TAG = "SpaceShipRound"
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
    override val statsIndicator: ShipStatsIndicator = ShipStatsIndicator(
        lifeIndicator = 4,
        speedIndicator = 5,
        damageIndicator = 3,
        reloadIndicator = 5,
    )
    init { Log.i(TAG, "init : ") }
}