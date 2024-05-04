package com.mobilegame.spaceshooter.presentation.ui.template

import androidx.compose.runtime.Composable
import com.mobilegame.spaceshooter.logic.model.navigation.NavigationDestination
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.logic.model.navigation.PressureHandler


@Composable
fun TemplateWithoutBand(
    backNav: Screens? = null,
    backPressureHandler: PressureHandler = PressureHandler(backNav),
    ui: TemplateUI,
    header: @Composable () -> Unit,
    body: @Composable () -> Unit,
) {
    Template(
        type = TemplatesType.WithoutHeadBand,
//        backNav = backNav,
        backPressureHandler = backPressureHandler,
        ui = ui,
        header = { header.invoke() },
        headBand = { },
        body = { body.invoke()},
    )
}

@Composable
fun TemplateWithBand(
//    navigator: Navigator,
//    backNav: Screens,
    backNav: Screens? = null,
    backPressureHandler: PressureHandler = PressureHandler(backNav),
    ui: TemplateUI,
    header: @Composable () -> Unit,
    band: @Composable () -> Unit,
    body: @Composable () -> Unit,
) {
    Template(
        type = TemplatesType.WithHeadBand,
//        navigator = navigator,
//        backNav = backNav,
        backPressureHandler = backPressureHandler,
        ui = ui,
        header = { header.invoke() },
        headBand = { band.invoke() },
        body = { body.invoke()}
    )
}