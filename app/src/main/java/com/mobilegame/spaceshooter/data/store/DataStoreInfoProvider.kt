package com.mobilegame.spaceshooter.data.store

//todo : is key usefull here ?
enum class DataStoreInfoProvider(val pref: String, val key: DataStoreKey) {
    DeviceName(pref = "device_name_datastore", key = DataStoreKey("device_name_datastore_key")),
    DeviceStats(pref = "device_stats_datastore", key = DataStoreKey("device_stats_datastore_key")),

//    PlayerLooseNumber(pref = "player_loose_number", key = DataStoreKey("player_loose_number_key")),
//    PlayerWinsNumber(pref = "player_wins_number", key = DataStoreKey("player_wins_number_key"))
}
