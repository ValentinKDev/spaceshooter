package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.HitBox
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.SpaceShipIconUIInterface
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.toDpSize
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CircleSpaceShipIconUI(override val shipViewBoxSize: Size): SpaceShipIconUIInterface {
    val TAG = "CircleSpaceShipIconUI"

    val sizes = SizesSpaceShipIcon(shipViewBoxSize)
    val points = PointsSpaceShipIcon(sizes)
    val ammunition = MunitionsRoundSpaceShipIcon(shipViewBoxSize.height)
    val colors = ColorsSpaceShipIcon()
    override val hitBox = HitBoxRoundShip(sizes)

    data class ColorsSpaceShipIcon(
        var strokes: Color = MyColor.applicationContrast,
        var body: Color = MyColor.roundShip,
//        var ammo: Color = MyColor.applicationContrast,
        val ammo: Color = MyColor.applicationContrast,
        var shoot: Color = MyColor.roundShip
    )

    class SizesSpaceShipIcon(shipBox: Size) {
        var shipBox: Size = shipBox
        var shipBoxDp: DpSize = shipBox.toDpSize()
        var strokeWidth: Float = shipBox.height * 0.06F
        var halfWidth: Float = shipBox.height * 0.5F
        var epsilon: Float = shipBox.height * 0.2F
    }

    class PointsSpaceShipIcon(sizes: SizesSpaceShipIcon) {
        var pTopCentralBar: Offset = Offset(sizes.halfWidth, 0F)
        var pBottomCentralBar: Offset = Offset(sizes.halfWidth, sizes.epsilon)
    }
    class HitBoxRoundShip(sizes: SizesSpaceShipIcon): HitBox {
        override val size: Size = sizes.shipBox.times(0.7F)
        override val sizeDp: DpSize = size.toDpSize()
    }

    class MunitionsRoundSpaceShipIcon(shipBox: Float) {
        val TAG = "MunitionsRoundSpaceShipIcon"
        val width = shipBox
        val radius: Float = width * 0.1F
        val innerRadius: Float = radius * 0.8F
        private val delta: Float = width * 0.25F
        private val distance: Float = (width / 2F) + delta
        private val center: Float = width / 2F
        private val openRadian: Double = (1.0 / 2.0)
        private val intervalRadian: Double = (2.0 - openRadian) / 9
        private val startRadian: Double = (3.0 / 4.0)
        val m1: Offset = Offset(Axe.X munition 1, Axe.Y munition 1)
        val m2: Offset = Offset(Axe.X munition 2, Axe.Y munition 2)
        val m3: Offset = Offset(Axe.X munition 3, Axe.Y munition 3)
        val m4: Offset = Offset(Axe.X munition 4, Axe.Y munition 4)
        val m5: Offset = Offset(Axe.X munition 5, Axe.Y munition 5)
        val m6: Offset = Offset(Axe.X munition 6, Axe.Y munition 6)
        val m7: Offset = Offset(Axe.X munition 7, Axe.Y munition 7)
        val m8: Offset = Offset(Axe.X munition 8, Axe.Y munition 8)
        val m9: Offset = Offset(Axe.X munition 9, Axe.Y munition 9)
        val m10: Offset = Offset(Axe.X munition 10, Axe.Y munition 10)

        private enum class Axe {
            X, Y
        }

        private infix fun Axe.munition(n: Int ): Float {
            val newAngle = ((n - 1) * intervalRadian) + startRadian
            return when (this) {
                Axe.X -> (cos(PI * newAngle).toFloat() * (distance)) + center
                Axe.Y -> (sin(PI * newAngle).toFloat() * (distance) * -1F ) + center
            }
        }
//        init {
//            Log.i(TAG, "init: ")
//        }
    }

//    init { Log.i(TAG, "init: ") }
    fun getAmmunitionOffset(n: Int): Offset = when (n) {
        1 -> ammunition.m5
        2 -> ammunition.m6
        3 -> ammunition.m4
        4 -> ammunition.m7
        5 -> ammunition.m3
        6 -> ammunition.m8
        7 -> ammunition.m2
        8 -> ammunition.m9
        9 -> ammunition.m1
        10 -> ammunition.m10
        else -> {
            eLog("SpaceShipVM::getAmmunitionOffset", "ERROR getMunition(ui, --> ${ammunition})")
            Offset.Zero
        }
    }
}