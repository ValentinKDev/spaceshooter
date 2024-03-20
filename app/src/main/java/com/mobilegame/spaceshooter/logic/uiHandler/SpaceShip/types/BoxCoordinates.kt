package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import com.mobilegame.spaceshooter.utils.extensions.toOffset

data class BoxCoordinates (
    val size: Size,
//    val height: Float = size.height, // from top to bottom
//    val width: Float = height, //from left to right
    var centerOffset: Offset,
    var centerOffsetDp: DpOffset = centerOffset.toDpOffset(),
    private val halfWidth: Float = size.width / 2F,
    private val halfHeight: Float = size.height / 2F,
    val sizeDp: DpSize = size.toDpSize(),
    val widthDp: Dp = size.width.toDp(),
    val heightDp: Dp = size.height.toDp(),
) {
    var startWidth: Float = Float.NaN
    var startWidthDp: Dp = Dp.Unspecified
    var endWidth: Float = Float.NaN
    var endWidthDp: Dp = Dp.Unspecified
    var topHeight: Float = Float.NaN
    var topHeightDp: Dp = Dp.Unspecified
    var bottomHeight: Float = Float.NaN
    var bottomHeightDp: Dp = Dp.Unspecified

    private fun updateWidth() {
        startWidth = centerOffset.x - halfWidth
        startWidthDp = startWidth.toDp()
        endWidth = centerOffset.x + halfWidth
        endWidthDp = endWidth.toDp()
    }
    private fun updateHeight() {
        topHeight = centerOffset.y - halfHeight
        topHeightDp = topHeight.toDp()
        bottomHeight = centerOffset.y + halfHeight
        bottomHeightDp = bottomHeight.toDp()
    }

    private fun updateWith(centerDp: DpOffset) {
        centerOffsetDp = centerDp
        centerOffset = centerDp.toOffset()
        updateWidth()
        updateHeight()
    }
    fun getUpdatedBoxCoordinates(centerDp: DpOffset): BoxCoordinates {
        centerOffsetDp = centerDp
        centerOffset = centerDp.toOffset()
        updateWidth()
        updateHeight()
        return this
    }
    companion object {
        fun with(center: Offset, size: Size): BoxCoordinates = BoxCoordinates(
            centerOffset = center,
            size = size,
        )
    }
}