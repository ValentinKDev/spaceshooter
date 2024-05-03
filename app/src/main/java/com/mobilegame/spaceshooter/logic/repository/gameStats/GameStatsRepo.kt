package com.mobilegame.spaceshooter.logic.repository.gameStats

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mobilegame.spaceshooter.data.store.DataStoreKey
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.utils.TypeListGameResult
import com.mobilegame.spaceshooter.utils.TypeListPairStringInt
import com.mobilegame.spaceshooter.utils.TypeListString
import com.mobilegame.spaceshooter.utils.TypePairStringInt
import com.mobilegame.spaceshooter.utils.extensions.getOrNull
import com.mobilegame.spaceshooter.utils.extensions.indexOfFirstOrNullOf
import com.mobilegame.spaceshooter.utils.extensions.indexOrNullOf

class GameStatsRepo(val context: Context) {
    private val TAG = "GameStatsRepo"
    private val dataStore = DataStoreService.deviceStats(context)
    private val gson = Gson()
    private val ERROR_INDEX = 15

    suspend fun addGameResult(gameStats: TryAgainStats) {
        Log.v(TAG, "addGameResult: against ${gameStats.enemiesName}")
        updateListOfOpponentWith(gameStats)
        addGameResultToLastTenGamesList(gameStats.gameResult)
        addWinAndLossToTotal(gameStats)
        addingTotalWinRate()
        addingStreak(gameStats)
        addPrefShipStatsList(gameStats)

//        Log.e(TAG, "addGameResult end --> listOfOpponents ")
//        Log.e(TAG, "addGameResult end --> ${getListOfOpponents()}")
//        Log.e(TAG, "addGameResult end --> listOfHistories ")
//        Log.e(TAG, "addGameResult end --> ${getAllTheHistoryAgainstOpponent()}")
//        val index = removeOldestOpponentAndGetIndex()
//        Log.e(TAG, "addGameResult end --> oldestIndex $index ")
//        Log.e(TAG, "addGameResult end --> listOfOpponents ")
//        Log.e(TAG, "addGameResult end --> ${getListOfOpponents()}")
//        Log.e(TAG, "addGameResult end --> listOfHistories ")
//        Log.e(TAG, "addGameResult end --> ${getAllTheHistoryAgainstOpponent()}")

        //todo: test a remove
    }
    private suspend fun getListOfOpponents(): List<String> {
        val listStr: String? = dataStore.getString(DataStoreKey.OPPONENT_NAME_LIST_KEY)
        val list: List<String> = listStr?.let {
            gson.fromJson(it, TypeListString)
        } ?: let {
            val emptyList: MutableList<String> = mutableListOf()
            for (index in 0 until ERROR_INDEX) {
                emptyList.add("")
            }
            emptyList.toList()
        }
        Log.v(TAG, "getListOfOpponents: $list")
        return list
    }

    private suspend fun updateListOfOpponentWith(gameStats: TryAgainStats) {
        Log.v(TAG, "updateListOfOpponentWith: ")
//        val namesMutableList: MutableList<String> = getListOfOpponents().toMutableList()
        val index: Int = updateOpponentNamesListAndGetIndex(gameStats.enemiesName)
//        val index: Int = namesMutableList.indexOrNullOf(gameStats.enemiesName)
//            ?: namesMutableList.indexOfFirstOrNullOf("")?.let {
//                namesMutableList[it] = gameStats.enemiesName
//                it
//            }?: removeOldestOpponentAndGetIndex().let {
//                namesMutableList[it] = gameStats.enemiesName
//                it
//            }
//        val nameListStr: String = gson.toJson(namesMutableList.toList(), TypeListString)
//        dataStore.putString(DataStoreKey.OPPONENT_NAME_LIST_KEY, nameListStr)
        updateOpponentAtIndex(index, gameStats)
//        Log.v(TAG, "updateListOfOpponentWith: ${getAllTheHistoryAgainstOpponent()}")
    }
    private suspend fun updateOpponentNamesListAndGetIndex(opponentName: String): Int {
        val namesMutableList: MutableList<String> = getListOfOpponents().toMutableList()
        val index: Int = namesMutableList.indexOrNullOf(opponentName)
            ?: namesMutableList.indexOfFirstOrNullOf("")?.let {
                namesMutableList[it] = opponentName
                it
            }?: removeOldestOpponentAndGetIndex().let {
                namesMutableList[it] = opponentName
                it
            }
        val nameListStr: String = gson.toJson(namesMutableList.toList(), TypeListString)
        dataStore.putString(DataStoreKey.OPPONENT_NAME_LIST_KEY, nameListStr)
        return index
    }

