package com.mobilegame.spaceshooter.logic.uiHandler

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BluetoothIcon
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.BtWifiIconsServiceUI
import com.mobilegame.spaceshooter.logic.uiHandler.Icons.WifiIconUI
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.extensions.alpha
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSp

class MainScreenUI {
    val template = TemplateUI()
//    val template = TemplateUI(instantNavBack = true)
    val tutoButton = TutorialButton(template)
    val instruction = InstructionMainScreen()
    val buttonBluetooth: BluetoothIcon = BluetoothIcon(Device.metrics.height * 0.3F)
    val buttonWifi: WifiIconUI = BtWifiIconsServiceUI.createWifiIconUI(Device.metrics.height * 0.3F, StrokeCap.Square, outlined = true) as WifiIconUI
//    val buttonWifi: WifiIcon = WifiIcon(Device.metrics.height * 0.3F, StrokeCap.Square, outlined = true

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

        class SizesInstructionsMainScreen( padding: PaddingInstructionsMainScreen) {
            val boxHeight: Float = (1F - padding.top - padding.bottom) * Device.metrics.height
            val text: Float = boxHeight * 0.45F
            val textSp: TextUnit = text.toSp()
        }
        data class ColorInstructionMainScreen (
            val targetColor: Color = MyColor.applicationContrastTransparent,
            val initialColor: Color = MyColor.applicationContrast.alpha(0.2F),
        )
        data class TextInstructionMainScreen (
            val principalMessage: String = "Hold the pressure",
            val warningBluetooth: String = "Bluetooth is not yet available",
            val warningTuto: String = "Tutorial is not yet available",
        )
    }

    class TutorialButton(val template: TemplateUI) {
        val id = "tutorial_Button"
        val sizes = SizesTutorialButton(template)
        val colors = ColorsTutorialButton()
        val text = TextTutorialButton()
        class SizesTutorialButton(template: TemplateUI) {
            private val iconButtonHeight: Float = template.header.sizes.height * 0.75F
//            private val iconQuestionMarkHeight: Float = template.header.sizes.height * 0.55F
            val iconButtonHeightDp: Dp = iconButtonHeight.toDp()
            val iconQuestionMarkHeightDp: Dp = iconButtonHeight.toDp()
            val circleStrokeWidth: Float = template.header.sizes.height * 0.07F
        }
        data class ColorsTutorialButton (
            val tint: Color = MyColor.applicationText,
        )
        data class TextTutorialButton (
            val description: String = "tutorial button",
        )
    }
}
