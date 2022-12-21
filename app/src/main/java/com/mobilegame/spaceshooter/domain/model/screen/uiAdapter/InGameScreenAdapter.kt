package com.mobilegame.spaceshooter.domain.model.screen.uiAdapter

import android.content.Context
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import com.mobilegame.spaceshooter.domain.model.screen.uiAdapter.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset

object InGameScreenAdapter {
    lateinit var spaceShip: SpaceShipIconAdapter
    val position = PositionInGameScreen
    var display = Size.Zero
    val sizes = SizesInGameScreen
    var density = 0F


    object SizesInGameScreen {
        var shipBox = 0F
    }

    object PositionInGameScreen {
        var pCenter = Offset.Zero
        var pCenterDp = DpOffset.Zero
    }

    fun initSpaceShip(context: Context) {
        sizes.shipBox = display.height * 0.11F
        spaceShip = SpaceShipIconAdapter( context = context, shipBox = 25F )
        position.pCenter = Offset(x = display.width / 2F, y = display.height / 2F)
        position.pCenterDp = position.pCenter.toDpOffset(density)
        displayDataUI?.let {
            wLog("InGameScreenAdapter::initSpaceShip", "spaceShip")
            vLog("InGameScreenAdapter::initSpaceShip", "shipBox ${sizes.shipBox}")
            vLog("InGameScreenAdapter::initSpaceShip", "pCenter ${position.pCenter}")
        }
    }

    fun create(context: Context, displaySize: Size): InGameScreenAdapter {
        display = displaySize
        density = context.resources.displayMetrics.density
        initSpaceShip(context)
        return this
    }
}
