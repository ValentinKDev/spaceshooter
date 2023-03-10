package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CircleSpaceShipIconUI(shipBox: Size) {
    private val TAG = object{}.javaClass.enclosingClass

    val sizes = SizesSpaceShipIcon(shipBox.height)
    val points = PointsSpaceShipIcon(sizes)
    val colors = ColorsSpaceShipIcon()
    val ammunition = MunitionsSpaceShipIcon(shipBox.height)

    data class ColorsSpaceShipIcon(
        var strokes: Color = MyColor.applicationContrast,
        var body: Color = MyColor.applicationBackground,
        var ammo: Color = MyColor.applicationContrast,
        var shoot: Color = MyColor.defaultShip
    )

    class SizesSpaceShipIcon(shipBox: Float) {
        var shipBoxDp: Dp = shipBox.toDp()
        var strokeWidth: Float = shipBox * 0.06F
        var halfWidth: Float = shipBox * 0.5F
        var epsilon: Float = shipBox * 0.2F
    }

    class PointsSpaceShipIcon(sizes: SizesSpaceShipIcon) {
        var pTopCentralBar: Offset = Offset(sizes.halfWidth, 0F)
        var pBottomCentralBar: Offset = Offset(sizes.halfWidth, sizes.epsilon)
    }

    class MunitionsSpaceShipIcon(shipBox: Float) {
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
        init {
            displayDataUI?.let {
                wLog("MunitionsSpaceShipIcon::init", "init munition")
                vLog("MunitionsSpaceShipIcon::init", "radius $radius")
            }
        }
    }

    init {
        displayDataUI?.let {
            vLog("SpaceShipIconUI::init", "shipBox $shipBox")
            vLog("SpaceShipIconUI::init", "shipBoxDp ${sizes.shipBoxDp}")
        }
    }
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