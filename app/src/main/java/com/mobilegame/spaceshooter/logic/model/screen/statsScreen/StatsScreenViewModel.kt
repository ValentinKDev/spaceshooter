package com.mobilegame.spaceshooter.logic.model.screen.statsScreen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.repository.gameStats.GameStatsRepo
import com.mobilegame.spaceshooter.logic.uiHandler.screens.statsScreen.StatsScreenUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsScreenViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = "StatsScreenViewModel"
    private val statsRepo = GameStatsRepo(getApplication())
    val ui = StatsScreenUI()
    private val _presentableStats = MutableStateFlow(PresentableStats())
    val presentableStats: StateFlow<PresentableStats> = _presentableStats.asStateFlow()
//    lateinit var presentableStats: PresentableStats
    init {
        Log.i(TAG, "init: ")
        viewModelScope.launch {
            statsRepo.addingTotalWinRate()
            _presentableStats.value = PresentableStats(
                wins = statsRepo.getNumberOfWins(),
                losses = statsRepo.getNumberOfLoss(),
                highestStreakInfo = statsRepo.getBiggestStreakInfo(),
                totalWinRate = statsRepo.getTotalWinRate(),
                lastTenGamesWinRate = statsRepo.getPastTenGameWinRate(),
                mostUsedShipNameAndPercent = statsRepo.getPreferredShipNameAndPercent()
            )
        }
    }
    /**
     * 1 - number of defeat
     * 2 - number of win
     * 3 - longest streak and against who
     * 4 - most used ship with percentage
     * 5 - winrate on the past 20 or less games
     */
    class PresentableStats(
        val wins: Int = 0,
        val losses: Int = 0,
        val highestStreakInfo: Pair<String, Int> = Pair("", 0),
        val totalWinRate: Int = 0,
        val lastTenGamesWinRate: Int = 0,
        val mostUsedShipNameAndPercent: Pair<String, Int> = Pair("", 0),
    )
}