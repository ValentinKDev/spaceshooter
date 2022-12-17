package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenterComposable(content: @Composable () -> Unit) {
    Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) { content.invoke() }
}

@Composable
fun CenterComposableVertically(content: @Composable () -> Unit) {
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
