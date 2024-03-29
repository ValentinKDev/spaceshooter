package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.SpaceShipViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.screens.games.SpaceWarGameScreenUI
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.circle.CircleShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.square.SquareShipView

@Composable
//fun ShipView(vm: SpaceShipViewModel, ui: SpaceWarGameScreenUI) {
fun ShipView(
    type: ShipType,
    lifeRatio: Float = 1F,
    magazine: Int = type.info.magazineSize,
    shipViewSizeBox: Size
) {
    when (type) {
//        ShipType.Circle -> CircleShipView( vm,  ui.sizes.shipViewBox)
//        ShipType.Square -> SquareShipView(vm, ui.sizes.shipViewBox)
//        ShipType.Circle -> CircleShipView(magazine, ui.sizes.shipViewBox)
//        ShipType.Square -> SquareShipView(lifeRatio,magazine, ui.sizes.shipViewBox)
        ShipType.Circle -> CircleShipView(magazine, shipViewSizeBox)
        ShipType.Square -> SquareShipView(lifeRatio,magazine, shipViewSizeBox)
    }
}
