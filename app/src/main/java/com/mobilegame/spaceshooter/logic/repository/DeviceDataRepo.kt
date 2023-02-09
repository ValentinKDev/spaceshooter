package com.mobilegame.spaceshooter.logic.repository

import android.content.Context
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.data.device.Device

class DeviceDataRepo {
    suspend fun init(context: Context) {
        Device.data.name = DataStoreService.deviceName(context).getString(DataStoreNameProvider.DeviceName.key)
    }
}