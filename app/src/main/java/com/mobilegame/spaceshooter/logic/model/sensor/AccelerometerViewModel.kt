package com.mobilegame.spaceshooter.logic.model.sensor

import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.utils.analyze.eLog

class AccelerometerViewModel(
    private val sensor: MeasurableSensor,
): ViewModel() {
    init { initializeSensor() }

//    var devicePosition = XYZ(0F, 0F, 0F)
    var averagePosition = XYZ(0F, 0F, 0F)
    private val smooth = 4
    private val lastIndex = smooth - 1
    private val positions = XYZ.listZero(smooth)
    private fun shiftPositions(xyz: XYZ) {
        for (i in lastIndex  downTo 0) {
            if (i == 0) positions[i] = xyz
            else positions[i] = positions[i - 1].clone()
        }
    }
    private fun setAveragePosition() {
        averagePosition = XYZ(
            x = positions.getSumX() / smooth,
            y = positions.getSumY() / smooth,
            z = positions.getSumZ() / smooth,
        )
    }
    private fun handlePosition(xyz: XYZ) {
//        averagePosition = xyz
        shiftPositions(xyz)
        positions.smooth(50)
//        smooth()

        setAveragePosition()
    }

    //todo: delete motionVM from Param
    fun initializeSensor() {
        sensor.start()
        sensor.setOnSensorValuesChanged { values ->
//            eLog("AccelerometerVM::initializeSensor", "values $values")
            values.getOrNull(0)?.let { x ->
                values.getOrNull(1)?.let { y ->
                    values.getOrNull(2)?.let { z ->
                        handlePosition(XYZ(x, y , z))
                    } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting z value")
                } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting y value")
            } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting x value")
        }
    }

    fun smooth() {
        var value = positions[0]
        for ( i in 1 until lastIndex) {
            var current = positions[i]
            value = (current minus value) div 10
            positions[i] = value
        }
    }
//    fun smoothArray( values: XYZ, smoothing: Int ){
//        var value = arauu[0]; // start with the first input
//        for (var i=1, len=values.length; i<len; ++i){
//            var currentValue = values[i];
//            value += (currentValue - value) / smoothing;
//            values[i] = value;
//        }
//    function smoothArray( values, smoothing ){
//        var value = values[0]; // start with the first input
//        for (var i=1, len=values.length; i<len; ++i){
//            var currentValue = values[i];
//            value += (currentValue - value) / smoothing;
//            values[i] = value;
//        }
//    }

    fun stop() {
        sensor.stop()
    }
}