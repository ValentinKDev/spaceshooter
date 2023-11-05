package com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.Keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.screens.connections.RegisterDeviceNameUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterVertically
import com.mobilegame.spaceshooter.utils.extensions.getKeyID

@Composable
fun ActionKey(
    vm: RegisterDeviceViewModel,
    navigator: Navigator,
    actionKey: ActionKeyType,
) {
    val textInput by remember { vm.input }.collectAsState()
    var enable = remember { true }
    val context = remember {LocalContext}.current

    val backgroundColor: Modifier = when (actionKey) {
        ActionKeyType.DELETE -> {
            enable = vm.isDeleteActionEnable(textInput)
            if (enable) Modifier
            else Modifier.background(Color.DarkGray)
        }
        ActionKeyType.REGISTER -> {
            enable = vm.isRegisteringActionEnable(textInput)
            if (enable) Modifier
            else Modifier.background(Color.DarkGray)
        }
        else -> Modifier
    }

    Box(
        Modifier
            .height(vm.ui.keyboard.key.heightDp)
            .width(vm.ui.keyboard.key.widthActionKeyDp)
            .then(backgroundColor)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(1.dp),
                color = MyColor.Platinium
            )
            .clickable(enabled = enable) {
                when (actionKey) {
                    ActionKeyType.DELETE -> vm.deleteAction()
                    ActionKeyType.SPACE -> vm.spaceAction()
                    ActionKeyType.REGISTER -> vm.registerAction(navigator, context)
                }
            }
    ) {
        ActionKeyText(text = actionKey.name)
    }
}

enum class ActionKeyType {
    DELETE, SPACE, REGISTER
}

@Composable
private fun ActionKeyText(text: String) {
    CenterComposable(id = text) {
        Text(
            text = text,
            color = MyColor.Platinium,
            style = TextStyle(
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
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
                    modifier = Modifier
                    ,
                    color = MyColor.Platinium,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
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
