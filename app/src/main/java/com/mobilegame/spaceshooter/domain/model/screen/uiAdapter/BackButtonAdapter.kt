package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

object BackButtonAdapter {
    val colors = ColorsBackButton
    val points = PointsBackButton
    val sizes = SizesBackButton
    val ratios = RatiosBackButton

    object RatiosBackButton {
        var canvasHeightPercent = 0.75F
    }
    object SizesBackButton {
        var boxHeight = 0F
        var boxHeightDp = Dp.Unspecified
        var canvas = 0F
        var canvasDp = Dp.Unspecified

        var delta = 0F
    }
    object ColorsBackButton {
        val contrast = MyColor.applicationContrast
        val background = MyColor.applicationBackground
    }
    object PointsBackButton {
        var topStart = Offset.Zero
        var topEnd = Offset.Zero
        var bottomStart = Offset.Zero
        var bottomEnd = Offset.Zero

        var p1 = Offset.Zero
        var p1Long = Offset.Zero
        var p2 = Offset.Zero
        var p3 = Offset.Zero
        var p4 = Offset.Zero
        var p5 = Offset.Zero
        var p6 = Offset.Zero
        var p7 = Offset.Zero
        var p8 = Offset.Zero
        var p9 = Offset.Zero
        var p9Long = Offset.Zero
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F

    fun initPoints() {
        points.topStart = Offset(x = 0F, y = 0F)
        points.topEnd = Offset(x = sizes.canvas, y = 0F)
        points.bottomStart = Offset(x = 0F, y = sizes.canvas)
        points.bottomEnd = Offset(x = sizes.canvas, y = sizes.canvas)

        val delta = 0.05F * sizes.canvas
        val epsilon = 0.025F * sizes.canvas
        val halfSize = 0.5F * sizes.canvas

        val xArrowPoint = 3 * delta
        val oneFifth = sizes.canvas * (1F/5F)
        val twoFifth = sizes.canvas * (2F/5F) - delta
        val threeFifth = sizes.canvas * (3F/5F) + delta
        val fourthFifth = sizes.canvas * (4F/5F)

        val half1 = halfSize - (0.08F * sizes.canvas)
        val half2 = halfSize + (0.08F * sizes.canvas)

        points.p1 = Offset(x = sizes.canvas - epsilon, y = twoFifth)
        points.p1Long = Offset(x = sizes.canvas, y = twoFifth)
        points.p2 = Offset(x = half2, y = twoFifth)
        points.p3 = Offset(x = half2, y = oneFifth)
        points.p4 = Offset(x = half1, y = oneFifth)

        points.p5 = Offset(x = xArrowPoint, y = halfSize)

        points.p6 = Offset(x = half1, y = fourthFifth)
        points.p7 = Offset(x = half2, y = fourthFifth)
        points.p8 = Offset(x = half2, y = threeFifth)
        points.p9 = Offset(x = sizes.canvas - epsilon , y = threeFifth)
        points.p9Long = Offset(x = sizes.canvas, y = threeFifth)

        displayDataUI?.let {
            wLog("BackButton::initPoints", "points")
            vLog("BackButton::initPoints", "topStart ${points.topStart}")
            vLog("BackButton::initPoints", "topEnd ${points.topEnd}")
            vLog("BackButton::initPoints", "bottomStart ${points.bottomStart}")
            vLog("BackButton::initPoints", "bottomEnd ${points.bottomEnd}")
        }
    }

    fun initSize() {
        sizes.canvas = ratios.canvasHeightPercent * sizes.boxHeight
        sizes.canvasDp = sizes.canvas.toDp(density)

        displayDataUI?.let {
            wLog("BackButton::initSize", "size")
            vLog("BackButton::initSize", "canvas ${sizes.canvas}")
            vLog("BackButton::initSize", "canvasDp ${sizes.canvasDp}")
            vLog("BackButton::initSize", "delta ${sizes.delta}")
        }
    }

    fun create(context: Context, boxHeight: Float): BackButtonAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        sizes.boxHeight = boxHeight
        sizes.boxHeightDp = boxHeight.toDp(density)

        displayDataUI?.let {
            wLog("BackButton::create", "start")
            vLog("BackButton::create", "widthFull = $widthFull")
            vLog("BackButton::create", "heightFull = $heightFull")
            vLog("BackButton::create", "density = $density")
            vLog("BackButton::create", "boxHeight = ${sizes.boxHeight}")
            vLog("BackButton::create", "boxHeightDp = ${sizes.boxHeightDp}")
        }

        initSize()
        initPoints()
        return this
    }
}