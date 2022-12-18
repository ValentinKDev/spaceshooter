package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.alpha
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSp

object MainScreenObj {
    val header = HeaderMainScreen
    val delimiter = DelimiterMainScreen
    val list = ListMainScreen
    val tutorialButton = TutorialButton
    val buttonWifi = ButtonWifiMainScreen
    val buttonBluetooth = ButtonBluetooth

    object HeaderMainScreen {
        val ratios = RatiosHeaderMainScreen
        val sizes = SizesHeaderMainScreen
        val padding = PaddingHeaderMainScreen
        val colors = ColorsHeaderMainScreen
        val text = TextHeaderMainScreen

        object RatiosHeaderMainScreen {
            const val heightWeight = 1F

            const val textPaddingStart = 0.03F
            const val buttonPaddingEnd = 0.03F
            const val paddingTop = 0F
            const val paddingBottom = 0F
            const val spaceBetweenTextAndIconPercent = 0.01F

            const val iconTextHeightPercent = 0.38F
            const val iconTextSpacePercent = 0.005F

            const val mainTextHeightPercent = 0.98F
            const val mainTextSpacePercent = 0.004F
        }

        object SizesHeaderMainScreen {
            var height = 0F
            var heightDp = Dp.Unspecified
            var iconTextSpace = 0F
            var iconTextSpaceSp = 0.sp
            var iconText = 0F
            var iconTextSp = 0.sp
            var mainTextHeight = 0F
            var mainTextHeightSp = 0.sp
            var mainTextSpace = 0F
            var mainTextSpaceSp = 0.sp
        }

        object PaddingHeaderMainScreen {
            var spaceBetweenTextAndIcon = 0F
            var spaceBetweenTextAndIconDp = Dp.Unspecified
        }

        object ColorsHeaderMainScreen {
            val mainText = MyColor.applicationText
            val iconText = MyColor.applicationText.alpha(0.8F)
        }

        object TextHeaderMainScreen {
            const val mainText = "WELCOME"
            const val iconText = "Tutorial"
        }
    }

    object TutorialButton {
        val ratios = RatiosTutorialButton
        val sizes = SizesTutorialButton
        val colors = ColorsTutorialButton
        val text = TextTutorialButton

        object RatiosTutorialButton {
            const val iconButtonHeightPercentage = 0.75F
            const val questionMarkHeightPercentage = 0.55F
            const val strokeHeightPercentage = 0.07F
        }
        object SizesTutorialButton {
            var iconButtonHeight = 0F
            var iconButtonHeightDp = Dp.Unspecified
            var iconQuestionMarkHeight = 0F
            var iconQuestionMarkHeightDp = Dp.Unspecified
            var circleStrokeWidth = 0F
        }
        object ColorsTutorialButton {
            val tint = MyColor.applicationText
        }
        object TextTutorialButton {
            val description = "tutorial button"
        }
    }

    object DelimiterMainScreen {
        val ratios = RatiosDelimiterMainScreen
        val sizes = SizesDelimiterMainScreen
        val colors = ColorsDelimiterMainScreen

        object RatiosDelimiterMainScreen {
            const val heightWeight = 0.06F
        }
        object SizesDelimiterMainScreen {
            var heightDp = Dp.Unspecified
        }
        object ColorsDelimiterMainScreen {
            val background = MyColor.applicationText
        }
    }

    object ListMainScreen {
        val ratios = RatioListMainScreen
        val sizes = SizesListMainScreen

        object RatioListMainScreen {
            const val heightWeight = 4.94F
        }
        object SizesListMainScreen {
            var heightDp = Dp.Unspecified
        }
    }

    object ButtonBluetooth {
        val ratios = RatiosButtonBluetooth
        val sizes = SizesButtonBluetooth
        val points = PointsButtonBluetooth
        val color = ColorButtonBluetooth

        object RatiosButtonBluetooth {
            const val squareHeightPercent = 0.30F
            const val squareStrokePercent = 0.01F
            const val canvasHeightPercent = 0.8F
            const val iconSmallStrokeHeightPercent = 0.015F
            const val iconBigStrokeHeightPercent = 0.030F
        }
        object SizesButtonBluetooth {
            var squareHeight = 0F
            var squareHeightDp = Dp.Unspecified
            var squareStrokeDp = Dp.Unspecified
            var canvas = 0F
            var canvasDp = Dp.Unspecified
            var iconSmallStroke = 0F
            var iconBigStroke = 0F
        }
        object PointsButtonBluetooth {
            var p1: Offset = Offset.Unspecified
            var p2 = Offset.Unspecified
            var p3 = Offset.Unspecified
            var p4 = Offset.Unspecified
            var p5 = Offset.Unspecified
            var p6 = Offset.Unspecified
        }
        object ColorButtonBluetooth {
            val stroke = MyColor.applicationText
        }
    }

