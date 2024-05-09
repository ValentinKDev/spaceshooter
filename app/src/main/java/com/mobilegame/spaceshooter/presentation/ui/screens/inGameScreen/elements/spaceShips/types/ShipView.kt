package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.CircleShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square.SquareShipView

@Composable
//fun ShipView(vm: SpaceShipViewModel, ui: SpaceWarGameScreenUI) {
fun ShipView(
    type: ShipType,
    lifeRatio: Float = 1F,
    magazine: Int = type.info.magazineSize,
    shipViewSizeBox: Size,
    angleTarget: Float = 0F,
) {

    val angle by animateFloatAsState(
        targetValue = angleTarget,
        animationSpec = tween(600),
        label = ""
    )

    Box(
        Modifier
            .wrapContentSize()
            .rotate(angle)
    ) {

    }
    when (type) {
        ShipType.Circle -> CircleShipView(lifeRatio, magazine, shipViewSizeBox)
        ShipType.Square -> SquareShipView(lifeRatio,magazine, shipViewSizeBox)
    }
}
