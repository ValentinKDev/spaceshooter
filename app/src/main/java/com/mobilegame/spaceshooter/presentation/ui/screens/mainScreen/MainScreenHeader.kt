package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.mobilegame.spaceshooter.logic.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.presentation.ui.navigation.Navigator
import com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.elements.buttons.TutoButton
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.*

@Composable
internal fun MainScreenHeader(navigator: Navigator, vm: MainScreenViewModel) {
    PaddingComposable(
        startPaddingRatio = vm.ui.template.header.padding.textPaddingStart,
        endPaddingRatio = vm.ui.template.header.padding.buttonPaddingEnd,
        topPaddingRatio = vm.ui.template.header.padding.paddingTop,
        bottomPaddingRatio = vm.ui.template.header.padding.paddingTop,
    ) {
        Box {
            AlignComposableToStart {
                CenterComposableVertically {
                    Text(
                        text = vm.ui.template.header.text.mainText,
                        color = vm.ui.template.header.colors.mainText,
                        style = TextStyle(
                            fontSize = vm.ui.template.header.sizes.mainTextHeightSp,
                            letterSpacing = vm.ui.template.header.sizes.mainTextSpaceSp,
                            fontWeight = FontWeight.Black
                        ),
                        textAlign = TextAlign.Justify,
                    )
                }
            }
            AlignComposableToEnd {
                Row {
                    CenterComposableVertically {
                        Text(
                            modifier = Modifier.padding(end = vm.ui.template.header.padding.spaceBetweenTextAndIconDp),
                            text = MainTemplate.header.text.iconText,
                            //todo : make the tuto text color shine a little bit
                            color = MainTemplate.header.colors.iconText,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = MainTemplate.header.sizes.iconTextSp,
                                letterSpacing = MainTemplate.header.sizes.iconTextSpaceSp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Default
                            )
                        )
                    }
                    CenterComposableVertically {
                        TutoButton(vm, navigator)
                    }
                }
            }
        }
    }
}