package com.mobilegame.spaceshooter.logic.model.sensor

import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.prettyPrint
import com.mobilegame.spaceshooter.utils.analyze.wLog

class AccelerometerViewModel(
    private val sensor: MeasurableSensor,
) {
    init { initializeSensor() }
    var isRunning = false
    private var firstTime = true
    private var position = XYZ.ZERO
    private val alpha = 0.6f
    var maxZ = 1F

    var averagePosition = XYZ(0.01F, 0.01F, 0.01F)

    //todo: delete motionVM from Param
    fun initializeSensor() {
        start()
        sensor.setOnSensorValuesChanged { values ->
            upDateMaxZ(averagePosition.z)
//            prettyPrint("AccelerometerVM::initializeSensor", "XYZ", values)
            lowAndHighPassFilter(
                xValue = values[0],
                yValue = values[1],
                zValue = values[2],
            )
        }
    }

    private fun lowAndHighPassFilter(xValue: Float, yValue: Float, zValue: Float) {
        if (firstTime) {
            firstTime = false
            averagePosition = XYZ(
                x = xValue,
                y = yValue,
                z = zValue
            )
        }
        averagePosition = XYZ(
            x = position.x + alpha * ( xValue - position.x ),
            y = position.y + alpha * ( yValue - position.y ),
            z = position.z + alpha * ( zValue - position.z ),
        )
    }

    private fun upDateMaxZ(z: Float) { if (maxZ < z) { maxZ = z } }

    fun start() {
        isRunning = true
        sensor.start()
    }
    fun stop() {
        isRunning = false
        sensor.stop()
    }
    fun restart() {
        wLog("AccelerometerVM::restart", "isRunning $isRunning")

        if (isRunning) initializeSensor()
    }
}