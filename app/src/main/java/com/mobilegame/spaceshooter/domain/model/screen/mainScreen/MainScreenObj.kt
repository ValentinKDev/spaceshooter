package com.mobilegame.spaceshooter.domain.model.screen.mainScreen

import android.content.Context
import androidx.compose.ui.unit.Dp
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp

object MainScreenObj {
    val header = HeaderMainScreen
    val delimiter = DelimiterMainScreen
    val list = ListMainScreen

    object HeaderMainScreen {
        val ratios = RatiosHeaderMainScreen
        val sizes = SizesHeaderMainScreen
        val colors = ColorsHeaderMainScreen
        val text = TextHeaderMainScreen
        val tutorialButton = TutorialButton

        object RatiosHeaderMainScreen {
            const val heightWeight = 1F
            const val textPaddingStart = 0.1F
            const val buttonPaddingEnd = 0.1F
        }

        object SizesHeaderMainScreen {
            var heightDp = Dp.Unspecified
            var widthDp = Dp.Unspecified
        }

        object ColorsHeaderMainScreen {
            val text = MyColor.applicationText
        }
        object TextHeaderMainScreen {
            const val messasge = "WELCOME"
        }
        object TutorialButton
    }

    object DelimiterMainScreen {
        val ratios = RatiosDelimiterMainScreen
        val sizes = SizesDelimiterMainScreen
        val colors = ColorsDelimiterMainScreen

        object RatiosDelimiterMainScreen {
            const val heightWeight = 0.08F
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
        val buttonWifi = ButtonWifiMainScreen
        val buttonBluetooth = ButtonBluetooth

        object RatioListMainScreen {
            const val heightWeight = 4.92F
        }
        object SizesListMainScreen {
            var heightDp = Dp.Unspecified
        }
        object ButtonWifiMainScreen {

        }
        object ButtonBluetooth {

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
        header.sizes.heightDp = (heightFull * (header.ratios.heightWeight / allWeightsHeight)).toDp(density)

        displayDataUI?.let {
            wLog("MainScreenObj::initHeader", "start")
            vLog("MainScreenObj::initHeader", "heightDp ${header.sizes.heightDp}")
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
        initDelimiter()
        initList()
        return this
    }
}