    private suspend fun updateOpponentAtIndex(index: Int, gameStats: TryAgainStats) {
//        Log.v(TAG, "updateOpponentAtIndex: ")
        val namesList: MutableList<String> = getListOfOpponents().toMutableList()
        namesList.getOrNull(index)?.let { name ->
//            val gameHistory: TryAgainStats = getOpponentHistoryOrDefault(name)
            val gameHistory: TryAgainStats = getOpponentHistoryOrNull(name, index) ?: TryAgainStats(
                wins = 0,
                losses = 0,
                streak = 0,
                gameResult = GameResult.OnGoing,
                shipName = gameStats.shipName,
                enemiesName = gameStats.enemiesName,
                currentDate = gameStats.currentDate,
            )
//            if (gameHistory.isEmpty()) gameHistory.updateWith(gameStats.)
//            gameHistory?.let { it = TryAgainStats.EMPTY_TRY_AGAIN_STATS }
//            gameHistory?.let {
//            gameHistory?.let {
                when (gameStats.gameResult) {
                    GameResult.VICTORY -> {
                        if (gameHistory.streak < gameStats.streak) gameHistory.streak = gameStats.streak
                        gameHistory.wins += 1
                    }
                    GameResult.DEFEAT -> { gameHistory.losses += 1 }
                    GameResult.OnGoing -> { Log.e(TAG, "updateOpponentAtIndex: gameStats.result ${gameStats.gameResult}")}
                }
                val gameHistoryStr: String = gameHistory.serialize()
//                Log.v(TAG, "updateOpponentAtIndex: gameHistory $gameHistoryStr")
                dataStore.putString(DataStoreKey.opponentHistoryKey(index), gameHistoryStr)
//            }?: let {
//                Log.e(TAG, "updateOpponentAtIndex: ERROR can not find element in histories list at index $index with name $name", )
//            }
//            Log.v(TAG, "updateOpponentAtIndex: gameHistory $gameHistory")
        } ?: let {
            Log.e(TAG, "updateOpponentAtIndex: ERROR can not find element in nameList at index $index")
        }
        Log.v(TAG, "updateOpponentAtIndex: namesList $namesList")
    }
    private suspend fun clearOpponentNameAndHistoryGetIndex(name: String): Int {
        val nameMutableList: MutableList<String> = getListOfOpponents().toMutableList()
        val index: Int? = nameMutableList.indexOrNullOf(name)
        return index?.let { i ->
            nameMutableList[i] = ""
            val nameMutableListStr: String = gson.toJson(nameMutableList.toList(), TypeListString)
            dataStore.putString(DataStoreKey.OPPONENT_NAME_LIST_KEY, nameMutableListStr)
            val stats: TryAgainStats = TryAgainStats.EMPTY_TRY_AGAIN_STATS
            val statsStr: String = stats.serialize()
            dataStore.putString(DataStoreKey.opponentHistoryKey(i), statsStr)
            i
        } ?: let {
            Log.e(TAG, "clearOpponentNameAndHistoryGetIndex: can not find name $name")
            ERROR_INDEX
        }
    }
    private suspend fun removeOldestOpponentAndGetIndex(): Int {
        val allOpponentHistoryGameList: List<TryAgainStats> = getAllTheHistoryAgainstOpponent()
        var oldestDate: String = MyDate.currentStr()
        var nameToRemove: String? = null
        allOpponentHistoryGameList.forEach { stats ->
            if (MyDate(stats.currentDate).isOlderThan(oldestDate)) {
                oldestDate = stats.currentDate
                nameToRemove = stats.enemiesName
            }
        }
        return nameToRemove?.let { name ->
            clearOpponentNameAndHistoryGetIndex(name)
        } ?: let {
            Log.e(TAG, "removeOldestOpponent: function called but can not find any opponent history to remove")
            ERROR_INDEX
        }
    }
    private suspend fun getOpponentHistoryOrNull(name: String, index: Int): TryAgainStats? {
        val opponentHistoryStr: String? = dataStore.getString(DataStoreKey.opponentHistoryKey(index))
        val opponentHistory: TryAgainStats? = opponentHistoryStr?.let { TryAgainStats.deserialize(it) }
        opponentHistory?.let {
            if (it.enemiesName != name) Log.e( TAG, "getOpponentHistoryOrNull: ERROR at index $index name does not correspond ")
        }
        return opponentHistory
    }
    suspend fun getOpponentHistoryOrDefault(enemiesName: String): TryAgainStats {
        val nameList: List<String> = getListOfOpponents()
        val name: String? = nameList.find { it == enemiesName }
        return name?.let {
            val index: Int = nameList.indexOf(it)
            getOpponentHistoryOrNull(it, index)
        } ?: TryAgainStats.EMPTY_TRY_AGAIN_STATS
    }
    suspend fun getAllTheHistoryAgainstOpponent(): List<TryAgainStats> {
        val opponentList: List<String> = getListOfOpponents()
        val allHistoryMutableList: MutableList<TryAgainStats> = mutableListOf()
        opponentList.forEachIndexed { index, name ->
            val gameHistory: TryAgainStats? = getOpponentHistoryOrNull(name, index)
            gameHistory?.let { allHistoryMutableList.add(it) } ?: let {
                if (name.isNotEmpty()) Log.e(TAG, "getAllTheHistoryAgainstOpponent: can not find $index $name")
            }
        }
        return allHistoryMutableList.toList()
    }

