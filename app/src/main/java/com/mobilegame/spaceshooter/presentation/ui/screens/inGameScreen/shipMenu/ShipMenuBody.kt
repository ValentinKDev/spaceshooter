package com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.shipMenu

import ShipMenuViewModel
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen.LaunchDuelGameViewModel
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.ShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton

@Composable
//fun ShipMenuBody( vm: LaunchDuelGameViewModel) {
fun ShipMenuBody( vm: ShipMenuViewModel) {
    //todo : add animation that swipe the ship then arrows are hit
    //todo : add swipe instead of taping the arrow
//    val ui = remember {vm.shipMenuVM.shipMenuUI.body }
    val ui = remember {vm.shipMenuUI.body }
    val list = remember { ShipType.getList() }
//    val shipListIndex = remember { vm.shipMenuVM.shipListIndex }.collectAsState()
    val shipListIndex = remember { vm.shipListIndex }.collectAsState()

    ChargingButton(
//        handler = vm.shipMenuVM.pressureVM,
        handler = vm.pressureVM,
        sizeDp = ui.sizes.sizeWithBand,
        alphaAnimation = 0.2F,
    ) {
        val constraints = remember {
            ConstraintSet {
                val leftArrow = createRefFor(ui.ids.leftArrow)
                val rightArrow = createRefFor(ui.ids.rightArrow)
                val shipListIndicator = createRefFor(ui.ids.shipNumberListIndicator)
                val spaceShipPresentation = createRefFor(ui.ids.shipPresentation)

                constrain(leftArrow) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                    centerVerticallyTo(parent)
                }
                constrain(rightArrow) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                    centerVerticallyTo(parent)
                }
                constrain(spaceShipPresentation) {
                    top.linkTo(parent.top)
                    start.linkTo(leftArrow.end)
                    end.linkTo(rightArrow.start)
//                    bottom.linkTo(shipListIndicator.top)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                    centerHorizontallyTo(parent)
                }
                constrain(shipListIndicator) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                    centerHorizontallyTo(parent)
                }
            }
        }
        ConstraintLayout(constraints,
            Modifier
                .fillMaxSize()
        ) {
            Box (
                modifier = Modifier
                    .layoutId(ui.ids.shipPresentation)
            ){
//                ShipView(type = ShipType.getFromList(shipListIndex.value), shipViewSizeBox = vm.shipMenuVM.shipUI.shipViewBoxSize)
                ShipView(type = ShipType.getFromList(shipListIndex.value), shipViewSizeBox = vm.shipUI.shipViewBoxSize)
            }
            Box (
                modifier = Modifier
                    .layoutId(ui.ids.leftArrow)
//                    .clickable { vm.shipMenuVM.handleLeftArrowClick() }
                    .clickable { vm.handleLeftArrowClick() }
                    .size(50.dp)
//                    .background(Color.Red)
            ){
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "KeyboardArrowLeft",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    tint = MyColor.applicationContrast
                )
            }
            Box (
                modifier = Modifier
                    .layoutId(ui.ids.rightArrow)
//                    .clickable { vm.shipMenuVM.handleRightArrowClick() }
                    .clickable { vm.handleRightArrowClick() }
                    .size(50.dp)
//                    .background(Color.Red)
            ){
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "KeyboardArrowRight",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    tint = MyColor.applicationContrast

                )
            }
            Box (
                modifier = Modifier
                    .layoutId(ui.ids.shipNumberListIndicator)
                    .wrapContentSize()
//                    .alpha(0.5F)
            ){
                Row {
                    list.forEachIndexed() { i, _ ->
                        Box(
                            modifier = Modifier
                                .size(ui.sizes.indicatorBoxDp)
                                .padding(ui.sizes.indicatorBoxPadDp)
                                .background(
                                    if (i == shipListIndex.value) MyColor.applicationContrast
                                    else Color.Transparent
                                )
//                                .border(width = ui.sizes.indicatorBoxBorderWidth, brush = Brush.linearGradient(), shape = Icons.Rounded)
                                .border(
                                    width = ui.sizes.indicatorBoxBorderWidth,
                                    color = MyColor.applicationContrast
                                )
                        ) { }
                    }
                }
            }
        }
//        Box(modifier = Modifier
//            .size(ui.sizes.sizeWithBand)
//            .background(Color.Cyan))
    }
}