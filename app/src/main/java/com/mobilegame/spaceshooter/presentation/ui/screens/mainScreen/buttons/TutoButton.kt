package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel


@Composable
fun TutoButton(vm: MainScreenViewModel) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = androidx.compose.ui.Modifier
            .size(vm.ui.tutorialButton.sizes.iconButtonHeightDp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = vm.ui.tutorialButton.colors.tint,
                style = Stroke(width = vm.ui.tutorialButton.sizes.circleStrokeWidth)
            )
        }
        Icon(
            modifier = Modifier.size(vm.ui.tutorialButton.sizes.iconQuestionMarkHeightDp)
            ,
            imageVector = Icons.Sharp.QuestionMark,
            tint = vm.ui.tutorialButton.colors.tint,
            contentDescription = vm.ui.tutorialButton.text.description
        )
    }
}