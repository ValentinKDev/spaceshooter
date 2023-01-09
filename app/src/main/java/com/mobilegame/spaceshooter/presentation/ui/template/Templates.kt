package com.mobilegame.spaceshooter.presentation.ui.template

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator


@Composable
fun TemplateWithoutBand(
    navigator: Navigator,
    backNav: Screens,
    header: @Composable () -> Unit,
    emptySpace: @Composable () -> Unit,
) {
    Template(
        type = TemplatesType.WithoutHeadBand,
        navigator = navigator,
        backNav = backNav,
        header = { header.invoke() },
        headBand = { },
        emptySpace = { emptySpace.invoke()}
    )
}

@Composable
fun TemplateWithBand(
    navigator: Navigator,
    backNav: Screens,
    header: @Composable () -> Unit,
    band: @Composable () -> Unit,
    emptySpace: @Composable () -> Unit,
) {
    Template(
        type = TemplatesType.WithHeadBand,
        navigator = navigator,
        backNav = backNav,
        header = { header.invoke() },
        headBand = { band.invoke() },
        emptySpace = { emptySpace.invoke()}
    )
}

