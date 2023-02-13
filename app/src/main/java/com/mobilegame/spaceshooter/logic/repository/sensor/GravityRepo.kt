package com.mobilegame.spaceshooter.logic.repository.sensor

import android.content.Context
import android.util.Log
import com.mobilegame.spaceshooter.data.sensor.GravityDataProducer
import com.mobilegame.spaceshooter.data.sensor.GravitySensor
import com.mobilegame.spaceshooter.data.sensor.XYZ
import kotlinx.coroutines.flow.*

class GravityRepo(context: Context, refreshInterval: Long) {
    private val TAG = object{}.javaClass.enclosingClass.simpleName
    private val gravityProducer = GravityDataProducer(GravitySensor(context), refreshInterval)
    private var firstTime = true
    private var position = XYZ.ZERO
    private val alpha = 0.6f

    var maxZ = 1F
    var averageXYZ: Flow<XYZ> = gravityProducer.valuesFlow.map {
        upDateMaxZ(it[2])
        val filteredValues: XYZ = lowAndHighPassFilter(
            xValue = it[0],
            yValue = it[1],
            zValue = it[2],
        )
        filteredValues
    }

    private fun upDateMaxZ(z: Float) { if (maxZ < z) { maxZ = z } }
    private fun lowAndHighPassFilter(xValue: Float, yValue: Float, zValue: Float): XYZ {
        val filteredXYZ = if (firstTime) {
            XYZ(
                x = xValue,
                y = yValue,
                z = zValue
            )
        } else {
            XYZ(
                x = position.x + alpha * (xValue - position.x),
                y = position.y + alpha * (yValue - position.y),
                z = position.z + alpha * (zValue - position.z),
            )
        }
        return filteredXYZ
    }
    fun stop() {
        Log.e(TAG, "stop: gravityDataProducer::stop")
        gravityProducer.stop()
    }
}