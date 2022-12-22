package com.mobilegame.spaceshooter.domain.model.screen.uiHandler.tutos

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.Device
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.mainTemplate.MainTemplate
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

object DuelTutoScreenUI {
    var generalLayout = GeneralLayoutTutoScreen
    var smartphoneEmulator = SmartphoneEmulatorTutoScreen
    var template = MainTemplate
    private const val allWeightsHeight = (GeneralLayoutTutoScreen.paddVertical * 2) + (GeneralLayoutTutoScreen.smartphoneWeight * 2) + GeneralLayoutTutoScreen.paddMid

    object SmartphoneEmulatorTutoScreen {
        val ratios = RatiosSmartphoneEmulator
        val sizes = SizesSmartphoneEmulator
        val padd = PaddingSmartphoneEmulator
        val points = PointsSmartphoneEmulatorTutoScreen

        object RatiosSmartphoneEmulator {
            const val strokeHeightPercent = 0.005F
        }
        object SizesSmartphoneEmulator {
            var height = 0F
            var width = 0F
            var screenHeight = 0F
            var screenWidth = 0F
            var screenHeightDp = Dp.Unspecified
            var screenWidthDp = Dp.Unspecified
            var screenInner = Size.Zero
            var screenInnerDp = DpSize.Zero
            var border = 0F
            var borderDp = Dp.Unspecified
        }
        object PaddingSmartphoneEmulator {
            const val screenHorizontal = 0.1F
            const val screenVertical = 0.05F
        }
        object PointsSmartphoneEmulatorTutoScreen {
            var screenTopLeft = Offset.Zero
        }
    }

    object GeneralLayoutTutoScreen {
        const val paddVertical = 1F
        const val smartphoneWeight = 5F
        const val paddMid = 1.5F
        const val horizontalPadd = 0.27F
    }

    init {
        initSmartphoneEmulator()
    }

    private fun initSmartphoneEmulator() {
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border = Device.height * SmartphoneEmulatorTutoScreen.RatiosSmartphoneEmulator.strokeHeightPercent
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.borderDp = SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border.toDp()
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.height = (GeneralLayoutTutoScreen.smartphoneWeight / allWeightsHeight) * Device.height
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.width = (1F - (2F * GeneralLayoutTutoScreen.horizontalPadd)) * Device.width
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenWidth = SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.width * (1F - (2F * SmartphoneEmulatorTutoScreen.PaddingSmartphoneEmulator.screenHorizontal))+ (2F * SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border)
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenHeight = SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.height * (1F - (2F * SmartphoneEmulatorTutoScreen.PaddingSmartphoneEmulator.screenVertical)) + (2F * SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border)
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenWidthDp = SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenWidth.toDp()
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenHeightDp = SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenHeight.toDp()
        val sizeHeight = (SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenHeight - ( 2F * SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border))
        val sizeHeightDp = sizeHeight.toDp()
        val sizeWidth = (SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenWidth - ( 2F * SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border))
        val sizeWidthDp = sizeWidth.toDp()
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenInner = Size(sizeWidth, sizeHeight)
        SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenInnerDp = DpSize(height = sizeHeightDp, width = sizeWidthDp)
        SmartphoneEmulatorTutoScreen.PointsSmartphoneEmulatorTutoScreen.screenTopLeft = Offset(
            x = (SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.width - SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenWidth) / 2F,
            y = (SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.height - SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenHeight) ,
        )
        displayDataUI?.let {
            wLog("TutoScreenAdapter::initSmartphone", "smartphones")
            vLog("TutoScreenAdapter::initSmartphone", "height ${SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.height}")
            vLog("TutoScreenAdapter::initSmartphone", "width ${SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.width}")
            vLog("TutoScreenAdapter::initSmartphone", "screen height ${SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenHeight}")
            vLog("TutoScreenAdapter::initSmartphone", "screen width ${SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.screenWidth}")
            vLog("TutoScreenAdapter::initSmartphone", "border ${SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.border}")
            vLog("TutoScreenAdapter::initSmartphone", "borderDp ${SmartphoneEmulatorTutoScreen.SizesSmartphoneEmulator.borderDp}")
        }
    }

}