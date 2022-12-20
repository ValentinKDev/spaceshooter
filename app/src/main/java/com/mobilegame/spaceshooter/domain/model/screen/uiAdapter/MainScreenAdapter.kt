package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.alpha
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toSp
import com.mobilegame.spaceshooter.utils.extensions.toSpRef

object MainScreenAdapter {
    val header = HeaderMainScreen
    val delimiter = DelimiterMainScreen
    val list = ListMainScreen
    val tutorialButton = TutorialButton
//    val buttonWifi = ButtonWifiMainScreen
//    var buttonBluetooth = BluetoothIconObj
    lateinit var buttonBluetooth: BluetoothIconAdapter
    lateinit var buttonWifi: WifiIconAdapter
    val instruction = InstructionMainScreen

    object InstructionMainScreen {
        val ratios = RatiosInstructionMainScreen
        val padding = PaddingInstructionsMainScreen
        val sizes = SizesInstructionsMainScreen
        val text = TextInstructionMainScreen
        val color = ColorInstructionMainScreen

        object PaddingInstructionsMainScreen {
            const val top = 0.85F
            const val bottom = 0.008F
            const val start = 0.2F
            const val end = 0.2F
        }
        object RatiosInstructionMainScreen {
            const val textHeightPercent = 0.45F
        }
        object SizesInstructionsMainScreen {
            var boxHeight = 0F
            var text = 0F
            var textSp = 0.sp
        }
        object ColorInstructionMainScreen {
            val targetColor = MyColor.applicationContrastTransparent
            val initialColor = MyColor.applicationContrast.alpha(0.2F)
        }
        object TextInstructionMainScreen {
            val message = "Hold the pressure"
        }
    }
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

            const val mainTextHeightPercent = 1F
//            const val mainTextHeightPercent = 0.33F
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
            val iconText = MyColor.applicationContrastTransparent
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

    private fun initInstruction() {
        instruction.sizes.boxHeight = (1F - instruction.padding.top - instruction.padding.bottom) * heightFull
        instruction.sizes.text = instruction.sizes.boxHeight * instruction.ratios.textHeightPercent
        instruction.sizes.textSp = instruction.sizes.text.toSp(density)
        displayDataUI?.let {
            wLog("MainScreenAdapter::initInstruction", "instruction")
            vLog("MainScreenAdapter::initInstruction", "boxHeight ${instruction.sizes.boxHeight}")
            vLog("MainScreenAdapter::initInstruction", "text ${instruction.sizes.text}")
            vLog("MainScreenAdapter::initInstruction", "textSp ${instruction.sizes.textSp}")
        }
    }

