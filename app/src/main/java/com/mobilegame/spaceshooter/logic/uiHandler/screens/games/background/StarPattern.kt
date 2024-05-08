package com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.utils.extensions.dpToPixel
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.xMinus
import com.mobilegame.spaceshooter.utils.extensions.xPlus
import com.mobilegame.spaceshooter.utils.extensions.yMinus
import com.mobilegame.spaceshooter.utils.extensions.yPlus

class StarPattern(private val anchor: Offset, private val squareCase: Float) {
    private val TAG = "StarPattern"
    val lineNumber = 7
    val colNumber = 7
    val middleLineIndex = 3
    val middleColIndex = 3
    val matrix: Array<Array<Offset>> = Array(lineNumber){ Array(colNumber){ Offset.Unspecified} }
    init {
        for ((line, array) in matrix.withIndex()) {
            for (col in array.indices) {
                matrix[line][col] = Offset(
                    x = if ( col in 0..middleColIndex) {
                        anchor.x - ((middleColIndex - col) * squareCase)
                    } else anchor.x + ((col - middleColIndex ) * squareCase) ,
                    y = if (line in 0 until middleLineIndex) {
                        anchor.y - ((middleLineIndex - line) * squareCase)
                    } else anchor.y + ((line - middleLineIndex ) * squareCase) ,
                )
            }
        }
    }
    fun getSquareStarPattern(): Array<Offset> = arrayOf(
        matrix[0][2],
        matrix[0][4],

        matrix[1][0],
        matrix[1][6],

        matrix[3][0],
        matrix[3][6],

        matrix[5][0],
        matrix[5][6],

        matrix[6][2],
        matrix[6][4],
    )

    fun getCircleStarPattern(): Array<Offset> = arrayOf(
        matrix[0][2],
        matrix[0][4],

        matrix[2][0],
        matrix[2][2],
        matrix[2][4],
        matrix[2][6],

        matrix[4][0],
        matrix[4][2],
        matrix[4][4],
        matrix[4][6],

        matrix[6][2],
        matrix[6][4],
    )
}