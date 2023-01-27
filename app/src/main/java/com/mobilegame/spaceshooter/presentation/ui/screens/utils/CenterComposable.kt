package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun CenterComposable(id: String, content: @Composable () -> Unit) { Center(id, CenterType.Center, content) }

@Composable
fun CenterVertically(id: String, content: @Composable () -> Unit) = Center(id = id, type = CenterType.CenterVertically, content = content)

@Composable
fun CenterHorizontally(id: String, content: @Composable () -> Unit) = Center(id = id, type = CenterType.CenterHorizontally, content = content)

@Composable
private fun Center(id: String, type: CenterType, content: @Composable () -> Unit) {
    val constraints = remember { getCenterConstraintSet(id, type) }

    ConstraintLayout(constraints, Modifier.fillMaxSize() ) {
        Box(Modifier.layoutId(id)) {
            content.invoke()
        }
    }
}

private fun getCenterConstraintSet(id: String, type: CenterType) = ConstraintSet {
    val draw = createRefFor(id)

    constrain( draw ) {
        if (type == CenterType.Center || type == CenterType.CenterVertically) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }
        if (type == CenterType.Center || type == CenterType.CenterHorizontally) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        width = Dimension.wrapContent
        height = Dimension.wrapContent
    }
}

enum class CenterType {
    Center, CenterHorizontally , CenterVertically
}

@Composable
fun CenterComposableVertically(id: String, content: @Composable () -> Unit) {
    Row( modifier = Modifier.fillMaxHeight() ) {
        Column( modifier = Modifier
            .wrapContentSize()
            .align(Alignment.CenterVertically) ) {
            Box( modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                content.invoke()
            }
        }
    }
}

@Composable
fun CenterComposableHorizontally(content: @Composable () -> Unit) {
    Column( modifier = Modifier.fillMaxWidth()
    ) {
        Row( modifier = Modifier
            .wrapContentHeight()
            .align(Alignment.CenterHorizontally) ) {
            Box( modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                content.invoke()
            }
        }
    }
}
