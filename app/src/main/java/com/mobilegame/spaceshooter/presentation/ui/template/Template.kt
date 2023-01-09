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
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.PressureNavigationViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.template.MainTemplateUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.backButton.BackButton

@Composable
internal fun Template(
    type: TemplatesType,
    navigator: Navigator,
    backNav: Screens,
    vm: PressureNavigationViewModel = PressureNavigationViewModel(backNav),
    header: @Composable () -> Unit,
    headBand: @Composable () -> Unit,
    emptySpace: @Composable () -> Unit,
) {
    val constraints = remember {
        ConstraintSet {
            val head = createRefFor(MainTemplateUI.header.id)
            val topDelimiter = createRefFor(MainTemplateUI.topDelimiter.id)
            val band = createRefFor(MainTemplateUI.band.id)
            val bottomDelimiter = createRefFor(MainTemplateUI.bottomDelimiter.id)
            val space = createRefFor(MainTemplateUI.emptySpace.id)
            val backButton = createRefFor(MainTemplateUI.backButton.id)

            constrain( head ) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(topDelimiter.top)
                width = Dimension.fillToConstraints
                height = Dimension.percent(MainTemplateUI.header.percent.height)
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
                height = Dimension.percent(MainTemplateUI.topDelimiter.percent.height)
            }

            constrain( band ) {
                top.linkTo(topDelimiter.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottomDelimiter.top)
                width = Dimension.fillToConstraints
                height = Dimension.percent(MainTemplateUI.band.percent.height)
            }

            constrain( bottomDelimiter ) {
                top.linkTo(band.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(space.top)
                width = Dimension.fillToConstraints
                height = Dimension.percent(MainTemplateUI.bottomDelimiter.percent.height)
            }

            constrain( space ) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                height = Dimension.percent(
                    when (type) {
                        TemplatesType.WithoutHeadBand -> MainTemplateUI.emptySpace.percent.heightWithoutBand
                        TemplatesType.WithHeadBand -> MainTemplateUI.emptySpace.percent.heightWithBand
                    }
                )
            }
        }
    }
    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Box(
            Modifier
                .layoutId(MainTemplateUI.header.id)
        ) { header.invoke() }
        Box(
            Modifier.layoutId(MainTemplateUI.backButton.id)
        ) { BackButton(vm, navigator = navigator) }
        Box(
            Modifier
                .background(MyColor.Platinium)
                .layoutId(MainTemplateUI.topDelimiter.id) )
        if (type == TemplatesType.WithHeadBand) {
            Box(
                Modifier
                    .layoutId(MainTemplateUI.band.id)
            ) { headBand.invoke() }
            Box(
                Modifier
                    .background(MyColor.Platinium)
                    .layoutId(MainTemplateUI.bottomDelimiter.id) )
        }
        Box(
            Modifier
                .layoutId(MainTemplateUI.emptySpace.id)
        ) { emptySpace.invoke() }
    }
}
