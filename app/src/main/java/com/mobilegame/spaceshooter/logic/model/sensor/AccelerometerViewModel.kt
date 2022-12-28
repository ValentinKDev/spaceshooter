package com.mobilegame.spaceshooter.logic.model.sensor

import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.logic.model.data.sensor.XYZ
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.Device
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog

class AccelerometerViewModel(
    private val sensor: MeasurableSensor,
): ViewModel() {
    init { initializeSensor() }

    var devicePosition = XYZ(0F, 0F, 0F)

    //todo: delete motionVM from Param
    fun initializeSensor() {
        sensor.start()
        sensor.setOnSensorValuesChanged { values ->
            values.getOrNull(0)?.let { x ->
                values.getOrNull(1)?.let { y ->
                    values.getOrNull(2)?.let { z ->
                        devicePosition = XYZ(x, y, z )
                    } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting z value")
                } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting y value")
            } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting x value")
        }
    }

    fun stop() {
        sensor.stop()
    }
}