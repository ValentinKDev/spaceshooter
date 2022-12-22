package com.mobilegame.spaceshooter.domain.model.screen.uiHandler.games

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.Device
import com.mobilegame.spaceshooter.domain.model.screen.uiHandler.SpaceShip.SpaceShipIconAdapter
import com.mobilegame.spaceshooter.utils.analyze.displayDataUI
import com.mobilegame.spaceshooter.utils.analyze.vLog
import com.mobilegame.spaceshooter.utils.analyze.wLog
import com.mobilegame.spaceshooter.utils.extensions.toDp
import com.mobilegame.spaceshooter.utils.extensions.toDpOffset
import com.mobilegame.spaceshooter.utils.extensions.toDpSize

class DuelGameScreenUI(displaySize: Size) {
    lateinit var spaceShip: SpaceShipIconAdapter
    val position = PositionInGameScreen()
    val sizes = SizesInGameScreen()
    private val device = Device

    data class SizesInGameScreen (
        var display: Size = Size.Zero,
        var displayDp: DpSize = DpSize.Zero,
        var displayDpDeltaBox: DpSize = DpSize.Zero,
        var shipBox: Float = 0F,
        var shipBoxDp: Dp = Dp.Unspecified,
    )

    data class PositionInGameScreen (
        var pCenter: Offset = Offset.Zero,
        var pCenterDp: DpOffset = DpOffset.Zero,
    )

    fun initSpaceShip() {
    }

    init {
        sizes.display = displaySize
        sizes.displayDp = sizes.display.toDpSize()
        sizes.shipBox = sizes.display.height * 0.11F
        sizes.shipBoxDp = sizes.shipBox.toDp()
        sizes.displayDpDeltaBox = DpSize(
            width = sizes.displayDp.width - sizes.shipBoxDp,
            height = sizes.displayDp.height - sizes.shipBoxDp,
        )
        spaceShip = SpaceShipIconAdapter( shipBox = 25F )
        position.pCenter = Offset(x = sizes.display.width / 2F, y = sizes.display.height / 2F)
        position.pCenterDp = position.pCenter.toDpOffset()
        displayDataUI?.let {
            wLog("InGameScreenAdapter::initSpaceShip", "spaceShip")
            vLog("InGameScreenAdapter::initSpaceShip", "shipBox ${sizes.shipBox}")
            vLog("InGameScreenAdapter::initSpaceShip", "pCenter ${position.pCenter}")
        }

        displayDataUI?.let { wLog("InGameScreenAdapter::create", "init") }
        initSpaceShip()
    }
}
