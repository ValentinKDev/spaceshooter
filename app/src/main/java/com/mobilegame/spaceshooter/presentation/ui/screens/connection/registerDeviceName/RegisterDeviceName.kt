package com.mobilegame.spaceshooter.presentation.ui.screens.connection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.Keyboard.MyKeyboard
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.registerDeviceName.MyTextField
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand

@Composable
fun RegisterDeviceName(navigator: Navigator, vm: RegisterDeviceViewModel = viewModel()) {
    TemplateWithoutBand(
        navigator = navigator,
        backNav = Screens.MainScreen,
        ui = vm.ui.template,
        header = {
            CenterComposable(id = "instruction") {
                Text(
                    text = "ENTER YOUR NAME :",
                    color = MyColor.applicationText,
                    modifier = Modifier.wrapContentSize(),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                )
            }
        },
    ) {
        Column() {
            Box(Modifier.weight(1F)) {
                MyTextField(vm)
            }
            Box(Modifier.weight(1F)) {
                MyKeyboard(navigator, vm)
            }
        }
    }
}