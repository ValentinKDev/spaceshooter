package com.mobilegame.spaceshooter.logic.repository.device

import android.content.Context
import com.mobilegame.spaceshooter.data.store.DataStoreNameProvider
import com.mobilegame.spaceshooter.data.store.DataStoreService
import com.mobilegame.spaceshooter.data.device.Device

class DeviceDataRepo {
    suspend fun init(context: Context) {
        //todo: remove the initialisation of the name to null
//        DataStoreService.deviceName(context).delString(DataStoreNameProvider.DeviceName.key)

        updateName(context)
    }

    suspend fun updateName(context: Context) {
        Device.data.name = DataStoreService.deviceName(context).getString(DataStoreNameProvider.DeviceName.key)
    }
}