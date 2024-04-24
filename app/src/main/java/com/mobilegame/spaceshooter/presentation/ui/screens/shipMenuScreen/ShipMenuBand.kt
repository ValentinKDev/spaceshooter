package com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen

import ShipMenuViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.addNavArg

@Composable
fun ShipMenuBand(vm: ShipMenuViewModel, gameStats: TryAgainStats? = null) {
    val ui = remember {vm.shipMenuUI.band}
    val shipType = remember { vm.shipPicking.shipType }.collectAsState()

    val constraints = remember {
        ConstraintSet {
            val shipName = createRefFor(ui.ids.shipName)
            val statsTextBox = createRefFor(ui.ids.statsTextBox)
            val shipStatsBarBox = createRefFor(ui.ids.statsBarBox)
            val gameStatsBarBox = createRefFor(ui.ids.gameStatsBarBox)

            constrain(shipName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                height = Dimension.wrapContent
                centerVerticallyTo(parent)
            }
            constrain(gameStatsBarBox) {
                start.linkTo(shipName.absoluteRight)
                bottom.linkTo(shipName.bottom)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
            }
            constrain(statsTextBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
                centerVerticallyTo(parent)
            }
            constrain(shipStatsBarBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(statsTextBox.start)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
                centerVerticallyTo(parent)
            }
        }
    }
    ConstraintLayout(constraints,
        Modifier
            .fillMaxSize()
            .background(MyColor.applicationBackground)) {
        Text(
            modifier = Modifier.layoutId(ui.ids.shipName),
            text = gameStats?.lastGame?.name ?: shipType.value.info.name,
            color = MyColor.applicationText,
            style = ui.sizes.shipNameTextStyle,
        )
        gameStats?.let {
            Row(
                modifier = Modifier.layoutId(ui.ids.gameStatsBarBox)
                    .padding(start = 10.dp)
//                    .background(Color.Green)
                    .height(ui.sizes.gameStatBoxHeightDp)
//                    .fillMaxHeight(),
                ,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = ui.ids.wins + "  ${gameStats.wins}   ",
                    color = MyColor.applicationText,
                    style = ui.sizes.gameStatTextStyle,
                )
                Text(
                    text = ui.ids.loses + "  ${gameStats.losses}   ",
                    color = MyColor.applicationText,
                    style = ui.sizes.gameStatTextStyle,
                )
                Text(
                    text = ui.ids.streak + "  ${gameStats.streak}   ",
                    color = MyColor.applicationText,
                    style = ui.sizes.gameStatTextStyle,
                )
            }
        }
        Column(
            Modifier
                .layoutId(ui.ids.statsBarBox)
        ){
            shipType.value.info.statsIndicator.map.forEach {statIndication ->
                Row(
                    modifier = Modifier
                        .height(ui.sizes.statHeight)
                        .align(Alignment.End)
//                        .background(if (statIndication.key == ShipStatsIndicator.LIST.get(0)) Color.Blue else if (statIndication.key == ShipStatsIndicator.LIST.get(3)) Color.Blue else Color.Transparent)
//                    .background(Color.Cyan)
                ) {
                    (0 until statIndication.value).toList().forEach{
                        Box(
                            modifier = Modifier
                                .size(ui.sizes.statsBoxBarDp)
                                .padding(ui.sizes.statsBoxBarPadDp)
                                .background(Color.White)
                                .align(Alignment.CenterVertically)
                        ) { }
                    }
                }
            }
        }
        Column(
            Modifier
                .padding(ui.sizes.shipStatTextPadDp)
                .layoutId(ui.ids.statsTextBox)
        ){
            shipType.value.info.statsIndicator.map.forEach {
                Text(
                    modifier = Modifier .height(ui.sizes.statHeight) ,
                    text = it.key,
                    color = MyColor.applicationText,
                    style = ui.sizes.shipStatStyle,
                    softWrap = false,
                    maxLines = 1,
                    minLines= 1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}