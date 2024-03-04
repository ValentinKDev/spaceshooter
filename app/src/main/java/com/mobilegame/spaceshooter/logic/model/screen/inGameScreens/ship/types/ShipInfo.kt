package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types

interface ShipInfo {
//    val type: ShipType
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
}