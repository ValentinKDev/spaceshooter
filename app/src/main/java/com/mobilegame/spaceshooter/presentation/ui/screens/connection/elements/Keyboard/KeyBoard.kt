package com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.Keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable

@Composable
fun MyKeyboard(navigator: Navigator, vm: RegisterDeviceViewModel) {
    Column(Modifier.background(MyColor.Liver)) {
        Box(
            Modifier
                .weight(1F)
                .layoutId(vm.ui.keyboard.firstLine.id)
        ) {
            KeyboardRow(vm, vm.ui.keyboard.firstLine.id , vm.ui.keyboard.firstLine.keys)
        }
        Box(
            Modifier
                .weight(1F)
                .layoutId(vm.ui.keyboard.secondLine.id)
        ) {
            KeyboardRow(vm, vm.ui.keyboard.secondLine.id, vm.ui.keyboard.secondLine.keys)
        }
        Box(
            Modifier
                .weight(1F)
                .layoutId(vm.ui.keyboard.thirdLine.id)
        ) {
            KeyboardRow(vm, vm.ui.keyboard.thirdLine.id, vm.ui.keyboard.thirdLine.keys)
        }
        Box(
            Modifier
                .weight(1F)
                .layoutId(vm.ui.keyboard.fourthLine.id)
        ) {
            KeyBoardActionRow(vm, navigator)
        }
    }
}

@Composable
fun KeyboardRow(vm: RegisterDeviceViewModel, id: String, keys: String) {
    CenterComposable(id = id) {
        LazyRow {
            itemsIndexed(items = keys.toList()) { index, _ ->
                Key(vm, keys[index])
                KeySpace(vm.ui.keyboard.key)
            }
        }
    }
}

@Composable
fun KeyBoardActionRow(vm: RegisterDeviceViewModel, navigator: Navigator) {
    val constraints = remember {
        ConstraintSet {
            val deleteKey = createRefFor(ActionKeyType.DELETE.name)
            val spaceKey = createRefFor(ActionKeyType.SPACE.name)
            val registerKey = createRefFor(ActionKeyType.REGISTER.name)

            constrain( registerKey ) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(spaceKey.start)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
            constrain( spaceKey ) {
                top.linkTo(parent.top)
                start.linkTo(registerKey.end)
                end.linkTo(deleteKey.start)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
            constrain( deleteKey ) {
                top.linkTo(parent.top)
                start.linkTo(spaceKey.end)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        }
    }
    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Box(Modifier.layoutId(ActionKeyType.DELETE.name)) {
            ActionKey(vm = vm, actionKey = ActionKeyType.DELETE, navigator = navigator)
        }
        Box(Modifier.layoutId(ActionKeyType.SPACE.name)) {
            ActionKey(vm = vm, actionKey = ActionKeyType.SPACE, navigator = navigator)
        }
        Box(Modifier.layoutId(ActionKeyType.REGISTER.name)) {
            ActionKey(vm = vm, actionKey = ActionKeyType.REGISTER, navigator = navigator)
        }
    }
}