    object ButtonWifiMainScreen {
        val ratios = RatiosButtonWifi
        val sizes = SizesButtonWifi
        val circles = CircleButtonWifi
        val color = ColorButtonWifi
        val stroke = StrokeButtonWifi

        object RatiosButtonWifi {
            const val squareHeightPercent = 0.30F
            const val squareStrokePercent = 0.01F
            const val canvasHeightPercent = 1F
            const val p1strokeHeightPercent = 0.007F
            const val bigStrokeHeightPercent = 0.025F
            const val smallStrokeHeightPercent = 0.011F
        }
        object SizesButtonWifi {
            var squareHeight = 0F
            var squareHeightDp = Dp.Unspecified
            var canvas = 0F
            var canvasDp = Dp.Unspecified
        }
        object StrokeButtonWifi {
            var squareStrokeDp = Dp.Unspecified
            var p1stroke = 0F
            var bigStroke = 0F
            var smallStroke = 0F
        }
        object CircleButtonWifi {
            var p1Radius = 0F
            var p1CanvasSize = 0F
            var p1CanvasSizeDp = Dp.Unspecified
            var p2CanvasSizeDp = Dp.Unspecified
            var p3CanvasSizeDp = Dp.Unspecified
            var p4CanvasSizeDp = Dp.Unspecified
        }
        object ColorButtonWifi {
            var icon = MyColor.applicationContrast
            var iconInner = MyColor.applicationBackground
        }
    }

    private fun initWifiButton() {
        buttonWifi.sizes.squareHeight = heightFull * buttonWifi.ratios.squareHeightPercent
        buttonWifi.sizes.squareHeightDp = buttonWifi.sizes.squareHeight.toDp(density)
        buttonWifi.stroke.squareStrokeDp = (heightFull * buttonWifi.ratios.squareStrokePercent).toDp(density)
        buttonWifi.sizes.canvas = buttonWifi.sizes.squareHeight * buttonWifi.ratios.canvasHeightPercent
        buttonWifi.sizes.canvasDp = buttonWifi.sizes.canvas.toDp(density)

        buttonWifi.circles.p1CanvasSize = buttonWifi.sizes.canvas * 0.2F
        buttonWifi.circles.p1CanvasSizeDp = buttonWifi.circles.p1CanvasSize.toDp(density)
        buttonWifi.circles.p1Radius = buttonWifi.circles.p1CanvasSize / 3F
        buttonWifi.circles.p2CanvasSizeDp = buttonWifi.sizes.canvasDp * 0.40F
        buttonWifi.circles.p3CanvasSizeDp = buttonWifi.sizes.canvasDp * 0.70F
        buttonWifi.circles.p4CanvasSizeDp = buttonWifi.sizes.canvasDp * 1F

        buttonWifi.stroke.p1stroke = buttonWifi.ratios.p1strokeHeightPercent * heightFull
        buttonWifi.stroke.bigStroke = buttonWifi.ratios.bigStrokeHeightPercent * heightFull
        buttonWifi.stroke.smallStroke = buttonWifi.ratios.smallStrokeHeightPercent * heightFull

        displayDataUI?.let {
            wLog("MainScreenObj::initWifiButton", "start")
            vLog("MainScreenObj::initWifiButton", "squareHeight ${buttonWifi.sizes.squareHeight}")
            vLog("MainScreenObj::initWifiButton", "squareHeightDp ${buttonWifi.sizes.squareHeightDp}")
            vLog("MainScreenObj::initWifiButton", "squareStrokeDp ${buttonWifi.stroke.squareStrokeDp}")
            vLog("MainScreenObj::initWifiButton", "canvas ${buttonWifi.sizes.canvas}")
            vLog("MainScreenObj::initWifiButton", "canvasDp ${buttonWifi.sizes.canvasDp}")
            vLog("MainScreenObj::initWifiButton", "p1Stroke ${2 * density}")
            vLog("MainScreenObj::initWifiButton", "p1Stroke ${buttonWifi.stroke.p1stroke}")
            vLog("MainScreenObj::initWifiButton", "bigStroke ${8 * density}")
            vLog("MainScreenObj::initWifiButton", "bigStroke ${buttonWifi.stroke.bigStroke}")
            vLog("MainScreenObj::initWifiButton", "smallStroke ${3.5 * density}")
            vLog("MainScreenObj::initWifiButton", "smallStroke ${buttonWifi.stroke.smallStroke}")
        }
    }

