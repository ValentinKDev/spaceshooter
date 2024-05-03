package com.mobilegame.spaceshooter.logic.repository.device

import android.content.Context
import com.mobilegame.spaceshooter.data.store.DataStoreInfoProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.data.device.Device

class DeviceDataRepo {
    suspend fun init(context: Context) { updateName(context) }

    suspend fun updateName(context: Context) {
        Device.data.name = DataStoreService.deviceName(context).getString(DataStoreInfoProvider.DeviceName.key)
    }
    suspend fun registerName(name: String ,context: Context) {
        DataStoreService.deviceName(context).putString(DataStoreInfoProvider.DeviceName.key, name)
    }
}