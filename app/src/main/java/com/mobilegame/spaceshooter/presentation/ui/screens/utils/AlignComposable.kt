package com.mobilegame.spaceshooter.presentation.ui.screens.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AlignComposableToEnd(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.End)) {
            content.invoke()
        }
    }
}

@Composable
fun AlignComposableToStart(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Start)) {
            content.invoke()
        }
    }
}

@Composable
fun AlignComposableToTop(content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Top)) {
            content.invoke()
        }
    }
}

@Composable
fun AlignComposableToBottom(content: @Composable () -> Unit) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Bottom)) {
            content.invoke()
        }
    }
}