    private fun initBluetoothButton() {
        buttonBluetooth.sizes.squareHeight = heightFull * buttonBluetooth.ratios.squareHeightPercent
        buttonBluetooth.sizes.squareHeightDp = (buttonBluetooth.sizes.squareHeight).toDp(density)
        buttonBluetooth.sizes.squareStrokeDp = (heightFull * buttonBluetooth.ratios.squareStrokePercent).toDp( density)
        buttonBluetooth.sizes.canvas = (buttonBluetooth.sizes.squareHeight * buttonBluetooth.ratios.canvasHeightPercent)
        buttonBluetooth.sizes.canvasDp = buttonBluetooth.sizes.canvas.toDp(density)

        val size = buttonBluetooth.sizes.canvas

        val oneFourthWidth = size * (1F/ 4F)
        val twoFourthWidth = size * (2F/ 4F)
        val threeFourthWidth = size * (3F/ 4F)

        val zeroFourthHeight = size * 0.10F
        val oneFourthHeight = zeroFourthHeight + size * 0.80F * (1F/ 4F)
        val threeFourthHeight = zeroFourthHeight + size * 0.80F * (3F/ 4F)
        val fourthFourthHeight = zeroFourthHeight + size * 0.80F

        buttonBluetooth.points.p1 = Offset(x = twoFourthWidth, y = zeroFourthHeight)
        buttonBluetooth.points.p2 = Offset(x = oneFourthWidth, y = oneFourthHeight)
        buttonBluetooth.points.p3 = Offset(x = threeFourthWidth, y = oneFourthHeight)
        buttonBluetooth.points.p4 = Offset(x = oneFourthWidth, y = threeFourthHeight)
        buttonBluetooth.points.p5 = Offset(x = threeFourthWidth, y = threeFourthHeight)
        buttonBluetooth.points.p6 = Offset(x = twoFourthWidth, y = fourthFourthHeight)

        buttonBluetooth.sizes.iconSmallStroke = heightFull * buttonBluetooth.ratios.iconSmallStrokeHeightPercent
        buttonBluetooth.sizes.iconBigStroke = heightFull * buttonBluetooth.ratios.iconBigStrokeHeightPercent

        displayDataUI?.let {
            wLog("MainScreenObj::initBluetoothButton", "start")
            vLog("MainScreenObj::initBluetoothButton", "squareHeight ${buttonBluetooth.sizes.squareHeight}")
            vLog("MainScreenObj::initBluetoothButton", "squareHeightDp ${buttonBluetooth.sizes.squareHeightDp}")
            vLog("MainScreenObj::initBluetoothButton", "squareStrokeDp ${buttonBluetooth.sizes.squareStrokeDp}")
            vLog("MainScreenObj::initBluetoothButton", "canvasHeight ${buttonBluetooth.sizes.canvas}")
            vLog("MainScreenObj::initBluetoothButton", "canvasHeightDp ${buttonBluetooth.sizes.canvasDp}")
            vLog("MainScreenObj::initBluetoothButton", "iconSmallStrokeDp ${buttonBluetooth.sizes.iconSmallStroke}")
            vLog("MainScreenObj::initBluetoothButton", "iconBigStrokeDp ${buttonBluetooth.sizes.iconBigStroke}")
        }
    }