    private suspend fun getPrefShipStatsList(): List<Pair<String, Int>> {
        val listStr: String? = dataStore.getString(DataStoreKey.PREFERRED_SHIP_KEY)
        val list: List<Pair<String, Int>> = listStr?.let {
            gson.fromJson(it, TypeListPairStringInt)
        } ?: ShipType.getList().map { Pair(it.info.name, 0) }
        return list
    }
    private suspend fun addPrefShipStatsList(gameStats: TryAgainStats) {
        Log.v(TAG, "addPrefShipStatsList: ")
        val list = getPrefShipStatsList().toMutableList()
        val newPair: Pair<String, Int>? = list.find { it.first == gameStats.shipName }
        newPair?.let {
            val i: Int = list.indexOfFirst { it.first == gameStats.shipName }
            list[i] = Pair(it.first, it.second + 1)
            val listStr = gson.toJson(list.toList(), TypeListPairStringInt)
            dataStore.putString(DataStoreKey.PREFERRED_SHIP_KEY, listStr)
        } ?: let { Log.e( TAG, "addPrefShipStatsList: ERROR can not find ${gameStats.shipName} in $list", )}
    }
    suspend fun getPreferredShipNameAndPercent(): Pair<String, Int> {
        val listStr: String? = dataStore.getString(DataStoreKey.PREFERRED_SHIP_KEY)
        return listStr?.let {
            val list: List<Pair<String, Int>> = gson.fromJson(it, TypeListPairStringInt)
            var totalGamePlayed: Int = 0
            var mostPlayedShipName: String = ""
            val mostPlayedShipGames: Int = 0
            list.forEach { pair ->
                if (mostPlayedShipGames < pair.second) mostPlayedShipName = pair.first
                totalGamePlayed += pair.second
            }
            val percent: Int = ((mostPlayedShipGames.toFloat() / totalGamePlayed.toFloat()) * 100F).toInt()
            Pair(mostPlayedShipName, percent)
        }?: let {
            Pair( ShipType.DEFAULT.info.name, 0 )
        }

    }
    suspend fun getPastTenGameWinRate(): Int {
        Log.v(TAG, "getPastTenGameWinRate: ")
        val lastTenGameResultList = getLastTenGameList()
        val losses: Int = lastTenGameResultList.filter { it == GameResult.DEFEAT }.size
//        if (losses == 0) losses = 1
        val wins: Int = lastTenGameResultList.filter { it == GameResult.VICTORY }.size
        val winRate: Int =  ((wins.toFloat() / (losses.toFloat() + wins.toFloat())) * 100).toInt()
        return winRate
    }
    suspend fun addingTotalWinRate() {
        Log.v(TAG, "addingTotalWinRate: ")
        val losses: Int = getNumberOfLoss()
        val wins: Int = getNumberOfWins()
        val winRate: Int =  ((wins.toFloat() / (wins.toFloat() + losses.toFloat())) * 100).toInt()
        dataStore.putInt(DataStoreKey.WIN_RATE_KEY, winRate)
    }
    suspend fun getTotalWinRate(): Int = dataStore.getInt(DataStoreKey.WIN_RATE_KEY) ?: 0
    suspend fun getBiggestStreakInfo(): Pair<String, Int> {
        val lastStreakInfoStr: String? = dataStore.getString(DataStoreKey.LONGEST_STREAK_KEY)
        return lastStreakInfoStr?.let { gson.fromJson(it, TypePairStringInt) } ?: Pair("", 0)
    }
    private suspend fun addingStreak(gameStats: TryAgainStats) {
        Log.v(TAG, "addingStreak: ")
        val lastStreakInfo: Pair<String, Int> = getBiggestStreakInfo()
        val newStreakInfo: Pair<String, Int> = Pair(gameStats.enemiesName, gameStats.streak)
        val newStreakInfoStr: String = gson.toJson(newStreakInfo, TypePairStringInt)
        if (lastStreakInfo.second < gameStats.streak) dataStore.putString(DataStoreKey.LONGEST_STREAK_KEY, newStreakInfoStr)
    }
    private suspend fun addWinAndLossToTotal(gameStats: TryAgainStats) {
        Log.v(TAG, "addWinAndLossToTotal: ")
        //todo : factorise this ?
        when (gameStats.gameResult) {
            GameResult.VICTORY -> { addWinToTotalNumber() }
            GameResult.DEFEAT -> { addLossToTotalNumber() }
            GameResult.OnGoing -> { Log.e(TAG, "addGameResult: ERROR gameresult is ${gameStats.gameResult}")}
        }
    }
    suspend fun getNumberOfWins(): Int = dataStore.getInt(key = DataStoreKey.VICTORIOUS_GAMES_KEY) ?: 0
    private suspend fun addWinToTotalNumber() {
        val wins: Int = getNumberOfWins()
        val newNumber = wins + 1
        Log.i(TAG, "addWinToTotalNumber: new number of wins $newNumber")
        dataStore.putInt(DataStoreKey.VICTORIOUS_GAMES_KEY, newNumber)
    }
    suspend fun getNumberOfLoss(): Int = dataStore.getInt(key = DataStoreKey.DEFEATED_GAMES_KEY) ?: 0
    private suspend fun addLossToTotalNumber() {
        val losses: Int = getNumberOfLoss()
        val newNumber = losses + 1
        Log.i(TAG, "addWinToTotalNumber: new number of losses $newNumber")
        dataStore.putInt(DataStoreKey.DEFEATED_GAMES_KEY, newNumber)
    }

