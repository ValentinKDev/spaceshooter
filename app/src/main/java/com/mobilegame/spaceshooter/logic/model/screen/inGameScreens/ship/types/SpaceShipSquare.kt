package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.ShipStatsIndicator
import com.mobilegame.spaceshooter.presentation.theme.MyColor

class SpaceShipSquare(
) : ShipInfo {
    private val TAG = "SpaceShipSquare"
    override val life: Float = ShipBasicStats.life * 1.40F
    override val speed: Float = ShipBasicStats.speed * 1F
    override val damage: Float = ShipBasicStats.damage.toFloat()
    override val reloadTime: Float = ShipBasicStats.reload * 1F
    override val damageChargeRatio: Float = 1.18F
    override val name: String = "SQUARE"
    override val magazineSize: Int = 6
    override val shootingTimeInterval: Long = 120L
    override val ammoRecoveryTime: Long = 450L
    override val chargedProjectileType: ChargedProjectileType = ChargedProjectileType.Instant
    override val statsIndicator: ShipStatsIndicator = ShipStatsIndicator(
        lifeIndicator = 6,
        speedIndicator = 3,
        damageIndicator = 4,
        reloadIndicator = 3,
    )
    override val color: Color = MyColor.squareShip
    init { Log.i(TAG, "init: ") }
}
