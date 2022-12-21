package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

object TutoScreenAdapter {
    lateinit var backButton: BackButtonAdapter
    var backButtonLayer = BackButtonLayerTutoScreen
    var generalLayout = GeneralLayoutTutoScreen
    var smartphoneEmulator = SmartphoneEmulatorTutoScreen

    object SmartphoneEmulatorTutoScreen {
        val ratios = RatiosSmartphoneEmulator
        val sizes = SizesSmartphoneEmulator
        val padd = PaddingSmartphoneEmulator
        val points = PointsSmarthponeEmulatorTutoScreen

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
//            var screenInnerHeightDp = Dp.Unspecified
//            var screenInnerWidthDp = Dp.Unspecified
            var border = 0F
            var borderDp = Dp.Unspecified
        }
        object PaddingSmartphoneEmulator {
            const val screenHorizontal = 0.1F
            const val screenVertical = 0.05F
        }
        object PointsSmarthponeEmulatorTutoScreen {
            var smartphoneTopLeft = Offset.Zero
            var screenTopLeft = Offset.Zero
        }
    }
    object GeneralLayoutTutoScreen {
        const val paddVertical = 1F
        const val smartphoneWeight = 5F
        const val paddMid = 1.5F
        const val horizontalPadd = 0.27F
    }
    object BackButtonLayerTutoScreen {
        var buttonHeightWeight = 1F
        var emptyHeightWeight = 5F
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    private fun initSmartphoneEmulator() {
        smartphoneEmulator.sizes.border = heightFull * smartphoneEmulator.ratios.strokeHeightPercent
        smartphoneEmulator.sizes.borderDp = smartphoneEmulator.sizes.border.toDp(density)
        smartphoneEmulator.sizes.height = (generalLayout.smartphoneWeight / allWeightsHeight) * heightFull
        smartphoneEmulator.sizes.width = (1F - (2F * generalLayout.horizontalPadd)) * widthFull
        smartphoneEmulator.sizes.screenWidth = smartphoneEmulator.sizes.width * (1F - (2F * smartphoneEmulator.padd.screenHorizontal))+ (2F * smartphoneEmulator.sizes.border)
//        smartphoneEmulator.sizes.screenHeight = smartphoneEmulator.sizes.height * (1F - (2F * smartphoneEmulator.padd.screenVertical))
        smartphoneEmulator.sizes.screenHeight = smartphoneEmulator.sizes.height * (1F - (2F * smartphoneEmulator.padd.screenVertical)) + (2F * smartphoneEmulator.sizes.border)
        smartphoneEmulator.sizes.screenWidthDp = smartphoneEmulator.sizes.screenWidth.toDp(density)
        smartphoneEmulator.sizes.screenHeightDp = smartphoneEmulator.sizes.screenHeight.toDp(density)
        val sizeHeight = (smartphoneEmulator.sizes.screenHeight - ( 2F * smartphoneEmulator.sizes.border))
        val sizeHeightDp = sizeHeight.toDp(density)
        val sizeWidth = (smartphoneEmulator.sizes.screenWidth - ( 2F * smartphoneEmulator.sizes.border))
        val sizeWidthDp = sizeWidth.toDp(density)
        smartphoneEmulator.sizes.screenInner = Size(sizeWidth, sizeHeight)
        smartphoneEmulator.sizes.screenInnerDp = DpSize(height = sizeHeightDp, width = sizeWidthDp)
        smartphoneEmulator.points.screenTopLeft = Offset(
            x = (smartphoneEmulator.sizes.width - smartphoneEmulator.sizes.screenWidth) / 2F,
            y = (smartphoneEmulator.sizes.height - smartphoneEmulator.sizes.screenHeight) ,
        )
        displayDataUI?.let {
            wLog("TutoScreenAdapter::initSmartphone", "smartphones")
            vLog("TutoScreenAdapter::initSmartphone", "height ${smartphoneEmulator.sizes.height}")
            vLog("TutoScreenAdapter::initSmartphone", "width ${smartphoneEmulator.sizes.width}")
            vLog("TutoScreenAdapter::initSmartphone", "screen height ${smartphoneEmulator.sizes.screenHeight}")
            vLog("TutoScreenAdapter::initSmartphone", "screen width ${smartphoneEmulator.sizes.screenWidth}")
            vLog("TutoScreenAdapter::initSmartphone", "border ${smartphoneEmulator.sizes.border}")
            vLog("TutoScreenAdapter::initSmartphone", "borderDp ${smartphoneEmulator.sizes.borderDp}")
        }
    }
    private fun initBackButton(context: Context) {
        val boxHeight = heightFull * (backButtonLayer.buttonHeightWeight / (backButtonLayer.buttonHeightWeight + backButtonLayer.emptyHeightWeight))
        backButton = BackButtonAdapter.create(context, boxHeight)
    }

    fun create(context: Context): TutoScreenAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        allWeightsHeight = (generalLayout.paddVertical * 2) + (generalLayout.smartphoneWeight * 2) + generalLayout.paddMid

        displayDataUI?.let {
            wLog("TutoScreenAdapter::create", "start")
            vLog("TutoScreenAdapter::create", "widthFull = $widthFull")
            vLog("TutoScreenAdapter::create", "heightFull = $heightFull")
            vLog("TutoScreenAdapter::create", "density = $density")
            vLog("TutoScreenAdapter::create", "weight height = $allWeightsHeight")
        }

        initBackButton(context)
        initSmartphoneEmulator()
        return this
    }

}