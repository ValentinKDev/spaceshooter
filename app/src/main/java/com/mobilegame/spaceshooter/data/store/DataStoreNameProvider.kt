package com.mobilegame.spaceshooter.data.store

internal sealed class DataStoreNameProvider(val pref: String) {
    object DeviceName : DataStoreNameProvider("device_name_datastore") {
        val key = DataStoreKey("device_name_datastore_key")
    }
}
