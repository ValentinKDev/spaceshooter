package com.mobilegame.spaceshooter.data.store

import android.content.Context
import com.mobilegame.spaceshooter.utils.extensions.deviceNameDataStore

interface DataStoreService {
    suspend fun getBoolean(key: DataStoreKey): Boolean?
    suspend fun putBoolean(key: DataStoreKey, value: Boolean)
    suspend fun delBoolean(key: DataStoreKey)

    suspend fun getString(key: DataStoreKey): String?
    suspend fun putString(key: DataStoreKey, value: String)
    suspend fun delString(key: DataStoreKey)

    suspend fun getInt(key: DataStoreKey): Int?
    suspend fun putInt(key: DataStoreKey, value: Int)
    suspend fun delInt(key: DataStoreKey)

    suspend fun getFloat(key: DataStoreKey): Float?
    suspend fun putFloat(key: DataStoreKey, value: Float)
    suspend fun delFloat(key: DataStoreKey)

    companion object {
        fun createDeviceName(context: Context): DataStoreService {
            return DataStoreImplementation(context.deviceNameDataStore)
        }
    }
}
