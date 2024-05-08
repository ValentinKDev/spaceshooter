package com.mobilegame.spaceshooter.presentation.ui.screens.shipMenuScreen

import ShipMenuViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.inGameScreen.elements.spaceShips.types.ShipView
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.ChargingButton
import com.mobilegame.spaceshooter.presentation.ui.template.AnimateSlide

@Composable
fun ShipMenuBody( vm: ShipMenuViewModel) {
    //todo : add animation that swipe the ship then arrows are hit
    //todo : add swipe instead of taping the arrow
    //todo : instruction hold to begin
    val ui = remember {vm.shipMenuUI.body }
    val list = remember { ShipType.getList() }
    val shipListIndex = remember { vm.shipPicking.shipListIndex }.collectAsState()
    val visible by remember { vm.animationSlide.visibleAnimation }.collectAsState()
//    val precedentType = remember {}

    ChargingButton(
        handler = vm.pressureHandler,
        sizeDp = ui.sizes.sizeWithBand,
        alphaAnimation = 0.2F,
    ) {
        val constraints = remember { ui.constraint }
        AnimateSlide(handler = vm.animationSlide, visibility = visible) {
            ConstraintLayout(constraints, Modifier.fillMaxSize() ) {
                Box( modifier = Modifier.layoutId(ui.ids.shipPresentation) ) {
                    ShipView(
                        type = ShipType.getFromList(if (visible) shipListIndex.value else vm.shipPicking.oldIndex),
                        shipViewSizeBox = vm.shipPicking.shipUI.shipViewBoxSize
                    )
                }
            }
        }
        AnimateSlide(handler = vm.animationSlide, visibility = !visible) {
            ConstraintLayout(constraints, Modifier.fillMaxSize() ) {
                Box( modifier = Modifier.layoutId(ui.ids.shipPresentation) ) {
                    ShipView(
                        type = ShipType.getFromList(if (!visible) shipListIndex.value else vm.shipPicking.oldIndex),
                        shipViewSizeBox = vm.shipPicking.shipUI.shipViewBoxSize
                    )
                }
            }
        }
        ConstraintLayout(constraints,
            Modifier
                .fillMaxSize()
        ) {
            Box (
                modifier = Modifier
                    .layoutId(ui.ids.leftArrow)
                    .clickable { vm.handleLeftArrowClick() }
                    .size(50.dp)
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
                                .border(
                                    width = ui.sizes.indicatorBoxBorderWidth,
                                    color = MyColor.applicationContrast
                                )
                        ) { }
                    }
                }
            }
        }
    }
}