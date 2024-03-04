package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.Motions
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.motions.MotionsViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.ShipOrigin
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType

class Shoot(
    val type: ShipType,
    val from: ShipOrigin = ShipOrigin.User,
    val motion: Motions,
    var vector: Size = Size.Zero,
    val size: Int,
    val damage: Float,
    var offsetDp: DpOffset = DpOffset.Zero,
) {
    //todo : add some kind of size of behavior number related to the shiptype
    fun updateDpOffset() {
        offsetDp = DpOffset(
            x = offsetDp.x + vector.width.dp,
            y = offsetDp.y - vector.height.dp,
        )
    }
    fun getShootWithUpdatedDpOffset() = Shoot(
        type = this.type,
        from = this.from,
        motion = this.motion,
        vector = this.vector,
        size = this.size,
        damage = this.damage,
        offsetDp = DpOffset(
            x = offsetDp.x + vector.width.dp,
            y = offsetDp.y - vector.height.dp,
        )
    )
    companion object {
        fun newFromUser(ship: ShipType, vm: MotionsViewModel, behavior: Int = 0, damage: Float = 1F): Shoot = Shoot(
            type = ship,
            from = ShipOrigin.User,
            motion = vm.motion.value,
            vector = vm.getShootVector(),
            size = behavior,
            damage = ship.info.damage,
            offsetDp = vm.getShipTopCenter(),
        )
    }
}