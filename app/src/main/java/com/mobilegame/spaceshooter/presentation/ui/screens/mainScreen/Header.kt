package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mobilegame.spaceshooter.domain.model.screen.bluetoothScreen.BluetoothScreenViewModel
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons.TutoButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*
import com.mobilegame.spaceshooter.utils.extensions.alpha

@Composable
internal fun Header(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        startPaddingRatio = vm.ui.header.ratios.textPaddingStart,
        endPaddingRatio = vm.ui.header.ratios.buttonPaddingEnd,
        topPaddingRatio = vm.ui.header.ratios.paddingTop,
        bottomPaddingRatio = vm.ui.header.ratios.paddingBottom,
    ) {
        Box {
            AlignComposableToStart {
                CenterComposableVertically {
                    Text(
                        text = vm.ui.header.text.mainText,
                        color = vm.ui.header.colors.mainText,
                        style = TextStyle(
                            fontSize = vm.ui.header.sizes.mainTextHeightSp,
                            letterSpacing = vm.ui.header.sizes.mainTextSpaceSp,
                            fontWeight = FontWeight.Black
                        ),
                        textAlign = TextAlign.Justify,
//                        modifier = Modifier.background(Color.Black.alpha(0.1F))
                    )
            }
            }
            AlignComposableToEnd {
                Row {
                    CenterComposableVertically {
                        Text(
                            modifier = Modifier.padding(end = vm.ui.header.padding.spaceBetweenTextAndIconDp),
                            text = vm.ui.header.text.iconText,
                            //todo : make the tuto text color shine a little bit
                            color = vm.ui.header.colors.iconText,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = vm.ui.header.sizes.iconTextSp,
                                letterSpacing = vm.ui.header.sizes.iconTextSpaceSp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Default
                            )
                        )
                    }
                    CenterComposableVertically {
                        TutoButton(vm)
                    }
                }
            }
        }
    }
}