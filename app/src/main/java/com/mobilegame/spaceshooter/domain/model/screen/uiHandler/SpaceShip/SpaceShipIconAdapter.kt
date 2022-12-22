package com.mobilegame.spaceshooter.domain.model.screen.uiHandler.SpaceShip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class SpaceShipIconAdapter(type: SpaceShipType = SpaceShipType.Default, shipBox: Float) {
    val sizes = SizesSpaceShipIcon()
    val points = PointsSpaceShipIcon()
    val colors = ColorsSpaceShipIcon()
    val munitions = MunitionsSpaceShipIcon()

    data class ColorsSpaceShipIcon (
        var strokes: Color = MyColor.applicationContrast,
        var body: Color = MyColor.applicationBackground,
        var munitions: Color = MyColor.applicationContrast
    )

    data class SizesSpaceShipIcon (
        var shipBox: Float = 0F,
        var shipBoxDp: Dp = Dp.Unspecified,

        var strokeWidth: Float = 0F,
        var halfWidth: Float = 0F,
        var epsilon: Float = 0F,
    )

    data class PointsSpaceShipIcon (
        var pTopCentralBar: Offset = Offset.Zero,
        var pBottomCentralBar: Offset = Offset.Zero,
    )

    data class MunitionsSpaceShipIcon (
        var distance: Float = 0F,
        var radius: Float = 0F,
        var delta: Float = 0F,
        var center: Float = 0F,
        var intervalRadian: Double = 0.0,
        var openRadian: Double = 0.0,
        var startRadian: Double = 0.0,

        var m1: Offset = Offset.Zero,
        var m2: Offset = Offset.Zero,
        var m3: Offset = Offset.Zero,
        var m4: Offset = Offset.Zero,
        var m5: Offset = Offset.Zero,
        var m6: Offset = Offset.Zero,
        var m7: Offset = Offset.Zero,
        var m8: Offset = Offset.Zero,
        var m9: Offset = Offset.Zero,
        var m10: Offset = Offset.Zero,
    )

    private enum class Axe {
        X, Y
    }

    private infix fun Axe.munition(n: Int ): Float {
        val newAngle = ((n - 1) * munitions.intervalRadian) + munitions.startRadian
        return when (this) {
            Axe.X -> (cos(PI * newAngle).toFloat() * (munitions.distance)) + munitions.center
            Axe.Y -> (sin(PI * newAngle).toFloat() * (munitions.distance) * -1F ) + munitions.center
        }
    }

    fun initMunitions() {
        val width = sizes.shipBox
        munitions.radius = sizes.shipBox * 0.1F
        munitions.delta = sizes.shipBox * 0.25F
        munitions.distance =  (width / 2F) + munitions.delta
        munitions.center = width / 2F

        munitions.openRadian = (1.0 / 2.0)
        munitions.intervalRadian = (2.0 - munitions.openRadian) / 9

        munitions.startRadian = (3.0 / 4.0)
        val p1x: Float = (cos(PI * munitions.startRadian).toFloat() * (munitions.distance)) + munitions.center
        val p1y: Float = (sin(PI * munitions.startRadian).toFloat() * (munitions.distance) * -1F ) + munitions.center
        munitions.m1 = Offset(p1x, p1y)
        munitions.m2 = Offset(Axe.X munition 2, Axe.Y munition 2)
        munitions.m3 = Offset(Axe.X munition 3, Axe.Y munition 3)
        munitions.m4 = Offset(Axe.X munition 4, Axe.Y munition 4)
        munitions.m5 = Offset(Axe.X munition 5, Axe.Y munition 5)
        munitions.m6 = Offset(Axe.X munition 6, Axe.Y munition 6)
        munitions.m7 = Offset(Axe.X munition 7, Axe.Y munition 7)
        munitions.m8 = Offset(Axe.X munition 8, Axe.Y munition 8)
        munitions.m9 = Offset(Axe.X munition 9, Axe.Y munition 9)
        munitions.m10 = Offset(Axe.X munition 10, Axe.Y munition 10)

        displayDataUI?.let {
            vLog("SpaceShipIconAdapter::initMunitions", "r ${munitions.radius}")
        }
    }

    fun initShip() {
        sizes.strokeWidth = sizes.shipBox * 0.06F
        sizes.halfWidth = sizes.shipBox * 0.5F
        sizes.epsilon = sizes.shipBox * 0.2F
        points.pTopCentralBar = Offset(sizes.halfWidth, 0F)
        points.pBottomCentralBar = Offset(sizes.halfWidth, sizes.epsilon)

        displayDataUI?.let {
            vLog("SpaceShipIconAdapter::initShape", "strokeWidth ${sizes.strokeWidth}")
            vLog("SpaceShipIconAdapter::initShape", "halfWidth ${sizes.halfWidth}")
            vLog("SpaceShipIconAdapter::initShape", "epsilon ${sizes.epsilon}")
        }
    }

    init {
        sizes.shipBox = shipBox
        sizes.shipBoxDp = shipBox.toDp()

        displayDataUI?.let {
            wLog("SpaceShipIconAdapter::init", type.name)
            vLog("SpaceShipIconAdapter::init", "shipBox $shipBox")
            vLog("SpaceShipIconAdapter::init", "shipBoxDp ${sizes.shipBoxDp}")
        }
        initShip()
        initMunitions()
    }

}