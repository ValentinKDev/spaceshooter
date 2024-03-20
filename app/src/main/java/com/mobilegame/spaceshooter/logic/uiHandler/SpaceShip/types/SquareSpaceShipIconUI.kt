package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.HitBox
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.*

class SquareSpaceShipIconUI(override val shipViewBoxSize: Size): SpaceShipIconUIInterface {
    val sizes = SizesSquareShip(shipViewBoxSize)
    val points = PointsSquareShip(sizes)
    val ammo = Ammos(sizes)
    val colors = ColorsSquareShip()
    override val hitBox = HitBoxSquareShip(sizes)

    class SizesSquareShip(shipBox: Size) {
        val shipViewBoxSizeDp: DpSize = shipBox.toDpSize()
        val shipSize: Size = shipBox
        val shipSizeDp: DpSize = shipSize.toDpSize()
        val stroke: Float = shipBox.width * 0.06F
    }

    class PointsSquareShip(shipSizes: SizesSquareShip) {
        val center: Offset = shipSizes.shipSize.center
        val startCentralBar: Offset = Offset(x = center.x, y = 0F)
        val bottomCentralBar: Offset = Offset(center.x, center.y * 0.70F)
    }

    class Ammos(sizes: SizesSquareShip) {
        private val padding: Float = sizes.shipSize.height * 0.12F
        val ammoSize: Size = ((sizes.shipSize.height - (2F * padding) + sizes.stroke) / 3F).toSize()
        val innerSize: Size = ammoSize * 0.85F
        private val m1: Offset = Offset.Zero - Offset(x = padding + ammoSize.width + (sizes.stroke / 2F), y = sizes.stroke / 2F)
        private val m2: Offset = m1 yPlus (ammoSize.height + padding)
        private val m3: Offset = m2 yPlus (ammoSize.height + padding)
        private val m4: Offset = Offset(x = sizes.shipSize.width + (sizes.stroke / 2F) + padding ,y = sizes.stroke / -2F)
        private val m5: Offset = m4 yPlus (ammoSize.height + padding)
        private val m6: Offset = m5 yPlus (ammoSize.height + padding)

        private val projectileSize: Size = innerSize
        private val ratioCharge: Float = 0.70F
        val c2: Size = projectileSize * (ratioCharge * 2F)
        val c3: Size = projectileSize * (ratioCharge * 3F)
        val c4: Size = projectileSize * (ratioCharge * 4F)
        val c5: Size = projectileSize * (ratioCharge * 5F)
        val c6: Size = projectileSize * (ratioCharge * 6F)
        fun getShootSize(n: Int): Size = when (n) {
            1 -> projectileSize
            2 -> c2
            3 -> c3
            4 -> c4
            5 -> c5
            6 -> c6
            else -> {
                eLog("SquareSpaceShipIconUI::getShootSize", "ERROR get( ${n})")
                Size.Unspecified
            }
        }

        fun getAmmunitionOffset(n: Int): Offset = when (n) {
            1 -> m3
            2 -> m6
            3 -> m2
            4 -> m5
            5 -> m1
            6 -> m4
            else -> {
                eLog("SquareSpaceShipIconUI:getAmmunitionOffset", "ERROR getMunition(ui, --> ${n})")
                Offset.Zero
            }
        }
    }
    data class ColorsSquareShip(
        val outline: Color = MyColor.applicationContrast,
        var body: Color = MyColor.applicationBackground,
    )

    class HitBoxSquareShip(sizes: SizesSquareShip) : HitBox {
//        override val topLeft: DpOffset =
        override val size: Size = sizes.shipSize
        override val sizeDp: DpSize = sizes.shipSizeDp

    }
}