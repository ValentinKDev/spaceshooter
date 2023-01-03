package com.mobilegame.spaceshooter.data.sensor

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class GravitySensor (
    context: Context,
) : AndroidSensor(
    context = context,
    feature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    type = Sensor.TYPE_GRAVITY,
)
