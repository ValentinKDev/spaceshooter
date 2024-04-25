package com.mobilegame.spaceshooter.logic.model.screen.statsScreen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.uiHandler.screens.statsScreen.StatsScreenUI

class StatsScreenViewModel(application: Application): AndroidViewModel(application) {
    private val dataStore = DataStoreService.deviceStats(application.applicationContext)
    val ui = StatsScreenUI()
    private val TAG = "StatsScreenViewModel"
    init { Log.i(TAG, "init: ") }
    /**
     * 1 - number of defeat
     * 2 - number of win
     * 3 - longest streak and against who
     * 4 - most used ship with percentage
     * 5 - winrate on the past 20 or less games
     */
}