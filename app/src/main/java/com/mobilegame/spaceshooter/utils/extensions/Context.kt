package com.mobilegame.spaceshooter.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mobilegame.spaceshooter.data.store.DataStoreInfoProvider

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

val Context.deviceNameDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreInfoProvider.DeviceName.name)
val Context.deviceStatsDataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreInfoProvider.DeviceStats.name)

//fun Context.getConnectivityManager() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager