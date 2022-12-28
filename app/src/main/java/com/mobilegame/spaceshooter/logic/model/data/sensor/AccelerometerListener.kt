package com.mobilegame.spaceshooter.logic.model.data.sensor

import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog

object AccelerometerListener {
    private var sensor: MeasurableSensor? = null

//    private var status: OnGoing = OnGoing.Stop

    var devicePosition = XYZ(0F, 0F, 0F)

    //todo: delete motionVM from Param
    fun initializeSensor(theSensor: MeasurableSensor) {
        sensor = sensor ?: theSensor
        sensor?.start()
//        status = OnGoing.Start
//        Start()
//        sensor?.start()
        sensor?.setOnSensorValuesChanged { values ->
            values.getOrNull(0)?.let { x ->
                values.getOrNull(1)?.let { y ->
                    values.getOrNull(2)?.let { z ->
                        devicePosition = XYZ(x, y, z )
                    } ?: eLog("AccelerometerListener::initializeSensor", "ERROR getting z value")
                } ?: eLog("AccelerometerListener::initializeSensor", "ERROR getting y value")
            } ?: eLog("AccelerometerListener::initializeSensor", "ERROR getting x value")
        }
    }

    fun stop() {
        sensor?.stop()
    }
}