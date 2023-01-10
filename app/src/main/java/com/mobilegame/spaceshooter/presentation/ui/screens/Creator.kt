package com.mobilegame.spaceshooter.presentation.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
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
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.alpha
import com.mobilegame.spaceshooter.utils.extensions.toRad
import com.mobilegame.spaceshooter.utils.extensions.fromDp
import com.mobilegame.spaceshooter.utils.extensions.toRadian
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Creator(navigator: Navigator,vm: MainScreenViewModel = viewModel()) {
    CenterComposable(id = "creation") {
        Row {
            DrawD()
            Spacer(Modifier.width(10.dp))
//            DrawE()
        }
    }
}

@Composable
fun DrawE() {

}

@Composable
fun DrawD() {
        val listDEx = remember { getListCircleOffset(Offset(50.dp.fromDp(), 50.dp.fromDp()), 50.dp.fromDp(), (-95F..95F).toRadian())}
        val listDIn = remember { getListCircleOffset(Offset(50.dp.fromDp(), 50.dp.fromDp()), 25.dp.fromDp(), (-95F..95F).toRadian())}
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
        val pathDEx = Path().apply {
            if (listDEx.isEmpty()) eLog("ERROR", "list Offset size ${listDEx.size}")
            else {
                eLog("size", "list Offset size ${listDEx.size}")
                moveTo(listDEx[0].x - 90, listDEx[0].y)
                lineTo(listDEx[0].x, listDEx[0].y)
                var i = 1
                while (i in 1 until listDEx.size) {
                    val point = Offset(listDEx[i].x, listDEx[i].y)
                    lineTo(point.x, point.y)
                    i += 1
                }
                lineTo(listDEx[i-1].x - 90, listDEx[i-1].y)
                lineTo(listDEx[0].x - 90, listDEx[0].y)
            }
        }
        Canvas(
            Modifier
                .size(100.dp)
//                .background(Color.Yellow.alpha(0.5F))
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

fun getListCircleOffset(center: Offset, radius: Float, angleRange: ClosedFloatingPointRange<Float>): MutableList<Offset> {
    var angle = angleRange.start
    val list = mutableListOf<Offset>()
    while (angle <= angleRange.endInclusive) {
        list += getCircleOffsetAt(center, radius, angle)
        angle += 0.01F
    }
    return list
}

fun getCircleOffsetAt(center: Offset, radius: Float, angle: Float): Offset {
    val x = radius * cos(angle)
    val y = radius * sin(angle)
    return (Offset(center.x + x, center.y + y))
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
