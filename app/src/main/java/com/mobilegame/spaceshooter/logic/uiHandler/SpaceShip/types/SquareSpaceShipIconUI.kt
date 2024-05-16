package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types


import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.HitBox
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.*

class SquareSpaceShipIconUI(override val shipViewBoxSize: Size): SpaceShipIconUIInterface {
    val TAG = "SquareSpaceShipIconUI"
    val sizes = SizesSquareShip(shipViewBoxSize)
    val points = PointsSquareShip(sizes)
    val ammo = MunitionsSquareSpaceShip(sizes)
    val colors = ColorsSquareShip()
    override val hitBox = HitBoxSquareShip(sizes)

    class SizesSquareShip(shipBox: Size) {
        val shipViewBoxSizeDp: DpSize = shipBox.toDpSize()
        val shipSize: Size = shipBox
        val shipSizeDp: DpSize = shipSize.toDpSize()
        val stroke: Float = shipBox.width * 0.06F

        val ammoPadding: Float = shipSize.height * 0.12F
        val ammoSize: Size = ((shipSize.height - (2F * ammoPadding) + stroke) / 3F).toDpSize()
        val ammoInnerSize: Size = ammoSize * 0.85F
    }

    class PointsSquareShip(shipSizes: SizesSquareShip) {
        val center: Offset = shipSizes.shipSize.center
        val startCentralBar: Offset = Offset(x = center.x, y = 0F)
        val bottomCentralBar: Offset = Offset(center.x, center.y * 0.70F)
    }

    class MunitionsSquareSpaceShip(sizes: SizesSquareShip) {
        val TAG = "MunitionsSquareSpaceShip"
        private val padding = sizes.ammoPadding
        val ammoSize = sizes.ammoSize
        val innerSize: Size = sizes.ammoInnerSize
        private val m1: Offset = Offset.Zero - Offset(x = padding + ammoSize.width + (sizes.stroke / 2F), y = sizes.stroke / 2F)
        private val m2: Offset = m1 yPlus (ammoSize.height + padding)
        private val m3: Offset = m2 yPlus (ammoSize.height + padding)
        private val m4: Offset = Offset(x = sizes.shipSize.width + (sizes.stroke / 2F) + padding ,y = sizes.stroke / -2F)
        private val m5: Offset = m4 yPlus (ammoSize.height + padding)
        private val m6: Offset = m5 yPlus (ammoSize.height + padding)

        private val projectileSize: Size = innerSize
        private val projectileSizeDp: DpSize = innerSize.toDpSize()
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
//        init { Log.i(TAG, "init:") }
    }
    data class ColorsSquareShip(
        val outline: Color = MyColor.applicationContrast,
        val body: Color = MyColor.squareShip,
        val chargeAnimation: Color = MyColor.applicationContrast
    )

    class HitBoxSquareShip(sizes: SizesSquareShip) : HitBox {
        override val ratio: Float = 1F
        override val canvasSize: Size = sizes.shipSize
        override val boxDp: DpSize = (canvasSize.width * ratio).toDp().toDpSize()
        override val boxDpOffset: DpOffset = (canvasSize.width * (1F - ratio) * 0.5F).toDp().toDpOffset()
        override val ammoWidthDp: Dp = sizes.ammoSize.width.toDp()
//        override val ammoWidthDp: Dp = sizes.ammoSize.width.dp / 5F
//        override val ammoWidthDp: Dp = 5.dp
//        /2
    }
}