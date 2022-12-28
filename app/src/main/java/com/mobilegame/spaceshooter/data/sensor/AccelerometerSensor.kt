package com.mobilegame.spaceshooter.data.sensor

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor

class AccelerometerSensor(
    context: Context,
) : AndroidSensor(
    context = context,
    feature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    type = Sensor.TYPE_ACCELEROMETER,
//    feature = PackageManager.FEATURE_SENSOR_ACCELEROMETER_LIMITED_AXES,
//    type = Sensor.TYPE_ACCELEROMETER_LIMITED_AXES,
//    feature = PackageManager.FEATURE_SENSOR_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED,
//    type = Sensor.TYPE_ACCELEROMETER_LIMITED_AXES_UNCALIBRATED,
//    feature = PackageManager.FEATURE_SENSOR_GYROSCOPE,
//    type = Sensor.TYPE_GYROSCOPE,
//    feature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
//    type = Sensor.TYPE_ROTATION_VECTOR
)
