package com.mobilegame.spaceshooter.presentation.theme.mainTemplate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.Delimiter

@Composable
fun MainTemplate(
    header: @Composable () -> Unit,
    emptySpace: @Composable () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight( MainTemplate.headerHeightWeight )
        ) {
            header.invoke()
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight( MainTemplate.delimiterHeightWeight )
                .background( MainTemplate.delimiter.colors.background )
        ) {
            Delimiter()
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight( MainTemplate.emptySpaceHeightWeight )
        ) {
            emptySpace.invoke()
        }
    }
}