    private fun initTutoButton() {
        tutorialButton.sizes.iconButtonHeight = tutorialButton.ratios.iconButtonHeightPercentage * header.sizes.height
        tutorialButton.sizes.iconButtonHeightDp = tutorialButton.sizes.iconButtonHeight.toDp(density)
        tutorialButton.sizes.iconQuestionMarkHeight = tutorialButton.ratios.questionMarkHeightPercentage * header.sizes.height
        tutorialButton.sizes.iconQuestionMarkHeightDp = tutorialButton.sizes.iconQuestionMarkHeight.toDp(density)
        tutorialButton.sizes.circleStrokeWidth = tutorialButton.ratios.strokeHeightPercentage * header.sizes.height

        displayDataUI?.let {
            wLog("MainScreenObj::initTutoButton", "start")
            vLog("MainScreenObj::initTutoButton", "iconButtonHeight ${tutorialButton.sizes.iconButtonHeight}")
            vLog("MainScreenObj::initTutoButton", "iconButtonHeightDp ${tutorialButton.sizes.iconButtonHeightDp}")
            vLog("MainScreenObj::initTutoButton", "iconQuestionMarkHeight ${tutorialButton.sizes.iconQuestionMarkHeight}")
            vLog("MainScreenObj::initTutoButton", "iconQuestionMarkHeightDp ${tutorialButton.sizes.iconQuestionMarkHeightDp}")
            vLog("MainScreenObj::initTutoButton", "circleStrokeWidth ${tutorialButton.sizes.circleStrokeWidth}")
        }
    }
    private fun initList() {
        list.sizes.heightDp = (heightFull * (list.ratios.heightWeight / allWeightsHeight)).toDp( density)

        displayDataUI?.let {
            wLog("MainScreenObj::iniList", "start")
            vLog("MainScreenObj::iniList", "heightDp ${list.sizes.heightDp}")
        }
    }
    private fun initDelimiter() {
        delimiter.sizes.heightDp = (heightFull * (delimiter.ratios.heightWeight / allWeightsHeight)).toDp( density)

        displayDataUI?.let {
            wLog("MainScreenObj::initDelimiter", "start")
            vLog("MainScreenObj::initDelimiter", "heightDp ${delimiter.sizes.heightDp}")
        }
    }

    private fun initHeader() {
        header.sizes.height = heightFull * (header.ratios.heightWeight / allWeightsHeight)
        header.sizes.heightDp = header.sizes.height.toDp(density)
        header.padding.spaceBetweenTextAndIcon = widthFull * header.ratios.spaceBetweenTextAndIconPercent
        header.padding.spaceBetweenTextAndIconDp = header.padding.spaceBetweenTextAndIcon.toDp(density)
        header.sizes.iconText = header.sizes.height * header.ratios.iconTextHeightPercent
        header.sizes.iconTextSp = header.sizes.iconText.toSp(density)
        header.sizes.iconTextSpace = widthFull * header.ratios.iconTextSpacePercent
        header.sizes.iconTextSpaceSp = header.sizes.iconTextSpace.toSp(density)
        header.sizes.mainTextHeight = header.sizes.height * header.ratios.mainTextHeightPercent
        header.sizes.mainTextHeightSp = header.sizes.mainTextHeight.toSp(density)
        header.sizes.mainTextSpace = widthFull * header.ratios.mainTextSpacePercent
        header.sizes.mainTextSpaceSp = header.sizes.mainTextSpace.toSp(density)

        displayDataUI?.let {
            wLog("MainScreenObj::initHeader", "start")
            vLog("MainScreenObj::initHeader", "height ${header.sizes.height}")
            vLog("MainScreenObj::initHeader", "heightDp ${header.sizes.heightDp}")
            vLog("MainScreenObj::initHeader", "spaceBetweenTextAndIcon ${header.padding.spaceBetweenTextAndIcon}")
            vLog("MainScreenObj::initHeader", "spaceBetweenTextAndIconDp ${header.padding.spaceBetweenTextAndIconDp}")
            vLog("MainScreenObj::initHeader", "iconText ${header.sizes.iconText}")
            vLog("MainScreenObj::initHeader", "iconTextSp ${header.sizes.iconTextSp}")
            vLog("MainScreenObj::initHeader", "iconTextSpace ${header.sizes.iconTextSpace}")
            vLog("MainScreenObj::initHeader", "iconTextSpaceSp ${header.sizes.iconTextSpaceSp}")
            vLog("MainScreenObj::initHeader", "mainTextHeight ${header.sizes.mainTextHeight}")
            vLog("MainScreenObj::initHeader", "mainTextHeightSp ${header.sizes.mainTextHeightSp}")
            vLog("MainScreenObj::initHeader", "mainTextSpace ${header.sizes.mainTextSpace}")
            vLog("MainScreenObj::initHeader", "mainTextSpaceSp ${header.sizes.mainTextSpaceSp}")
        }
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    fun create(context: Context): MainScreenObj {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        allWeightsHeight = header.ratios.heightWeight + delimiter.ratios.heightWeight + list.ratios.heightWeight

        wLog("MainScreenObj::create", "start")
        displayDataUI?.let {
            vLog("MainScreenObj::create", "widthFull = $widthFull")
            vLog("MainScreenObj::create", "heightFull = $heightFull")
            vLog("MainScreenObj::create", "density = $density")
            vLog("MainScreenObj::create", "weight height = $allWeightsHeight")
        }

        initHeader()
        initTutoButton()
        initDelimiter()
        initList()
        initBluetoothButton()
        initWifiButton()
        return this
    }
}