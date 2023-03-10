package com.mobilegame.spaceshooter.logic.uiHandler.template

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSp
import com.mobilegame.spaceshooter.utils.extensions.toSpRef

class HeaderTemplate(percent: TemplateUI.TemplatePercents) {
    val id = "HeaderTemplate"
    val ids = IdsHeaderTemplate()
    val sizes = SizesHeaderTemplate(percent)
    val padding = PaddingHeaderTemplate(sizes)
    val colors = ColorsHeaderTemplate()
    val text = TextHeaderTemplate()

    data class IdsHeaderTemplate (
        val mainTextId: String = "main_text_header_template",
        val tutorialTextId: String = "tutorial_text_header_template",
        val tutorialButtonId: String = "tutorial_button_header_template",
    )
    class PaddingHeaderTemplate(sizes: SizesHeaderTemplate) {
        val textPaddingStart: Float = (sizes.height / Device.metrics.width) + 0.04F
        val buttonPaddingEnd: Float = 0.03F
        val paddingTop: Float = 0F
        val paddingBottom: Float = 0F
        private val spaceBetweenTextAndIcon: Float = Device.metrics.width * 0.01F
        val spaceBetweenTextAndIconDp: Dp = spaceBetweenTextAndIcon.toDp()
    }

    class SizesHeaderTemplate(percent: TemplateUI.TemplatePercents) {
        val height: Float = percent.header * Device.metrics.height
        private val iconTextSpace: Float = Device.metrics.width * 0.005F
        private val iconText: Float = height * 0.38F
        private val mainTextHeight: Float = height * 1F
        private val mainTextSpace: Float = Device.metrics.width * 0.004F
        val mainTextHeightSp: TextUnit = mainTextHeight.toSpRef()
        val mainTextSpaceSp: TextUnit = mainTextSpace.toSp()
        val heightDp: Dp = height.toDp()
        val iconTextSpaceSp: TextUnit = iconTextSpace.toSp()
        val iconTextSp: TextUnit = iconText.toSp()
    }

    data class ColorsHeaderTemplate (
        val mainText: Color = MyColor.applicationText,
        val iconText: Color = MyColor.applicationContrastTransparent,
    )

    data class TextHeaderTemplate (
        val mainText: String = "WELCOME",
        val iconText: String = "Tutorial",
    )
}