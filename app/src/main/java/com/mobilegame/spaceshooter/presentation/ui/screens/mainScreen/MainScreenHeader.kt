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
import com.mobilegame.spaceshooter.logic.model.navigation.Navigator
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
//                CenterComposableVertically {
                CenterComposableVertically(vm.ui.template.header.ids.mainTextId) {
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
//                    CenterComposableVertically {
//                    CenterComposableVertically(vm.ui.template.) {
                    CenterComposableVertically(vm.ui.template.header.ids.tutorialTextId) {
                        Text(
                            modifier = Modifier.padding(end = vm.ui.template.header.padding.spaceBetweenTextAndIconDp),
                            text = vm.ui.template.header.text.iconText,
                            //todo : make the tuto text color shine a little bit
                            color = vm.ui.template.header.colors.iconText,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = vm.ui.template.header.sizes.iconTextSp,
                                letterSpacing = vm.ui.template.header.sizes.iconTextSpaceSp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Default
                            )
                        )
                    }
//                    CenterComposableVertically {
                    CenterComposableVertically(vm.ui.template.header.ids.tutorialButtonId) {
                        TutoButton(vm, navigator)
                    }
                }
            }
        }
    }
}