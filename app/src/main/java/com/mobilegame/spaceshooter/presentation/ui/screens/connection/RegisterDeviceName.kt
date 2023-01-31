package com.mobilegame.spaceshooter.presentation.ui.screens.connection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobilegame.spaceshooter.logic.model.screen.Screens
import com.mobilegame.spaceshooter.logic.model.screen.connection.registerDevice.RegisterDeviceViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.connection.elements.Keyboard.MyKeyboard
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable
import com.mobilegame.spaceshooter.presentation.ui.template.TemplateWithoutBand
import kotlinx.coroutines.delay

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

@Composable
fun MyTextField(vm: RegisterDeviceViewModel) {
    val visibleUnderscoreState by remember {vm.visibleUnderscoreState}.collectAsState()
    val input by remember { vm.input }.collectAsState()


    LaunchedEffect(visibleUnderscoreState.currentState) {
        delay(700)
        vm.setVisibleUnderscoreTargetTo(!visibleUnderscoreState.currentState)
    }

    val constraints = remember {
        ConstraintSet {
            val input = createRefFor("input")
            val underscore = createRefFor("underscore")

            constrain( input ) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
            constrain( underscore ) {
                start.linkTo(input.end)
                bottom.linkTo(input.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
        }
    }

    ConstraintLayout(constraints, Modifier.fillMaxSize()) {
        Box(Modifier.layoutId("input")) {
            Text(
                text = input,
                color = MyColor.applicationText,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(end = 3.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }
        Box(Modifier.layoutId("underscore")) {
            AnimatedVisibility(
                visibleState = visibleUnderscoreState ,
                enter = fadeIn(animationSpec = tween(100)),
                exit = fadeOut(animationSpec = tween(100))
            ) {
                Text(
                    text = "_",
                    color = MyColor.applicationText,
                    modifier = Modifier.wrapContentSize(),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                )
            }
        }
    }
    Column {
        CenterComposable(id = "filter") {
            Text(
                text = input,
                color = MyColor.applicationText,
                modifier = Modifier.background(MyColor.applicationBackground),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }
    }
}

