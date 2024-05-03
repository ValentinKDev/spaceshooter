package com.mobilegame.spaceshooter.logic.repository.gameStats

data class GameStatsEssentials(
    var wins: Int,
    var losses: Int,
    var streak: Int,
    val enemiesName: String,
)