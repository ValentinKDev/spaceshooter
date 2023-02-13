package com.mobilegame.spaceshooter.data.sensor

import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.utils.analyze.vLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class GravityDataProducer(
    private val sensor: MeasurableSensor,
    private val refreshInterval: Long,
) {
    init { initializeSensor() }
    var isRunning = false

    var XYZrawValues: List<Float> = listOf()

    //todo: delete motionVM from Param
    fun initializeSensor() {
        start()
        sensor.setOnSensorValuesChanged { values ->
            XYZrawValues = values
        }
    }

    val valuesFlow = flow {
        while (isRunning) {
            if (XYZrawValues.isNotEmpty())
                emit(XYZrawValues)
            delay(refreshInterval)
        }
    }

    fun start() {
        isRunning = true
        sensor.start()
    }
    fun stop() {
        isRunning = false
        sensor.stop()
    }
    fun restart() {
        if (!isRunning) initializeSensor()
    }
}