package com.mobilegame.spaceshooter.presentation.ui.screens.aboutUsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.aboutScreen.AboutScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.template.backButton.BackButton
import com.mobilegame.spaceshooter.utils.analyze.eLog

@Composable
fun AboutUsScreen(vm: AboutScreenViewModel = viewModel()) {
    LaunchedEffect(true) {
        eLog("AboutUsScreen", "AboutUsScreen launch")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(vm.ui.colors.background)
    ) {
        Box(
            modifier = Modifier
//                .background(Color.Green)
                .padding(start = 10.dp)
                .size(vm.templateUI.backButton.sizes.boxHeightDp)
                .align(Alignment.TopStart)
//                .size(templ)
//                .wrapContentSize()
        ) {
            BackButton(
                handler = vm.backNavHandler,
                ui = vm.templateUI.backButton,
            )
        }
        Text(
            text = vm.text2,
            color = vm.ui.colors.textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}