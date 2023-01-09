package com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.Keyboard

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.RegisterDeviceNameUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterVertically
import com.mobilegame.spaceshooter.utils.extensions.getKeyID

@Composable
fun ActionKey(vm: RegisterDeviceViewModel, actionKey: ActionKey) {
    Box(
        Modifier
            .height(vm.ui.keyboard.key.heightDp)
            .width(vm.ui.keyboard.key.widthActionKeyDp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(1.dp),
                color = MyColor.Platinium
            )
            .clickable {
                when (actionKey) {
                    ActionKey.DELETE -> vm.deleteAction()
                    ActionKey.SPACE -> vm.spaceAction()
                    ActionKey.REGISTER -> vm.registerAction()
                }
            }
    ) {
        ActionKeyText(text = actionKey.name)
    }
}

enum class ActionKey {
    DELETE, SPACE, REGISTER
}

@Composable
private fun ActionKeyText(text: String) {
    CenterComposable(id = text) {
        Text(
            text = text,
//                            textAlign = TextAlign.Center,
            modifier = Modifier
//                                .shadow(elevation = 15.dp)
//                                .padding(end = vm.ui.template.header.padding.spaceBetweenTextAndIconDp),
            ,
            color = MyColor.Platinium,
            style = TextStyle(
                fontSize = 25.sp,
//                                letterSpacing = MainTemplateUI.header.sizes.iconTextSpaceSp,
                fontWeight = FontWeight.ExtraBold,
//                                fontFamily = FontFamily.Default
            )
        )
    }
}


@Composable
fun Key(vm: RegisterDeviceViewModel, key: Char) {
    CenterVertically(key.getKeyID()) {
        Box(
            Modifier
                .height(vm.ui.keyboard.key.heightDp)
                .width(vm.ui.keyboard.key.widthDp)
                .clickable {
                    vm.addCharToInput(key)
                }
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(1.dp),
                    color = MyColor.Platinium
                )
        ) {
            CenterComposable(id = key.getKeyID()) {
                Text(
                    text = key.toString(),
//                            textAlign = TextAlign.Center,
                    modifier = Modifier
//                                .shadow(elevation = 15.dp)
//                                .padding(end = vm.ui.template.header.padding.spaceBetweenTextAndIconDp),
                    ,
                    color = MyColor.Platinium,
                    style = TextStyle(
                        fontSize = 25.sp,
//                                letterSpacing = MainTemplateUI.header.sizes.iconTextSpaceSp,
                        fontWeight = FontWeight.ExtraBold,
//                                fontFamily = FontFamily.Default
                    )
                )
            }
        }
    }
}

@Composable
fun KeySpace(ui: RegisterDeviceNameUI.Keyboard.KeyKeyboard) {
    Box(modifier = Modifier
        .fillMaxHeight()
        .width(ui.betweenKeyPaddingDp)
    )
}
