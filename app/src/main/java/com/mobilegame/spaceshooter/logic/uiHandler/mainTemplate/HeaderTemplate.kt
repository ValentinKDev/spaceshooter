package com.mobilegame.spaceshooter.logic.uiHandler.mainTemplate

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSp
import com.mobilegame.spaceshooter.utils.extensions.toSpRef

class HeaderTemplate {
    val id = "HeaderTemplate"
    val ids = IdsHeaderTemplate()
    val sizes = SizesHeaderTemplate()
    val padding = PaddingHeaderTemplate()
    val colors = ColorsHeaderTemplate()
    val text = TextHeaderTemplate()
    val percent = PercentHeaderTemplate()

    data class IdsHeaderTemplate (
        val mainTextId: String = "main_text_header_template",
        val tutorialTextId: String = "tutorial_text_header_template",
        val tutorialButtonId: String = "tutorial_button_header_template",
    )
    data class PercentHeaderTemplate (
        val height: Float = ( MainTemplateUI.headerHeightWeight / MainTemplateUI.allWeights)
    )
    data class PaddingHeaderTemplate (
        val textPaddingStart: Float = 0.03F,
        val buttonPaddingEnd: Float = 0.03F,
        val paddingTop: Float = 0F,
        val paddingBottom: Float = 0F,
        private val spaceBetweenTextAndIcon: Float = Device.width * 0.01F,
        val spaceBetweenTextAndIconDp: Dp = spaceBetweenTextAndIcon.toDp(),
    )

    data class SizesHeaderTemplate(
        val height: Float = Device.height * ( MainTemplateUI.headerHeightWeight / MainTemplateUI.allWeights),
        private val iconTextSpace: Float = Device.width * 0.005F,
        private val iconText: Float = height * 0.38F,
        private val mainTextHeight: Float = height * 1F,
        private val mainTextSpace: Float = Device.width * 0.004F,
        val mainTextHeightSp: TextUnit = mainTextHeight.toSpRef(),
        val mainTextSpaceSp: TextUnit = mainTextSpace.toSp(),
        val heightDp: Dp = height.toDp(),
        val iconTextSpaceSp: TextUnit = iconTextSpace.toSp(),
        val iconTextSp: TextUnit = iconText.toSp(),
    )

    data class ColorsHeaderTemplate (
        val mainText: Color = MyColor.applicationText,
        val iconText: Color = MyColor.applicationContrastTransparent,
    )

    data class TextHeaderTemplate (
        val mainText: String = "WELCOME",
        val iconText: String = "Tutorial",
    )
}