    private suspend fun addGameResultToLastTenGamesList(result: GameResult) {
        Log.v(TAG, "addGameResultToLastTenGamesList: ")
        val oldListStr: String? = dataStore.getString(DataStoreKey.LAST_TEN_GAMES_KEY)
        val oldList: List<GameResult> = oldListStr?.let { gson.fromJson(it, TypeListGameResult) } ?: emptyList<GameResult>()
        val newList: MutableList<GameResult> = mutableListOf<GameResult>()
        newList.add(result)
        oldList.forEachIndexed {i, element -> if (i < 8) newList.add(element) }
        val newListStr: String = gson.toJson(newList, TypeListGameResult)
//        Log.i(TAG, "addGameResultToLastTenGamesList: list size ${newList.size}")
//        Log.i(TAG, "addGameResultToLastTenGamesList: list $newList")
        dataStore.putString(DataStoreKey.LAST_TEN_GAMES_KEY, newListStr)
    }
    suspend fun getLastTenGameList(): List<GameResult> {
        val listStr: String? = dataStore.getString(DataStoreKey.LAST_TEN_GAMES_KEY)
        val list: List<GameResult> = listStr?.let { gson.fromJson(it, TypeListGameResult) } ?: emptyList<GameResult>()
        Log.i(TAG, "getLastTenGameList: list $list")
        return list
    }
}