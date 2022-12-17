package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import android.content.Context
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

    object ButtonWifiMainScreen {

    }
    object ButtonBluetooth {

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
        return this
    }
}