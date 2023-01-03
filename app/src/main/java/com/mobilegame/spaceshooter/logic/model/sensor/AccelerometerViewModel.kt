package com.mobilegame.spaceshooter.logic.model.sensor

import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.utils.analyze.eLog

class AccelerometerViewModel(
    private val sensor: MeasurableSensor,
): ViewModel() {
    init { initializeSensor() }
    private var firstTime = true
    private var position = XYZ.ZERO
    private val alpha = 0.6f
    var maxZ = 1F

    var averagePosition = XYZ(0F, 0F, 0F)

    //todo: delete motionVM from Param
    fun initializeSensor() {
        sensor.start()
        sensor.setOnSensorValuesChanged { values ->
            if (firstTime) {
                firstTime = false
                averagePosition = XYZ(
                    x = values[0],
                    y = values[1],
                    z = values[2],
                )
            }
            averagePosition = XYZ(
                x = position.x + alpha * ( values[0] - position.x ),
                y = position.y + alpha * ( values[1] - position.y ),
                z = position.z + alpha * ( values[2] - position.z ),
            )
            upDateMaxZ(averagePosition.z)
        }
    }

    private fun upDateMaxZ(z: Float) {
        if (maxZ < z) {
            maxZ = z
            eLog("AccelerometerVM::upDateMaxZ", "max Z $maxZ")
        }
    }

    fun stop() {
        sensor.stop()
    }
}