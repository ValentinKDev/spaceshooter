package com.mobilegame.spaceshooter.logic.model.screen.connection.wifiScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.WifiScreenUI
import kotlinx.coroutines.launch

class WifiScreenViewModel(application: Application): AndroidViewModel(application) {
    val ui = WifiScreenUI()
    private val userNameDataStore = DataStoreService.createDeviceName(application)
    var userName: String? = null
    init {
        viewModelScope.launch() {
            userName = userNameDataStore.getString(DataStoreNameProvider.DeviceName.NameKey)
        }
    }
}