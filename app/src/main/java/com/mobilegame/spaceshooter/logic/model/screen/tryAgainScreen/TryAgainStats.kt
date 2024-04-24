package com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen

import com.google.gson.Gson
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType

class TryAgainStats (
    var wins: Int,
    var losses: Int,
    var streak: Int,
    var lastGame: GameResult,
    var lastShipName: String,
) {
    fun updateWith(result: GameResult, shipName: String) {
        this.lastGame = result
        this.lastShipName = shipName
        when (result) {
            GameResult.VICTORY -> {
                this.wins += 1
                this.streak += 1
            }
            GameResult.DEFEAT -> {
                this.losses += 1
                this.streak = 0
            }
        }
    }
//    fun win(): TryAgainStats = TryAgainStats(this.wins + 1, this.losses, this.streak + 1)
//    fun loose(): TryAgainStats = TryAgainStats(this.wins, this.losses + 1, 0)

    fun serialize(): String = Gson().toJson(this)
    companion object {
        val EMPTY_TRY_AGAIN_STATS = TryAgainStats(0,0,0, GameResult.OnGoing, ShipType.DEFAULT.info.name)
        fun deserialize(argStr: String): TryAgainStats = Gson().fromJson(argStr, TryAgainStats::class.java)
    }
}