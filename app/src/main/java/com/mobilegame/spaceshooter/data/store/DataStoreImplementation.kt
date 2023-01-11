package com.mobilegame.spaceshooter.data.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.first

class DataStoreImplementation(private val dataStore: DataStore<Preferences>) : DataStoreService {

    override suspend fun putString(key: DataStoreKey, value: String) {
        val preferencesKey = stringPreferencesKey(key.value)
        dataStore.edit { preferences -> preferences[preferencesKey] = value }
    }
    override suspend fun putInt(key: DataStoreKey, value: Int) {
        val preferencesKey = intPreferencesKey(key.value)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }
    override suspend fun putFloat(key: DataStoreKey, value: Float) {
        val preferencesKey = floatPreferencesKey(key.value)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }
    override suspend fun putBoolean(key: DataStoreKey, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key.value)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: DataStoreKey): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key.value)
            val preferences = dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
    override suspend fun getInt(key: DataStoreKey): Int? {
        return try {
            val preferencesKey = intPreferencesKey(key.value)
            val preferences = dataStore.data.first()
            preferences[preferencesKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
    override suspend fun getFloat(key: DataStoreKey): Float? {
        return try {
            val preferencesKey = floatPreferencesKey(key.value)
            val preferences = dataStore.data.first()
            preferences[preferencesKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
    override suspend fun getBoolean(key: DataStoreKey): Boolean? {
        return try {
            val preferencesKey = booleanPreferencesKey(key.value)
            val preferences = dataStore.data.first()
            preferences[preferencesKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

    override suspend fun delString(key: DataStoreKey) {
        val preferencesKey = stringPreferencesKey(key.value)

        dataStore.edit { preferences -> preferences.remove(preferencesKey) }
    }
    override suspend fun delInt(key: DataStoreKey) {
        val preferencesKey = intPreferencesKey(key.value)

        dataStore.edit { preferences -> preferences.remove(preferencesKey) }
    }
    override suspend fun delFloat(key: DataStoreKey) {
        val preferencesKey = floatPreferencesKey(key.value)

        dataStore.edit { preferences -> preferences.remove(preferencesKey)}
    }
    override suspend fun delBoolean(key: DataStoreKey) {
        val preferencesKey = floatPreferencesKey(key.value)

        dataStore.edit { preferences -> preferences.remove(preferencesKey) }
    }
}