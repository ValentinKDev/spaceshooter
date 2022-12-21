package com.mobilegame.spaceshooter.presentation.ui.screens.utils.spaceShips

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.inGameScreen.SpaceShipViewModel
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.eLog
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.sin

@Composable
fun DefaultShip(vm: SpaceShipViewModel, ui: SpaceShipIconAdapter) {
    Box(
        Modifier
            .size(ui.sizes.shipBoxDp)
    ) {
        DefaultShipShape(ui)
        DefaultShipMunitions(vm, ui)
    }
}