package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.ShipStatsIndicator
import androidx.compose.ui.graphics.Color

interface ShipInfo {
    val name: String
    val life: Float
    val speed: Float
    val damage: Float
    val damageChargeRatio: Float
    val reloadTime: Float
    val magazineSize: Int
    val shootingTimeInterval: Long
    val ammoRecoveryTime: Long
    val chargedProjectileType: ChargedProjectileType
    val statsIndicator: ShipStatsIndicator
    val color: Color
}