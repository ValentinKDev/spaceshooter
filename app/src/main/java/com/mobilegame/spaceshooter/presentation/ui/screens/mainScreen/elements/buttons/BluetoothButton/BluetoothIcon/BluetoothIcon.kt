package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable

@Composable
fun BluetoothIcon(ui: BluetoothIcon) {
    Box(
        Modifier
            .size(ui.sizes.squareHeightDp)
    ) {
//        CenterComposable {
        CenterComposable(id = ui.id) {
            Canvas(
                Modifier
                    .size(ui.sizes.canvasDp)
            ) {
                drawLine(
                    start = ui.points.p2,
                    end = ui.points.p5,
                    color = ui.colors.stroke,
                    cap = StrokeCap.Round,
                    strokeWidth = ui.sizes.iconBigStroke
                )
                drawLine(
                    start = ui.points.p5,
                    end = ui.points.p6,
                    cap = StrokeCap.Round,
                    color = ui.colors.stroke,
                    strokeWidth = ui.sizes.iconBigStroke
                )
                drawLine(
                    start = ui.points.p5,
                    end = ui.points.p6,
                    cap = StrokeCap.Round,
                    color = ui.colors.stroke,
                    strokeWidth = ui.sizes.iconBigStroke
                )
                drawLine(
                    start = ui.points.p6,
                    end = ui.points.p1,
                    color = ui.colors.stroke,
                    cap = StrokeCap.Round,
                    strokeWidth = ui.sizes.iconBigStroke
                )
                drawLine(
                    start = ui.points.p1,
                    end = ui.points.p3,
                    color = ui.colors.stroke,
                    cap = StrokeCap.Round,
                    strokeWidth = ui.sizes.iconBigStroke
                )
                drawLine(
                    start = ui.points.p3,
                    end = ui.points.p4,
                    color = ui.colors.stroke,
                    cap = StrokeCap.Round,
                    strokeWidth = ui.sizes.iconBigStroke
                )

                drawLine(
//                    start = p2,
                    start = ui.points.p2,
                    end = ui.points.p5,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
                drawLine(
                    start = ui.points.p5,
                    end = ui.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
                drawLine(
                    start = ui.points.p5,
                    end = ui.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
                drawLine(
                    start = ui.points.p5,
                    end = ui.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
                drawLine(
                    start = ui.points.p6,
                    end = ui.points.p1,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
                drawLine(
                    start = ui.points.p1,
                    end = ui.points.p3,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
                drawLine(
                    start = ui.points.p3,
                    end = ui.points.p4,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = ui.sizes.iconSmallStroke
                )
            }
        }
    }
}