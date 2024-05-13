package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types

import android.util.Log
import androidx.compose.ui.geometry.Offset
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.HitBox
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.*

class LaserySpaceShipIconUI (override val shipViewBoxSize: Size): SpaceShipIconUIInterface {
    private val TAG = "LaserySpaceShipIconUI"
    val sizes = SizesLaseryShip(shipViewBoxSize)
    override val hitBox = HitBoxSquareShip(sizes)
    val path = PathLaseryShip(shipViewBoxSize)
    val colors = ColorsLaseryShip()
    val magazine = MagazineLaseryShip(shipViewBoxSize)
    val laser = LaserLaseryShip(shipViewBoxSize)

    class LaserLaseryShip(shipViewBoxSize: Size) {
        val middleLight: Float = shipViewBoxSize.width * 0.03F
        val middleLightSurrounding: Float = 2F * middleLight
        val sideLight: Float = 6F * middleLight
//        strokeWidth = ui.laser.sideLight * ratioSize * particularBehavior,
//        val chargeFactor:
    }

    class MagazineLaseryShip(shipViewBoxSize: Size) {
        private val TAG = "MagazineLaseryShip"

        private val size: Size = shipViewBoxSize
        private val center: Offset = size.center
        private val sideHeightRatio: Float = 0.45F
        val stroke: Float = shipViewBoxSize.width * 0.06F

        val s1: Path = Path().apply {
            moveTo((-0.5F * stroke), (size.height * sideHeightRatio + 0.5F * stroke) )
            lineTo(center.x - (2F * stroke) , size.height + (1.5F * stroke))
        }
        val s2: Path = Path().apply {
            moveTo( size.width + (0.5F * stroke), (size.height * sideHeightRatio + 0.5F * stroke) )
            lineTo(center.x + (2F * stroke) , size.height + (1.5F * stroke))
        }
        val s3: Path = Path().apply {
            moveTo((-2F * stroke), (size.height * sideHeightRatio) + ( 2F * stroke))
            lineTo(center.x - (5.5F * stroke) , size.height - (0.5F * stroke))
        }
        val s4: Path = Path().apply {
            moveTo(size.width + (2F * stroke), (size.height * sideHeightRatio) + ( 2F * stroke))
            lineTo(center.x + (5.5F * stroke) , size.height - (0.5F * stroke))
        }

        fun getPathAmmo(m: Int): Path = when (m) {
            1 -> s1
            2 -> s2
            3 -> s3
            4 -> s4
            else -> {
                Log.e(TAG, "getPathAmmo: ERROR magazine ammmo m $m") ; Path()}
        }
    }
    class SizesLaseryShip(shipViewBoxSize: Size) {
        val shipSize = shipViewBoxSize
        val shipSizeDp = shipSize.toDpSize()
    }
    class HitBoxSquareShip(sizes: SizesLaseryShip) : HitBox {
        override val size: Size = sizes.shipSize * 0.7F
        override val sizeDp: DpSize = size.toDpSize()
    }
    class PathLaseryShip(shipViewBoxSize: Size) {
        private val size: Size = shipViewBoxSize
        val stroke: Float = shipViewBoxSize.width * 0.06F
        private val halfSize: Float = size.height / 2F
        private val center: Offset = size.center
        private val sideHeightRatio: Float = 0.4F

        private val topOffset = Offset(center.x, stroke)
        private val bottomOffset = Offset(center.x, size.height + stroke)
        private val bottomCentralBar: Offset = Offset(center.x, center.y * 0.70F)
        private val rightOffset = Offset(size.width - stroke, size.height * sideHeightRatio)
        private val leftOffset = Offset(stroke, size.height * sideHeightRatio)

        val shape = Path().apply {
            moveTo(topOffset.x, topOffset.y)
            lineTo(rightOffset.x, rightOffset.y)

            lineTo(bottomOffset.x, bottomOffset.y)
            lineTo(leftOffset.x, leftOffset.y)
            lineTo(topOffset.x, topOffset.y)
            close()

            moveTo(bottomCentralBar.x, bottomCentralBar.y)
            lineTo(topOffset.x, topOffset.y + (stroke/2))
        }
    }
    class ColorsLaseryShip() {
        val ship: Color = MyColor.laseryShip
        val border: Color = MyColor.applicationContrast
    }
}