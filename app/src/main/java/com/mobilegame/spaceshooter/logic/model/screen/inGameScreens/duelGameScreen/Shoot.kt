package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.ShipOrigin
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.ShipType

class Shoot (
    val type: ShipType = ShipType.Default,
    val from: ShipOrigin = ShipOrigin.User,
    var vector: Size = Size.Zero,
    var offsetDp: DpOffset = DpOffset.Zero,
) {
    fun upDateDpOffset() {
        offsetDp = DpOffset(
            x = offsetDp.x + vector.width.dp,
            y = offsetDp.y - vector.height.dp,
        )
    }
}