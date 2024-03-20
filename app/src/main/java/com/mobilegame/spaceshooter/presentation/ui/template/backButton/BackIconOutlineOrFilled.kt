package com.mobilegame.spaceshooter.presentation.ui.template.backButton

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.uiHandler.template.BackButtonUI

@Composable
fun BackIconOutlineOrFilled(ui: BackButtonUI, style: DrawStyle) {
    Canvas(
        Modifier
            .size(ui.sizes.canvasDp)
    ) {
        drawPath(
            color = ui.colors.contrast,
            path = ui.path,
            style = style
        )
    }
}