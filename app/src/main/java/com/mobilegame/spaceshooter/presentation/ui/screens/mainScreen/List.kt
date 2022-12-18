package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.BluetoothIcon
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*
import com.mobilegame.spaceshooter.utils.extensions.alpha

@Composable
internal fun List(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        horizontalPadding = 0.2F,
    ) {
        Box {
            AlignComposableToStart {
                CenterComposableVertically {
                    Box(Modifier.size(100.dp)) {
                        Icon(
                            modifier = Modifier
                                .size(100.dp)
                                .background(MyColor.blue2.alpha(0.05F)),
                            imageVector = Icons.Default.Bluetooth,
                            contentDescription = "bluetooth icon",
                            tint = MyColor.blue2.alpha(0.1F),
                        )
                        Canvas(
                            Modifier
                                .height(200.dp)
                                .width(100.dp)
                        ) {
                            drawRect(
                                color = MyColor.applicationText,
                                style = Stroke(width = 10F)
                            )
                        }
                    }
                }
            }
            AlignComposableToEnd {
                CenterComposableVertically {
                    BluetoothIcon(vm)
                }
            }
        }
    }
}
