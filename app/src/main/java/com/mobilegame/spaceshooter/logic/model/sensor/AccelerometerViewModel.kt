package com.mobilegame.spaceshooter.logic.model.sensor

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
//import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.gravity
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.prettyPrint
import com.mobilegame.spaceshooter.utils.extensions.printTo

class AccelerometerViewModel(
    private val sensor: MeasurableSensor,
): ViewModel() {
    init { initializeSensor() }

//    var devicePosition = XYZ(0F, 0F, 0F)
    var averagePosition = XYZ(0F, 0F, 0F)
    private val positionsArraySize = 4
    private val positionArrayLastIndex = positionsArraySize - 1
    private val positionArray = XYZ.listZero(positionsArraySize)
    private fun shiftPositions(xyz: XYZ) {
        for (i in positionArrayLastIndex  downTo 0) {
            if (i == 0) positionArray[i] = xyz
            else positionArray[i] = positionArray[i - 1].clone()
        }
    }
    private fun setAveragePosition() {
        averagePosition = XYZ(
//            x = positionArray.getSumX() / positionsArraySize,
//            y = positionArray.getSumY() / positionsArraySize,
//            z = positionArray.getSumZ() / positionsArraySize,
            x = positionArray.getSumX() ,
            y = positionArray.getSumY() ,
            z = positionArray.getSumZ() ,
        )
    }
    private fun handlePosition(xyz: XYZ) {
//        averagePosition = xyz
        shiftPositions(xyz)
//        prettyPrint("AccelerometerVM::handlePosition", "postion Array", positionArray, Log.VERBOSE)
        positionArray.smooth(20)

        setAveragePosition()
    }

    //todo: delete motionVM from Param
    fun initializeSensor() {
        sensor.start()
        sensor.setOnSensorValuesChanged { values ->
//            var str = "values ["
//            values.forEach { str += "${it.printTo(2)}, " }
//            str += "]"
//            eLog("AccelerometerVM::initializeSensor", str)

            values.getOrNull(0)?.let { x ->
                values.getOrNull(1)?.let { y ->
                    values.getOrNull(2)?.let { z ->
//                    values.getOrNull(3)?.let { z ->
//                        handlePosition(
//                            XYZ(
//                                x = if (x <= gravity) x else gravity,
//                                y = if (y <= gravity) y else gravity,
//                                z = if (z <= gravity) z else gravity,
//                            )
//                        )
                    } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting z value")
                } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting y value")
            } ?: eLog("AccelerometerVM::initializeSensor", "ERROR getting x value")
        }
    }

    fun stop() {
        sensor.stop()
    }
}