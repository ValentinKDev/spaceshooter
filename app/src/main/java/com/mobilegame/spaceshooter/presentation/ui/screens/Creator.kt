package com.mobilegame.spaceshooter.presentation.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.getListCircleOffset
import com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters.getListEllipseOffset
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.utils.extensions.*

@Composable
fun Creator(navigator: Navigator,vm: MainScreenViewModel = viewModel()) {
    CenterComposable(id = "creation") {
        Row {
            DrawD()
            Spacer(Modifier.width(10.dp))
            DrawU()
            Spacer(Modifier.width(10.dp))
//            DrawS()
//            DrawEllipse()
        }
    }
}

@Composable
fun DrawU() {
    val listEllipseOffsetExt = remember {
        getListEllipseOffset(
            center = Offset(50.dp.DpToPixel(), 69.dp.DpToPixel()),
            radius = 30.5.dp.DpToPixel(),
            alphaX = 1.6F,
            betaY = 1F,
            angleRange = (-180F..0F).degreeToRadianRange()
        )
    }
    val listEllipseOffsetInt = remember {
        getListEllipseOffset(
            center = Offset(50.dp.DpToPixel(), 65.dp.DpToPixel()),
            radius = 16.5.dp.DpToPixel(),
            alphaX = 1.4F,
            betaY = 0.7F,
            angleRange = (-180F..0F).degreeToRadianRange()
        ).reverseElements()
//            .reverseElements()
    }
//    val pathellipse = path().apply {
//        moveTo(listEllipseOffsetInt[0].x, listEllipseOffsetInt[0].y - 127F)
//        lineTo(listEllipseOffsetInt[0].x, listEllipseOffsetInt[0].y)
//        var i = 1
//        while (i in 1 until listEllipseOffsetInt.size) {
//            val point = Offset(listEllipseOffsetInt[i].x, listEllipseOffsetInt[i].y)
//            lineTo(point.x, point.y)
//            i += 1
//        }
//        lineTo(listEllipseOffsetInt[i - 1].x, listEllipseOffsetInt[i-1].y - 127F)
//    }
    val pathU = Path().apply {
        moveTo(listEllipseOffsetExt[0].x, listEllipseOffsetExt[0].y - 135F)
        lineTo(listEllipseOffsetExt[0].x, listEllipseOffsetExt[0].y)
        var i = 1
        while (i in 1 until listEllipseOffsetExt.size) {
            val point = Offset(listEllipseOffsetExt[i].x, listEllipseOffsetExt[i].y)
            lineTo(point.x, point.y)
            i += 1
        }
        lineTo(listEllipseOffsetExt[i-1].x, listEllipseOffsetExt[i-1].y - 135F)

        lineTo(listEllipseOffsetInt[0].x, listEllipseOffsetInt[0].y - 127F)
        lineTo(listEllipseOffsetInt[0].x, listEllipseOffsetInt[0].y)
        i = 1
        while (i in 1 until listEllipseOffsetInt.size) {
            val point = Offset(listEllipseOffsetInt[i].x, listEllipseOffsetInt[i].y)
            lineTo(point.x, point.y)
            i += 1
        }
        lineTo(listEllipseOffsetInt[i - 1].x, listEllipseOffsetInt[i-1].y - 127F)

        lineTo(listEllipseOffsetExt[0].x, listEllipseOffsetExt[0].y - 135F)
    }
    Canvas(
        Modifier
            .size(100.dp)
//            .background(Color.Black)
    ) {
        drawPath(
            path = pathU,
            color = MyColor.Platinium,
            style = Stroke(width = 5F, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}

@Composable
fun getPathExteriorD(): Path {
    val listDEx = remember { getListCircleOffset(Offset(50.dp.DpToPixel(), 50.dp.DpToPixel()), 50.dp.DpToPixel(), (-95F..95F).degreeToRadianRange())}
    val pathDEx = Path().apply {
        moveTo(listDEx[0].x - 80, listDEx[0].y)
        lineTo(listDEx[0].x, listDEx[0].y)
        var i = 1
        while (i in 1 until listDEx.size) {
            val point = Offset(listDEx[i].x, listDEx[i].y)
            lineTo(point.x, point.y)
            i += 1
        }
        lineTo(listDEx[i-1].x - 80, listDEx[i-1].y)
        lineTo(listDEx[0].x - 80, listDEx[0].y)
    }
    return pathDEx
}
@Composable
fun getPathInteriorD(): Path {
    val listDIn = remember { getListCircleOffset(Offset(50.dp.DpToPixel(), 50.dp.DpToPixel()), 25.dp.DpToPixel(), (-95F..95F).degreeToRadianRange())}

    val pathDIn = Path().apply {
        moveTo(listDIn[0].x - 30, listDIn[0].y)
        lineTo(listDIn[0].x, listDIn[0].y)
        var i = 1
        while (i in 1 until listDIn.size) {
            val point = Offset(listDIn[i].x, listDIn[i].y)
            lineTo(point.x, point.y)
            i += 1
        }
        lineTo(listDIn[i - 1].x - 30, listDIn[i - 1].y)
        lineTo(listDIn[0].x - 30, listDIn[0].y)
    }
    return pathDIn
}

@Composable
fun DrawD() {
    val pathDIn = getPathInteriorD()
    val pathDEx = getPathExteriorD()
    Canvas(
        Modifier
            .size(100.dp)
    ) {
        drawPath(
            path = pathDEx,
            color = MyColor.Platinium,
            style = Stroke(width = 5F, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
        drawPath(
            path = pathDIn,
            color = MyColor.Platinium,
            style = Stroke(width = 5F, cap = StrokeCap.Round, join = StrokeJoin.Round),
        )
    }
}


@Composable
fun TextTruc() {
    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val yellowBox = createRefFor("yellowBox")

//        constrain(greenBox) {
//            top.linkTo(parent.top)
//            start.linkTo(parent.start)
//            width = Dimension.value(100.dp)
//            height = Dimension.percent(0.1F)
//        }
//        constrain(yellowBox) {
//            top.linkTo(greenBox.bottom)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//            bottom.linkTo(redBox.bottom)
//            width = Dimension.fillToConstraints
//            height = Dimension.fillToConstraints
//        }
        constrain(redBox) {
            top.linkTo(parent.top)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
            width = Dimension.wrapContent
            height = Dimension.wrapContent
        }
    }
    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(Color.Green)
                .layoutId("greenBox")
        )
        Box(
            Modifier
                .background(Color.Red)
                .layoutId("redBox")
        ) {
            Box(modifier = Modifier.size(40.dp))
        }
        Box(
            Modifier
                .background(Color.Yellow)
                .layoutId("yellowBox")
        )
    }
}
