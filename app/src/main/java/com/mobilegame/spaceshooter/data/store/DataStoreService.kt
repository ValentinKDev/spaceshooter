package com.mobilegame.spaceshooter.data.store

import android.content.Context
import com.mobilegame.spaceshooter.utils.extensions.deviceNameDataStore

interface DataStoreService {
    suspend fun getBoolean(key: String): Boolean?
    suspend fun putBoolean(key: String, value: Boolean)
    suspend fun delBoolean(key: String)

    suspend fun getString(key: String): String?
    suspend fun putString(key: String, value: String)
    suspend fun delString(key: String)

    suspend fun getInt(key: String): Int?
    suspend fun putInt(key: String, value: Int)
    suspend fun delInt(key: String)

    suspend fun getFloat(key: String): Float?
    suspend fun putFloat(key: String, value: Float)
    suspend fun delFloat(key: String)

    companion object {
        fun createDeviceName(context: Context): DataStoreService {
            return DataStoreImplementation(context.deviceNameDataStore)
        }
    }
}