    private fun initTutoButton() {
        tutorialButton.sizes.iconButtonHeight = tutorialButton.ratios.iconButtonHeightPercentage * header.sizes.height
        tutorialButton.sizes.iconButtonHeightDp = tutorialButton.sizes.iconButtonHeight.toDp(density)
        tutorialButton.sizes.iconQuestionMarkHeight = tutorialButton.ratios.questionMarkHeightPercentage * header.sizes.height
        tutorialButton.sizes.iconQuestionMarkHeightDp = tutorialButton.sizes.iconQuestionMarkHeight.toDp(density)
        tutorialButton.sizes.circleStrokeWidth = tutorialButton.ratios.strokeHeightPercentage * header.sizes.height

        displayDataUI?.let {
            wLog("MainScreenAdapter::initTutoButton", "tuto button")
            vLog("MainScreenAdapter::initTutoButton", "iconButtonHeight ${tutorialButton.sizes.iconButtonHeight}")
            vLog("MainScreenAdapter::initTutoButton", "iconButtonHeightDp ${tutorialButton.sizes.iconButtonHeightDp}")
            vLog("MainScreenAdapter::initTutoButton", "iconQuestionMarkHeight ${tutorialButton.sizes.iconQuestionMarkHeight}")
            vLog("MainScreenAdapter::initTutoButton", "iconQuestionMarkHeightDp ${tutorialButton.sizes.iconQuestionMarkHeightDp}")
            vLog("MainScreenAdapter::initTutoButton", "circleStrokeWidth ${tutorialButton.sizes.circleStrokeWidth}")
        }
    }
    private fun initList() {
        list.sizes.heightDp = (heightFull * (list.ratios.heightWeight / allWeightsHeight)).toDp( density)

        displayDataUI?.let {
            wLog("MainScreenAdapter::iniList", "list")
            vLog("MainScreenAdapter::iniList", "heightDp ${list.sizes.heightDp}")
        }
    }
    private fun initDelimiter() {
        delimiter.sizes.heightDp = (heightFull * (delimiter.ratios.heightWeight / allWeightsHeight)).toDp( density)

        displayDataUI?.let {
            wLog("MainScreenAdapter::initDelimiter", "delimiter")
            vLog("MainScreenAdapter::initDelimiter", "heightDp ${delimiter.sizes.heightDp}")
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
        header.sizes.mainTextHeightSp = header.sizes.mainTextHeight.toSpRef(density)
        header.sizes.mainTextSpace = widthFull * header.ratios.mainTextSpacePercent
        header.sizes.mainTextSpaceSp = header.sizes.mainTextSpace.toSp(density)

        displayDataUI?.let {
            wLog("MainScreenAdapter::initHeader", "header")
            vLog("MainScreenAdapter::initHeader", "height ${header.sizes.height}")
            vLog("MainScreenAdapter::initHeader", "heightDp ${header.sizes.heightDp}")
            vLog("MainScreenAdapter::initHeader", "spaceBetweenTextAndIcon ${header.padding.spaceBetweenTextAndIcon}")
            vLog("MainScreenAdapter::initHeader", "spaceBetweenTextAndIconDp ${header.padding.spaceBetweenTextAndIconDp}")
            vLog("MainScreenAdapter::initHeader", "iconText ${header.sizes.iconText}")
            vLog("MainScreenAdapter::initHeader", "iconTextSp ${header.sizes.iconTextSp}")
            vLog("MainScreenAdapter::initHeader", "iconTextSpace ${header.sizes.iconTextSpace}")
            vLog("MainScreenAdapter::initHeader", "iconTextSpaceSp ${header.sizes.iconTextSpaceSp}")
            vLog("MainScreenAdapter::initHeader", "mainTextHeight ${header.sizes.mainTextHeight}")
            eLog("MainScreenAdapter::initHeader", "ratioText ${header.sizes.mainTextHeightSp / header.sizes.height}")
            vLog("MainScreenAdapter::initHeader", "mainTextHeightSp ${header.sizes.mainTextHeightSp}")
            vLog("MainScreenAdapter::initHeader", "mainTextSpace ${header.sizes.mainTextSpace}")
            vLog("MainScreenAdapter::initHeader", "mainTextSpaceSp ${header.sizes.mainTextSpaceSp}")
        }
    }
    fun initBluetoothButton(context: Context) {
        val squareSize = heightFull * 0.3F
        buttonBluetooth = BluetoothIconAdapter(context, squareSize)
    }
    fun initWifiButton(context: Context) {
        val squareSize = heightFull * 0.3F
        buttonWifi = WifiIconAdapter(context, squareSize, StrokeCap.Square, outlined = true)
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    fun create(context: Context): MainScreenAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        allWeightsHeight = header.ratios.heightWeight + delimiter.ratios.heightWeight + list.ratios.heightWeight

        displayDataUI?.let {
            wLog("MainScreenAdapter::create", "start")
            vLog("MainScreenAdapter::create", "widthFull = $widthFull")
            vLog("MainScreenAdapter::create", "heightFull = $heightFull")
            vLog("MainScreenAdapter::create", "density = $density")
            vLog("MainScreenAdapter::create", "weight height = $allWeightsHeight")
        }

        initHeader()
        initTutoButton()
        initDelimiter()
        initList()
        initInstruction()
        initBluetoothButton(context)
        initWifiButton(context)
        return this
    }
}
