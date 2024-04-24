package com.mobilegame.spaceshooter.logic.model.screen.metrics

import android.content.Context
import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackgroundUI
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SpaceShooterMetricsViewModel(): ViewModel() {

    private val _initiated = MutableStateFlow<Boolean?>(null)
    val initiated: StateFlow<Boolean?> = _initiated.asStateFlow()
    val ui = BackgroundUI()
    fun initBackgroundData(context: Context) { ui.init(context) }
    fun initMetrics(context: Context, layout: LayoutCoordinates) = viewModelScope.launch {
        _initiated.emit(true)
        Device.metrics.width = layout.size.width.toFloat()
        Device.metrics.height = layout.size.height.toFloat()
        Device.metrics.size = Size(Device.metrics.width, Device.metrics.height)
        Device.metrics.density = context.resources.displayMetrics.density
//        Device.metrics.sizeDp = DpSize(Device.metrics.width.toDp(), Device.metrics.height.toDp())
        Device.metrics.sizeDp = Device.metrics.size.toDpSize()

        displayDataUI.let {
            Log.w("SpaceShooterMetricsVM", "initMetrics width = ${Device.metrics.width}")
            Log.w("SpaceShooterMetricsVM", "initMetrics height = ${Device.metrics.height}")
            Log.w("SpaceShooterMetricsVM", "initMetrics density = ${Device.metrics.density}")
            Log.w("SpaceShooterMetricsVM", "initMetrics sizeDP = ${Device.metrics.sizeDp}")
        }
    }
}