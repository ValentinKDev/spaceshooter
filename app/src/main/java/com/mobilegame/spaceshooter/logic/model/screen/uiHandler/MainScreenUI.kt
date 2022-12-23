package com.mobilegame.spaceshooter.logic.model.screen.uiHandler

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.Icons.BluetoothIcon
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.Icons.WifiIcon
import com.mobilegame.spaceshooter.logic.model.screen.uiHandler.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.alpha
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSp

class MainScreenUI {
    val tutoButton = TutorialButton()
    val instruction = InstructionMainScreen()
    val buttonBluetooth: BluetoothIcon = BluetoothIcon(Device.height * 0.3F)
    val buttonWifi: WifiIcon = WifiIcon(Device.height * 0.3F, StrokeCap.Square, outlined = true)
    val template = MainTemplate

    class InstructionMainScreen {
        val padding = PaddingInstructionsMainScreen()
        val sizes = SizesInstructionsMainScreen(padding)
        val text = TextInstructionMainScreen()
        val color = ColorInstructionMainScreen()

        data class PaddingInstructionsMainScreen (
            val top: Float = 0.85F,
            val bottom: Float = 0.008F,
            val start: Float = 0.2F,
            val end: Float = 0.2F,
        )

        class SizesInstructionsMainScreen( padding: PaddingInstructionsMainScreen ) {
            val boxHeight: Float = (1F - padding.top - padding.bottom) * Device.height
            val text: Float = boxHeight * 0.45F
            val textSp: TextUnit = text.toSp()
        }
        data class ColorInstructionMainScreen (
            val targetColor: Color = MyColor.applicationContrastTransparent,
            val initialColor: Color = MyColor.applicationContrast.alpha(0.2F),
        )
        data class TextInstructionMainScreen (
            val message: String = "Hold the pressure",
        )
    }

    class TutorialButton {
        val sizes = SizesTutorialButton()
        val colors = ColorsTutorialButton()
        val text = TextTutorialButton()

        class SizesTutorialButton {
            private val iconButtonHeight: Float = MainTemplate.header.sizes.height * 0.75F
            private val iconQuestionMarkHeight: Float = MainTemplate.header.sizes.height * 0.55F
            val iconButtonHeightDp: Dp = iconButtonHeight.toDp()
            val iconQuestionMarkHeightDp: Dp = iconButtonHeight.toDp()
            val circleStrokeWidth: Float = MainTemplate.header.sizes.height * 0.07F
        }
        data class ColorsTutorialButton (
            val tint: Color = MyColor.applicationText,
        )
        data class TextTutorialButton (
            val description: String = "tutorial button",
        )
    }
}
