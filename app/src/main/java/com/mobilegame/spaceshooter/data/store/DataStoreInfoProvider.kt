package com.mobilegame.spaceshooter.data.store

internal sealed class DataStoreInfoProvider(val pref: String) {
    object DeviceName : DataStoreInfoProvider("device_name_datastore") {
        val key = DataStoreKey("device_name_datastore_key")
    }
    object DeviceStats : DataStoreInfoProvider("device_stats_datastore") {
        val key = DataStoreKey("device_stats_datastore_key")
    }
    object PlayerLooseNumber : DataStoreInfoProvider("player_loose_number") {
        val key = DataStoreKey("player_loose_number_key")
    }
    object PlayerWinsNumber : DataStoreInfoProvider("player_wins_number") {
        val key = DataStoreKey("player_wins_number_key")
    }
}
