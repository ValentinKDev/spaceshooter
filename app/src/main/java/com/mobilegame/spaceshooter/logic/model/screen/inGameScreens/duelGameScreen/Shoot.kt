package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.ShipOrigin
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType

class Shoot(
    val type: ShipType,
    val from: ShipOrigin = ShipOrigin.User,
    val motion: Motions,
    var vector: Size = Size.Zero,
    var offsetDp: DpOffset = DpOffset.Zero,
) {
    fun updateDpOffset() {
        offsetDp = DpOffset(
            x = offsetDp.x + vector.width.dp,
            y = offsetDp.y - vector.height.dp,
        )
    }
}