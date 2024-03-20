package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types

class ShipStatsIndicator (
    val lifeIndicator: Int,
    val speedIndicator: Int,
    val damageIndicator: Int,
    val reloadIndicator: Int,
) {
    val map: Map<String, Int> = mapOf(
        Pair(ShipStatsIndicator.LIST[0], lifeIndicator),
        Pair(ShipStatsIndicator.LIST[1], speedIndicator),
        Pair(ShipStatsIndicator.LIST[2], damageIndicator),
        Pair(ShipStatsIndicator.LIST[3], reloadIndicator),
    )
    companion object {
        val LIST = listOf("HEALTH", "SPEED", "DAMAGE", "RELOAD")
        const val MAX_STATS = 6
    }
}