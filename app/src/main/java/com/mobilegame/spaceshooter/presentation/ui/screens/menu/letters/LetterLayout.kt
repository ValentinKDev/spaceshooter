package com.mobilegame.spaceshooter.presentation.ui.screens.menu.letters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor

@Composable
internal fun LetterLayout(
    verticalPadding: Dp,
    contentWidthDp: Dp,
    endPadding: Dp? = contentWidthDp * 0.12F,
    content: @Composable () -> Unit
) {
    Row {
        Column {
            Box(
                Modifier
                    .height(verticalPadding)
                    .width(contentWidthDp)
                    .background(MyColor.applicationBackground)
            )
            content.invoke()
            Box(
                Modifier
                    .height(verticalPadding)
                    .width(contentWidthDp)
                    .background(MyColor.applicationBackground)
            )
        }
        endPadding?.run {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(this)
                    .background(MyColor.applicationBackground)
            )
        }
    }
}