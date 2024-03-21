package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu

import ShipMenuViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip.types.ShipStatsIndicator
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterType

@Composable
fun ShipMenuBand(vm: ShipMenuViewModel) {
    val ui = remember {vm.shipMenuUI.band}
    val shipType = remember { vm.shipType }.collectAsState()

    val constraints = remember {
        ConstraintSet {
            val shipName = createRefFor(ui.ids.shipName)
            val statsTextBox = createRefFor(ui.ids.statsTextBox)
            val statsBarBox = createRefFor(ui.ids.statsBarBox)

            constrain(shipName) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
                height = Dimension.wrapContent
                centerVerticallyTo(parent)
            }
            constrain(statsTextBox) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                height = Dimension.wrapContent
                width = Dimension.wrapContent
                centerVerticallyTo(parent)
            }
            constrain(statsBarBox) {
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
            text = shipType.value.name,
            color = MyColor.applicationText,
            style = ui.sizes.shipNameTextStyle,
        )
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