package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.ShipStatsIndicator
import com.mobilegame.spaceshooter.presentation.theme.MyColor

class SpaceShipLasery (
) : ShipInfo {
    private val TAG = "SpaceShipLasery"
    override val name: String = "LASERY"
    override val life: Float = ShipBasicStats.life * 1.2F
    override val speed: Float = ShipBasicStats.speed * 0.9F
//    override val damage: Float = ShipBasicStats.damage * 0.8F
    override val damage: Float = ShipBasicStats.damage * 1F
    override val damageChargeRatio: Float = 0.08F
    override val reloadTime: Float = ShipBasicStats.reload * 1F
    override val magazineSize: Int = 4
    override val shootingTimeInterval: Long = ShipBasicStats.shootingTimeInterval
    override val ammoRecoveryTime: Long = ShipBasicStats.ammoRecoveryTime
    override val chargedProjectileType: ChargedProjectileType = ChargedProjectileType.Instant
    override val color: Color = MyColor.laseryShip
    override val statsIndicator: ShipStatsIndicator = ShipStatsIndicator(
        lifeIndicator = 3,
        speedIndicator = 2,
        damageIndicator = 2,
        reloadIndicator = 2,
    )

    companion object {
        const val NORMAL_LASER_LIFE = 350L
    }
}
