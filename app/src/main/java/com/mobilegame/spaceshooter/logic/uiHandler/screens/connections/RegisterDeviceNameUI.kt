package com.mobilegame.spaceshooter.logic.uiHandler.screens.connections

import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.data.device.Device
import com.mobilegame.spaceshooter.logic.uiHandler.template.TemplateUI
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

class RegisterDeviceNameUI {
    val template = TemplateUI()
    val keyboard = Keyboard()

    class Keyboard {
        val size = SizesKeyboard()
        val firstLine = FirstLineKeyboard()
        val secondLine = SecondLineKeyboard()
        val thirdLine = ThirdLineKeyboard()
        val fourthLine = FourthLineKeyboard()
        val key = KeyKeyboard(size.lineHeight, firstLine.keys)

        data class SizesKeyboard(
            private val height: Float = Device.metrics.height * 0.5F,
            val heightDp: Dp = height.toDp(),
            val lineHeight: Float = height / 4F,
            val lineHeightDp: Dp = lineHeight.toDp(),
        )
        data class FirstLineKeyboard(
            val id: String = "first_line_keyboard",
            val keys: String = "QWERTYUIOP"
        )
        data class SecondLineKeyboard(
            val id: String = "second_line_keyboard",
            val keys: String = "ASDFGHJKL"
        )
        data class ThirdLineKeyboard(
            val id: String = "third_line_keyboard",
            val keys: String = "ZXCVNM"
        )
        data class FourthLineKeyboard(
            val id: String = "fourth_line_keyboard",
            val del: String = "DELETE",
            val space: String = "SPACE",
            val register: String = "REGISTER",
//            val keys: String = " "
        )
        class KeyKeyboard(lineHeight: Float, longestKeyRow: String) {
            private val height: Float = lineHeight * 0.9F
            val heightDp: Dp = height.toDp()

            private val startEndPaddingRatio = 0.06F
            private val startEndPadding = startEndPaddingRatio * Device.metrics.width
            val startEndPaddingDp = startEndPadding.toDp()
            private val betweenKeyPaddingRatio = 0.008F
            private val betweenKeyPadding = betweenKeyPaddingRatio * Device.metrics.width
            val betweenKeyPaddingDp = betweenKeyPadding.toDp()

            private val betweenActionKeyPaddingRatio = 0.05F
            private val betweenActionKeyPadding = betweenActionKeyPaddingRatio * Device.metrics.width
            val betweenActionKeyPaddingDp = betweenActionKeyPadding.toDp()
            private val width: Float = ((1F - (2 * startEndPaddingRatio) - ((longestKeyRow.length - 1) * betweenKeyPaddingRatio)) * Device.metrics.width) / (longestKeyRow.length)
            val widthDp: Dp = width.toDp()
            private val widthActionKey: Float = ((1F - (2 * startEndPaddingRatio) - (2 * betweenActionKeyPaddingRatio)) * Device.metrics.width) / (3F)
            val widthActionKeyDp: Dp = widthActionKey.toDp()

//            init {
//                displayDataUI?.let {
//                    wLog("RegisterDeviceNameUI::KeyKeyboard", "Key")
//                    vLog("RegisterDeviceNameUI::KeyKeyboard", "startEndPadd $startEndPadding")
//                    vLog("RegisterDeviceNameUI::KeyKeyboard", "startEndPaddDp $startEndPaddingDp")
//                    vLog("RegisterDeviceNameUI::KeyKeyboard", "betweeenKeyPadd $betweenKeyPadding")
//                    vLog("RegisterDeviceNameUI::KeyKeyboard", "betweeenKeyPaddDp $betweenKeyPadding")
//                    vLog("RegisterDeviceNameUI::KeyKeyboard", "widthActionKey $widthActionKey")
//                    vLog("RegisterDeviceNameUI::KeyKeyboard", "widthActionKeyDp $widthActionKeyDp")
//                }
//            }
        }
    }
}
