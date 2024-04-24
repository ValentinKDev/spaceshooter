package com.mobilegame.spaceshooter.presentation.ui.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.navigation.NavigationDestination
import com.mobilegame.spaceshooter.logic.model.navigation.Screens
import com.mobilegame.spaceshooter.logic.model.navigation.PressureViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.template.backButton.BackButton

@Composable
internal fun Template(
    type: TemplatesType,
    navigator: Navigator,
//    backNav: NavigationDestination,
    backNav: Screens,
//    instantBackNav: Boolean?,
    ui: TemplateUI,
//    vm: PressureViewModel = viewModel(),
    vm: PressureViewModel = PressureViewModel(),
    header: @Composable () -> Unit,
    headBand: @Composable () -> Unit,
    body: @Composable () -> Unit,
) {
    val constraints = remember {
        ConstraintSet {
            val head = createRefFor(ui.header.id)
            val topDelimiter = createRefFor(ui.topDelimiter.id)
            val band = createRefFor(ui.band.id)
            val bottomDelimiter = createRefFor(ui.bottomDelimiter.id)
            val space = createRefFor(ui.body.id)
            val backButton = createRefFor(ui.backButton.id)

            constrain( head ) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(topDelimiter.top)
                width = Dimension.fillToConstraints
                height = Dimension.percent(ui.percent.header)
            }

            constrain( backButton ) {
                top.linkTo(head.top)
                start.linkTo(head.start)
                bottom.linkTo(head.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }

            constrain( topDelimiter ) {
                top.linkTo(head.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.percent(ui.percent.topDelimiter)
            }

            constrain( band ) {
                top.linkTo(topDelimiter.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomDelimiter.top)
                width = Dimension.fillToConstraints
                height = Dimension.percent(ui.percent.band)
            }

            constrain( bottomDelimiter ) {
                top.linkTo(band.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(space.top)
                width = Dimension.fillToConstraints
                height = Dimension.percent(ui.percent.bottomDelimiter)
            }

            constrain( space ) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.percent(
                    when (type) {
                        TemplatesType.WithoutHeadBand -> ui.percent.bodyWithoutBand
                        TemplatesType.WithHeadBand -> ui.percent.bodyWithBand
                    }
                )
            }
        }
    }

//    ConstraintLayout(constraints, Modifier.fillMaxSize().background(MyColor.applicationBackground)) {
    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Box(
            Modifier
                .layoutId(ui.header.id)
        ) { header.invoke() }
        Box(
            Modifier.layoutId(ui.backButton.id)
        ) {
            BackButton(
                vm = vm,
                backNav = backNav,
                navigator = navigator,
                ui = ui.backButton,
//            instant = instantBackNav
            )
        }
        Box(
            Modifier
                .background(MyColor.Platinium)
                .layoutId(ui.topDelimiter.id) )
        if (type == TemplatesType.WithHeadBand) {
            Box(
                Modifier
                    .layoutId(ui.band.id)
            ) { headBand.invoke() }
            Box(
                Modifier
                    .background(MyColor.Platinium)
                    .layoutId(ui.bottomDelimiter.id) )
        }
        Box(
            Modifier
                .layoutId(ui.body.id)
//                .background(Color.Transparent)
        ) { body.invoke() }
    }
}
