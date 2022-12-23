package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable


@Composable
fun TutoButton(vm: MainScreenViewModel, navigator: Navigator) {
    val interactionSource = remember { MutableInteractionSource() }
    val clickable = Modifier.clickable (
        interactionSource = interactionSource,
        indication = null,
        onClick = {
            vm.handleTutoButtonClick(navigator)
        }
    )

    Box(
        Modifier
            .size( vm.ui.tutoButton.sizes.iconButtonHeightDp )
            .then(clickable)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = vm.ui.tutoButton.colors.tint ,
                style = Stroke(width = vm.ui.tutoButton.sizes.circleStrokeWidth )
            )
        }
        CenterComposable {
            Icon(
                modifier = Modifier.size( vm.ui.tutoButton.sizes.iconQuestionMarkHeightDp )
                ,
                imageVector = Icons.Sharp.QuestionMark,
                tint = vm.ui.tutoButton.colors.tint,
                contentDescription = vm.ui.tutoButton.text.description,
            )
        }
    }
}