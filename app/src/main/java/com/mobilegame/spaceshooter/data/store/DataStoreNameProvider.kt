package com.mobilegame.spaceshooter.data.store

internal sealed class DataStoreNameProvider(val pref: String) {
    object DeviceName : DataStoreNameProvider("device_name_datastore") {
        const val NameKey = "device_name_datastore_key"
    }
}
