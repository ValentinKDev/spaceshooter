package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.CircleShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.lasery.LaseryShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square.SquareShipView

@Composable
fun ShipView(
    type: ShipType,
    lifeRatio: Float = 1F,
    magazine: Int = type.info.magazineSize,
    shipViewSizeBox: Size,
    visibleCharging: Boolean = false
) {
    when (type) {
        ShipType.Circle -> CircleShipView(lifeRatio, magazine, shipViewSizeBox, visibleCharging)
        ShipType.Square -> SquareShipView(lifeRatio,magazine, shipViewSizeBox, visibleCharging)
        ShipType.Lasery -> LaseryShipView(lifeRatio,magazine, shipViewSizeBox, visibleCharging)
    }
}
