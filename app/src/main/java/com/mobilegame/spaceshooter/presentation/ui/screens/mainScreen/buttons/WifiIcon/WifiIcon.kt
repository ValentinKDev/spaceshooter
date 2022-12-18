package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.WifiIcon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel

@Composable
fun WifiIcon(vm: MainScreenViewModel) {
    Box( contentAlignment = Alignment.Center ) {
        Canvas(
            Modifier
                .size(vm.ui.buttonWifi.circles.p1CanvasSizeDp)
        ) {
            drawCircle(
                radius = vm.ui.buttonWifi.circles.p1Radius,
                color = vm.ui.buttonWifi.color.icon,
                style = Stroke(vm.ui.buttonWifi.stroke.p1stroke, cap = StrokeCap.Square)
            )
        }
        Canvas(
            Modifier
                .size(vm.ui.buttonWifi.circles.p2CanvasSizeDp)
        ) {
            drawArc(
                startAngle = -135f,
                sweepAngle = 90f,
                useCenter = false,
                color = vm.ui.buttonWifi.color.icon,
                style = Stroke(vm.ui.buttonWifi.stroke.bigStroke, cap = StrokeCap.Square)
            )
            drawArc(
                startAngle = -135f,
                sweepAngle = 90f,
                useCenter = false,
                color = vm.ui.buttonWifi.color.iconInner,
                style = Stroke(vm.ui.buttonWifi.stroke.smallStroke, cap = StrokeCap.Square)
            )
        }
        Canvas(
            Modifier
                .size(vm.ui.buttonWifi.circles.p3CanvasSizeDp)
        ) {
            drawArc(
                color = vm.ui.buttonWifi.color.icon,
                startAngle = -135f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(vm.ui.buttonWifi.stroke.bigStroke, cap = StrokeCap.Square)
            )
            drawArc(
                color = vm.ui.buttonWifi.color.iconInner,
                startAngle = -135f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(vm.ui.buttonWifi.stroke.smallStroke, cap = StrokeCap.Square)
            )
        }
        Canvas(
            Modifier
                .size(vm.ui.buttonWifi.circles.p4CanvasSizeDp)
        ) {
            drawArc(
                color = vm.ui.buttonWifi.color.icon,
                startAngle = -135f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(vm.ui.buttonWifi.stroke.bigStroke, cap = StrokeCap.Square)
            )
            drawArc(
                color = vm.ui.buttonWifi.color.iconInner,
                startAngle = -135f,
                sweepAngle = 90f,
                useCenter = false,
                style = Stroke(vm.ui.buttonWifi.stroke.smallStroke, cap = StrokeCap.Square)
            )
        }
    }
}