package com.mobilegame.spaceshooter.presentation.ui.screens.utils.padding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterType
import com.mobilegame.spaceshooter.utils.extensions.alpha

//@Composable
//fun PaddingWithConstraints(
//    id: String,
//    topPaddingRatio: Float = 0F,
//    bottomPaddingRatio: Float = 0F,
//    startPaddingRatio: Float = 0F,
//    endPaddingRatio: Float = 0F,
//    content: @Composable () -> Unit,
//) {
//    val verticalElementWeight: Float? = if ( topPaddingRatio + bottomPaddingRatio < 1F ) 1F - topPaddingRatio - bottomPaddingRatio else null
//    val horizontalElementWeight: Float? = if ( endPaddingRatio + startPaddingRatio < 1F ) 1F - startPaddingRatio - endPaddingRatio else null
//
//    val
//    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//        Box(
//            Modifier
//                .layoutId(id)
//                .constrainAs(createRef()) {
//                    top.linkTo(parent.to, )
//                }
//        ) {
//            content.invoke()
//        }
//    }
//
////    val topPaddingModifier = Modifier.fillMaxWidth.
//    verticalElementWeight?.let {
//        horizontalElementWeight?.let {
//            Column(Modifier.fillMaxSize()) {
//                if (topPaddingRatio == 0F)
//                    Row( content = {}, modifier = Modifier.fillMaxWidth().background(enableColor?.let { Color.Red.alpha(0.5F) } ?: Color.Transparent))
//                else
//                    Row( content = {}, modifier = Modifier.fillMaxWidth().weight(topPaddingRatio).background(enableColor?.let { Color.Red.alpha(0.5F) } ?: Color.Transparent))
//                Row(Modifier.fillMaxWidth().weight(verticalElementWeight)) {
//                    if (startPaddingRatio == 0F)
//                        Column( content = {}, modifier = Modifier.fillMaxHeight().background(enableColor?.let { Color.Green.alpha(0.4F) } ?: Color.Transparent))
//                    else
//                        Column( content = {}, modifier = Modifier.fillMaxHeight().weight(startPaddingRatio).background(enableColor?.let { Color.Green.alpha(0.4F) } ?: Color.Transparent))
//                    Column( Modifier.fillMaxWidth().weight(horizontalElementWeight))
//                    {
//                        content.invoke()
//                    }
//                    if (endPaddingRatio == 0F)
//                        Column( content = {}, modifier = Modifier.fillMaxHeight().background(enableColor?.let { Color.Yellow.alpha(0.4F) } ?: Color.Transparent))
//                    else
//                        Column( content = {}, modifier = Modifier.fillMaxHeight().weight(endPaddingRatio).background(enableColor?.let { Color.Yellow.alpha(0.4F) } ?: Color.Transparent))
//                }
//                if (bottomPaddingRatio == 0F)
//                    Row( content = {}, modifier = Modifier.fillMaxWidth().background(enableColor?.let { Color.Blue.alpha(0.4F) } ?: Color.Transparent))
//                else
//                    Row( content = {}, modifier = Modifier.fillMaxWidth().weight(bottomPaddingRatio).background(enableColor?.let { Color.Blue.alpha(0.4F) } ?: Color.Transparent))
//            }
//        }
//    }
//}
//
////private fun getCenterConstraintSet(id: String, type: CenterType) = ConstraintSet {
////    val draw = createRefFor(id)
////
////    constrain( draw ) {
//////        if (type == CenterType.Center || type == CenterType.CenterVertically) {
////            top.linkTo(parent.top)
////            bottom.linkTo(parent.bottom)
//////        }
//////        if (type == CenterType.Center || type == CenterType.CenterHorizontally) {
////            start.linkTo(parent.start)
////            end.linkTo(parent.end)
//////        }
////        width = Dimension.wrapContent
////        height = Dimension.wrapContent
////    }
////}
