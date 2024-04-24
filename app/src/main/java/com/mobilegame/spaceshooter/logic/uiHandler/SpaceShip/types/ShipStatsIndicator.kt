package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types

class ShipStatsIndicator (
    val lifeIndicator: Int,
    val speedIndicator: Int,
    val damageIndicator: Int,
    val reloadIndicator: Int,
) {
    val map: Map<String, Int> = mapOf(
        Pair(LIST[0], lifeIndicator),
        Pair(LIST[1], speedIndicator),
        Pair(LIST[2], damageIndicator),
        Pair(LIST[3], reloadIndicator),
    )
    companion object {
        val LIST = listOf("HEALTH", "SPEED", "DAMAGE", "RELOAD")
    }
}