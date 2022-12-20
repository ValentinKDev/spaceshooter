package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.WifiButton.WifiIcon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.WifiIconAdapter

@Composable
fun WifiIcon(ui: WifiIconAdapter, color: Color? = null) {
    Canvas(
        Modifier
            .fillMaxSize()
    ) {
        val useCenter = false

        drawCircle(
            center = ui.shapes.bigCircleCenter,
            radius = ui.sizes.bigCircleSize,
            color = color ?: ui.colors.icon,
            style = Fill
        )
        drawArc(
            startAngle = -135f,
            sweepAngle = 90f,
            color = color ?: ui.colors.icon,
            useCenter = useCenter,
            style = ui.stroke.big,
            size = ui.sizes.size1,
            topLeft = ui.shapes.topLeft1
        )
        drawArc(
            startAngle = -135f,
            sweepAngle = 90f,
            color = color ?: ui.colors.icon,
            useCenter = useCenter,
            style = ui.stroke.big,
            size = ui.sizes.size2,
            topLeft = ui.shapes.topLeft2,
        )
        drawArc(
            startAngle = -135f,
            sweepAngle = 90f,
            color = color ?: ui.colors.icon,
            useCenter = useCenter,
            style = ui.stroke.big,
            size = ui.sizes.size3,
            topLeft = ui.shapes.topLeft3,
        )

        ui.outlined?.let {
            drawCircle(
                center = ui.shapes.smallCircleCenter,
                radius = ui.sizes.smallCircleSize,
                color = ui.colors.iconInner,
                style = Fill
            )
            drawArc(
                startAngle = -135f,
                sweepAngle = 90f,
                color = ui.colors.iconInner,
                useCenter = useCenter,
                style = ui.stroke.small,
                size = ui.sizes.size1,
                topLeft = ui.shapes.topLeft1,
            )
            drawArc(
                startAngle = -135f,
                sweepAngle = 90f,
                color = ui.colors.iconInner,
                useCenter = useCenter,
                style = ui.stroke.small,
                size = ui.sizes.size2,
                topLeft = ui.shapes.topLeft2,
            )
            drawArc(
                startAngle = -135f,
                sweepAngle = 90f,
                color = ui.colors.iconInner,
                useCenter = useCenter,
                style = ui.stroke.small,
                size = ui.sizes.size3,
                topLeft = ui.shapes.topLeft3,
            )
        }
    }
}