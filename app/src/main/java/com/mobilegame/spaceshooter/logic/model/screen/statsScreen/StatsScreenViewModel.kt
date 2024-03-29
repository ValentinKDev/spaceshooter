package com.mobilegame.spaceshooter.logic.model.screen.statsScreen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.data.store.DataStoreService

class StatsScreenViewModel(application: Application): AndroidViewModel(application) {
    private val dataStore = DataStoreService.deviceStats(application.applicationContext)
    private val TAG = "StatsScreenViewModel"
    init { Log.i(TAG, "init: ") }
}