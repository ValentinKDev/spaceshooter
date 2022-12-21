package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize

object InGameScreenAdapter {
    lateinit var spaceShip: SpaceShipIconAdapter
    val position = PositionInGameScreen
    val sizes = SizesInGameScreen
    var density = 0F


    object SizesInGameScreen {
        var display = Size.Zero
        var displayDp = DpSize.Zero
        var displayDpDeltaBox = DpSize.Zero
        var shipBox = 0F
        var shipBoxDp = Dp.Unspecified
    }

    object PositionInGameScreen {
        var pCenter = Offset.Zero
        var pCenterDp = DpOffset.Zero
    }

    fun initSpaceShip(context: Context) {
        sizes.shipBox = sizes.display.height * 0.11F
        sizes.shipBoxDp = sizes.shipBox.toDp(density)
        sizes.displayDpDeltaBox = DpSize(
            width = sizes.displayDp.width - sizes.shipBoxDp,
            height = sizes.displayDp.height - sizes.shipBoxDp
        )
        spaceShip = SpaceShipIconAdapter( context = context, shipBox = 25F )
        position.pCenter = Offset(x = sizes.display.width / 2F, y = sizes.display.height / 2F)
        position.pCenterDp = position.pCenter.toDpOffset(density)
        displayDataUI?.let {
            wLog("InGameScreenAdapter::initSpaceShip", "spaceShip")
            vLog("InGameScreenAdapter::initSpaceShip", "shipBox ${sizes.shipBox}")
            vLog("InGameScreenAdapter::initSpaceShip", "pCenter ${position.pCenter}")
        }
    }

    fun create(context: Context, displaySize: Size): InGameScreenAdapter {
        density = context.resources.displayMetrics.density
        sizes.display = displaySize
        sizes.displayDp = sizes.display.toDpSize(density)

        displayDataUI?.let {
            wLog("InGameScreenAdapter::create", "start")
            vLog("InGameScreenAdapter::create", "display ${sizes.display}")
            vLog("InGameScreenAdapter::create", "display ${sizes.displayDp}")
        }
        initSpaceShip(context)
        return this
    }
}
