package com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.utils.extensions.dpToPixel
import com.mobilegame.spaceshooter.utils.extensions.oneLineDowner
import com.mobilegame.spaceshooter.utils.extensions.oneLineUpper
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.yMinus
import com.mobilegame.spaceshooter.utils.extensions.yPlus
import kotlinx.coroutines.flow.MutableStateFlow

class BackgroundUI(val shipType: ShipType) {
    private val TAG = "BackgroundUI"
    val matrix = BackgroundMatrix(shipType)
    val anim = BackgroundAnim()
    class BackgroundAnim() {
        private val lateralBorder = Device.metrics.width * 0.006F
        val lateralBorderDp = lateralBorder.toDp()
    }

    class BackgroundMatrix(val shipType: ShipType) {
        val initialColorValue = 0.55F
        val targetColorValue = 0.1F
        private val TAG = "BackgroundMatrix"
        val littleStarDp = (1.5).dp
        val littleStar = littleStarDp.dpToPixel()
        val dotCanvasSize: Size = Size(littleStar, littleStar)
//        val color = Color.Green
        private val bigStarLineNumber = 7
        private val littleStarLineNumber = bigStarLineNumber + (bigStarLineNumber - 1) * 3
        private val lineStarsNumber = bigStarLineNumber + littleStarLineNumber
        private val columnStarsNumber: Int = 28
        private var starsPadding: Float = Device.metrics.size.width / (littleStarLineNumber)

        val matrix: Array<Array<Star>> = Array(columnStarsNumber) { Array(lineStarsNumber) { Star() } }

        init { matrixInit() }
        fun getHorizontalMargeDp(): Dp = (0.8F * (matrix[0].last().anchor.x - Device.metrics.width)).toDp()
        fun getVerticalMargeDp(): Dp = (0.8F * (matrix.last().first().anchor.y - Device.metrics.height)).toDp()
        private fun matrixInit() {
            val middleLineIndex = (matrix.size / 2) - 1
            val middleColIndex = (matrix[0].size / 2) - 1
            val patternNum = 4

            val middleOffset = Offset(x = Device.metrics.width / 2F, y = Device.metrics.height / 2F)
            //init middle offset
            matrix[middleLineIndex][middleColIndex] = Star(
                type = StarType.Big,
                anchor = middleOffset,
                patternArray = StarPattern(middleOffset, littleStar).getSquareStarPattern()
            )

            val anchor = matrix[middleLineIndex][middleColIndex].anchor
            for ((line, array) in matrix.withIndex()) {
                for (column in array.indices) {
                    matrix[line][column] = Star (
                        anchor = Offset(
                            x = if ( column in 0..middleColIndex) {
                                anchor.x - ((middleColIndex - column) * (littleStar + starsPadding))
                            } else anchor.x + ((column - middleColIndex ) * (littleStar + starsPadding)) ,
                            y = if (line in 0 until middleLineIndex) {
                                anchor.y - ((middleLineIndex - line) * (littleStar + starsPadding))
                            } else anchor.y + ((line - middleLineIndex ) * (littleStar + starsPadding)) ,
                        )
                    )
                    if (((line - middleLineIndex) % patternNum == 0) && ((column - middleColIndex) % patternNum == 0)) updateStarToBigPattern( matrix[line][column] )
                }
            }
        }
        private fun updateStarToBigPattern(star: Star) {
            star.type = StarType.Big
            star.patternArray = when (shipType) {
                ShipType.Circle -> { StarPattern(star.anchor, littleStar).getCircleStarPattern() }
                ShipType.Square -> { StarPattern(star.anchor, littleStar).getSquareStarPattern() }
            }
        }
    }
}

