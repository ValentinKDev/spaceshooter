package com.mobilegame.spaceshooter.logic.model.screen.metrics

import android.content.Context
import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceShooterMetricsViewModel(): ViewModel() {
    private val TAG = "SpaceShooterMetricsViewModel"

    private val _initiated = MutableStateFlow<Boolean?>(null)
    val initiated: StateFlow<Boolean?> = _initiated.asStateFlow()
//    val ui = BackgroundUI()
//    fun initBackgroundData() { ui.init() }
    fun initMetrics(context: Context, layout: LayoutCoordinates) = viewModelScope.launch {
        if (layout.size.width < layout.size.height) {
            Log.e( TAG, "initMetrics: ERROR WIDTH < HEIGHT", )
            Device.metrics.width = layout.size.height.toFloat()
            Device.metrics.height = layout.size.width.toFloat()
        } else {
            Device.metrics.width = layout.size.width.toFloat()
            Device.metrics.height = layout.size.height.toFloat()
        }
        Device.metrics.size = Size(Device.metrics.width, Device.metrics.height)
        Device.metrics.density = context.resources.displayMetrics.density
        Device.metrics.sizeDp = Device.metrics.size.toDpSize()
        _initiated.emit(true)

        Log.w("SpaceShooterMetricsVM", "initMetrics width = ${Device.metrics.width}")
        Log.w("SpaceShooterMetricsVM", "initMetrics height = ${Device.metrics.height}")
        Log.w("SpaceShooterMetricsVM", "initMetrics density = ${Device.metrics.density}")
        Log.w("SpaceShooterMetricsVM", "initMetrics sizeDP = ${Device.metrics.sizeDp}")
    }
}