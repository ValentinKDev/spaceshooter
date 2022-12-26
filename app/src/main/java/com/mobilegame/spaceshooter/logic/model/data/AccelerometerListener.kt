package com.mobilegame.spaceshooter.logic.model.data

import com.mobilegame.spaceshooter.logic.domain.MeasurableSensor
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreen.motions.MotionsViewModel

object AccelerometerListener {
    private var sensor: MeasurableSensor? = null

    var devicePosition = XYZ(0F, 0F, 0F)

    fun initializeSensor(theSensor: MeasurableSensor, motionVM: MotionsViewModel) {
        sensor = sensor ?: theSensor
        sensor?.start()
        sensor?.setOnSensorValuesChanged { values ->
            values.getOrNull(0)?.let { x ->
                values.getOrNull(1)?.let { y ->
                    values.getOrNull(2)?.let { z ->
                        devicePosition = XYZ(x, y, z )
                        motionVM.updatePlayerPosition()
                    }
                }
            }
        }
    }

    fun stop() { sensor?.stop() }
}