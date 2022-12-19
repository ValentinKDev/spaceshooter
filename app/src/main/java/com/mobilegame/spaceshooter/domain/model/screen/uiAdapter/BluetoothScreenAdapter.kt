package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog

object BluetoothScreenAdapter {
    val header = HeaderBluetoothScreen
    val delimiter = DelimiterBluetoothScreen
    val list = ListBluetoothScreen
    val colors = ColorsBluetoothScreen
    var backButton = BackButtonAdapter
    lateinit var bluetoothIcon: BluetoothIconAdapter
    var banner = BannerBluetoothScreen

    object BannerBluetoothScreen {
        val padding = PaddingBannerBluetoothScreen

        object PaddingBannerBluetoothScreen {
            const val top = 0.3F
//            const val bottom = 0.5F
        }
    }
    object ColorsBluetoothScreen {
        val contrast = MyColor.applicationContrast
    }

    object HeaderBluetoothScreen {
        val ratios = RatiosHeaderBluetoothScreen
        val sizes = SizesHeaderBluetoothScreen

        object RatiosHeaderBluetoothScreen {
            const val heightWeight = 1F
        }
        object SizesHeaderBluetoothScreen {
            var height = 0F
        }
    }

    object DelimiterBluetoothScreen {
        val ratios = RatiosDelimiterBluetoothScreen

        object RatiosDelimiterBluetoothScreen {
            const val heightWeight = 0.06F
        }
    }

    object ListBluetoothScreen {
        val ratios = RatiosBluetoothScreen

        object RatiosBluetoothScreen {
            const val heightWeight = 4.94F
            const val heightBanner = 1F
        }
    }

    private fun initHeader() {
        header.sizes.height = heightFull * (header.ratios.heightWeight / allWeightsHeight)

        displayDataUI?.let {
            wLog("BluetoothScreenObj::initHeader", "header")
            vLog("BluetoothScreenObj::initHeader", "height ${header.sizes.height}")
        }
    }

    private var widthFull = 0
    private var heightFull = 0
    private var density = 0F
    private var allWeightsHeight = 0F

    fun create(context: Context): BluetoothScreenAdapter {
        widthFull = context.resources.displayMetrics.widthPixels
        heightFull = context.resources.displayMetrics.heightPixels
        density = context.resources.displayMetrics.density
        allWeightsHeight = header.ratios.heightWeight + delimiter.ratios.heightWeight + list.ratios.heightWeight

        displayDataUI?.let {
            wLog("BluetoothScreenObj::create", "start")
            vLog("BluetoothScreenObj::create", "widthFull = ${widthFull}")
            vLog("BluetoothScreenObj::create", "heightFull = ${heightFull}")
            vLog("BluetoothScreenObj::create", "density = ${density}")
            vLog("BluetoothScreenObj::create", "weight height = ${allWeightsHeight}")
        }

        initHeader()
        backButton = BackButtonAdapter.create(context, header.sizes.height)
        bluetoothIcon = BluetoothIconAdapter(
            context,
            list.ratios.heightBanner * heightFull,
//            color = MyColor.applicationContrastTransparentPlus
        )
        return this
    }
}
