package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier



@Composable
fun AlignComposableToCenterEnd(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        content.invoke()
    }
}

@Composable
fun AlignComposableToCenterStart(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        content.invoke()
    }
}

@Composable
fun AlignComposableToEnd(content: @Composable () -> Unit) {
    AlignComposableHorizontally(alignment = Alignment.End) { content.invoke() }
}

@Composable
fun AlignComposableToStart(content: @Composable () -> Unit) {
    AlignComposableHorizontally(alignment = Alignment.Start) { content.invoke() }
}

@Composable
private fun AlignComposableHorizontally(alignment: Alignment.Horizontal, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(alignment)) {
            content.invoke()
        }
    }
}

@Composable
fun AlignComposableToTop(content: @Composable () -> Unit) {
    AlignComposableVertically(alignment = Alignment.Top) { content.invoke() }
}

@Composable
fun AlignComposableToBottom(content: @Composable () -> Unit) {
    AlignComposableVertically(alignment = Alignment.Bottom) { content.invoke() }
}

@Composable
private fun AlignComposableVertically(alignment: Alignment.Vertical, content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(alignment)) {
            content.invoke()
        }
    }
}
