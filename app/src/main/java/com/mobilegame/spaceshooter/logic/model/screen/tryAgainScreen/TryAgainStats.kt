package com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen

import android.util.Log
import com.google.gson.Gson
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.repository.gameStats.MyDate
import com.mobilegame.spaceshooter.utils.TypeListString
import com.mobilegame.spaceshooter.utils.TypeTryAgainStats

data class TryAgainStats (
    var wins: Int,
    var losses: Int,
    var streak: Int,
    var gameResult: GameResult,
    var shipName: String,
    var enemiesName: String,
    val currentDate: String,
) {
    private val TAG = "TryAgainStats"
    fun updateWith(result: GameResult, shipName: String, opponentName: String) {
        Log.v(TAG, "updateWith: ${gameResult.name} with $shipName against $opponentName")
        this.gameResult = result
        this.shipName = shipName
        if (enemiesName == DEFAULT_OPPONENENT_NAME) enemiesName = opponentName
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
    fun serialize(): String {
//        val gson = Gson.
        val listParam: List<String> = listOf(
            this.wins.toString(),
            this.losses.toString(),
            this.streak.toString(),
            this.gameResult.name.toString(),
            this.shipName.toString(),
            this.enemiesName.toString(),
            this.currentDate.toString()
        )
        return Gson().toJson(listParam, TypeListString)
    }
    fun isEmpty(): Boolean = this == EMPTY_TRY_AGAIN_STATS
    companion object {
        private val TAG = "TryAgainStats"
        fun deserialize(argStr: String): TryAgainStats {
//            Log.e(TAG, "deserialize: $argStr")
            val listParams: List<String> = Gson().fromJson(argStr, TypeListString)
//            Log.e(TAG, "deserialize: $listParams")
//            Log.e(TAG, "deserialize: ${listParams[1]}")
//            Log.e(TAG, "deserialize: ${listParams[3]}")
            val wins = listParams[0].toInt()
            val losses = listParams[1].toInt()
            val streak = listParams[2].toInt()
            val gameResult = GameResult.valueOf(listParams[3])
            val shipName = listParams[4]
            val opponentName = listParams[5]
            val date = listParams[6]
            return TryAgainStats(
                wins = wins,
                losses = losses,
                streak = streak,
                gameResult = gameResult,
                shipName = shipName,
                enemiesName = opponentName,
                currentDate = date,
            )
        }
        val DEFAULT_OPPONENENT_NAME = "unknown"
        val EMPTY_TRY_AGAIN_STATS = TryAgainStats(
            wins = 0,
            losses = 0,
            streak = 0,
            gameResult = GameResult.OnGoing,
            shipName = ShipType.DEFAULT.info.name,
            enemiesName = DEFAULT_OPPONENENT_NAME,
            currentDate = MyDate.currentStr(),
        )
    }
}