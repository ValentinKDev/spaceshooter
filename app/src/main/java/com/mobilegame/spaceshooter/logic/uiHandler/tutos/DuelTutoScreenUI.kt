package com.mobilegame.spaceshooter.logic.uiHandler.tutos

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.logic.uiHandler.Device
import com.mobilegame.spaceshooter.logic.uiHandler.template.MainTemplateUI
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpSize

class DuelTutoScreenUI() {
    var generalLayout = GeneralLayoutTutoScreen()
    var smartphoneEmulator = SmartphoneEmulatorTutoScreen(generalLayout)
    var template = MainTemplateUI

    class SmartphoneEmulatorTutoScreen(generalLayout: GeneralLayoutTutoScreen) {
        val idTop = "smartphone_emulator_top"
        val idBottom = "smartphone_emulator_bottom"
        val idSkinIn = "smartphone_skin_in"
        val idSkinOut = "smartphone_skin_out"
        val padd = PaddingSmartphoneEmulator()
        val sizes = SizesSmartphoneEmulator(generalLayout, padd)
        val points = PointsSmartphoneEmulatorTutoScreen(sizes, padd)

        class SizesSmartphoneEmulator(generalLayout: GeneralLayoutTutoScreen, padd: PaddingSmartphoneEmulator) {
            private val strokeHeightPercent: Float = 0.020F

            var height: Float = (generalLayout.smartphoneWeight / generalLayout.allWeightsHeight) * Device.height
            var width: Float = (1F - (2F * generalLayout.horizontalPadd)) * Device.width
            val paddVertical: Float = padd.screenVertical * height
            val paddHorizontal: Float = padd.screenHorizontal * width
            var screenHeight: Float = (height * (1F - (2F * padd.screenVertical)))
            var screenWidth: Float = (width * (1F - (2F * padd.screenHorizontal)))
            var border: Float = screenHeight * strokeHeightPercent
            var borderDp: Dp = border.toDp()
            var screenHeightDp: Dp = screenHeight.toDp()
            var screenWidthDp: Dp = screenWidth.toDp()
            private val sizeHeight = screenHeight - ( 2F * border)
            private val sizeWidth = screenWidth - ( 2F * border)
            var screenInner: Size = Size(sizeWidth, sizeHeight)
            var screenInnerDp: DpSize = screenInner.toDpSize()
        }
        data class PaddingSmartphoneEmulator (
            val screenHorizontal: Float = 0.1F,
            val screenVertical: Float = 0.05F,
        )
        class PointsSmartphoneEmulatorTutoScreen(sizes: SizesSmartphoneEmulator, padd: PaddingSmartphoneEmulator) {
            val screenTopLeft: Offset = Offset(
                x = sizes.width - sizes.screenInner.width - sizes.paddHorizontal - sizes.border,
                y = sizes.height - sizes.screenHeight - sizes.paddVertical + sizes.border,
            )
        }
    }

    data class GeneralLayoutTutoScreen (
        val paddVertical: Float = 1F,
        val smartphoneWeight: Float = 5F,
        val paddMid: Float = 1.5F,
        val horizontalPadd: Float = 0.30F,
        val allWeightsHeight: Float = (paddVertical * 2F) + (smartphoneWeight * 2F) + paddMid,
    )

    init {
        displayDataUI?.let {
            wLog("TutoScreenAdapter::init", "smartphones")
            vLog("TutoScreenAdapter::init", "height ${this.smartphoneEmulator.sizes.height}")
            vLog("TutoScreenAdapter::init", "width ${this.smartphoneEmulator.sizes.width}")
            vLog("TutoScreenAdapter::init", "screen height ${this.smartphoneEmulator.sizes.screenHeight}")
            vLog("TutoScreenAdapter::init", "screen width ${this.smartphoneEmulator.sizes.screenWidth}")
            vLog("TutoScreenAdapter::init", "border ${this.smartphoneEmulator.sizes.border}")
            vLog("TutoScreenAdapter::init", "borderDp ${this.smartphoneEmulator.sizes.borderDp}")
            vLog("TutoScreenAdapter::init", "paddvertical ${this.smartphoneEmulator.sizes.paddVertical}")
        }
    }
}