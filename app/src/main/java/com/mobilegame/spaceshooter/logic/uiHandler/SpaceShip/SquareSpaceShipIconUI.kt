package com.mobilegame.spaceshooter.logic.uiHandler.SpaceShip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.utils.analyze.eLog
import com.mobilegame.spaceshooter.utils.extensions.*

class SquareSpaceShipIconUI(shipViewBox: Size) {
    private val TAG = object{}.javaClass.enclosingClass.simpleName
    val sizes = SizesSquareShip(shipViewBox)
    val points = PointsSquareShip(sizes)
    val magazine = MagazineShip(sizes)
    val colors = ColorsSquareShip()

    class SizesSquareShip(shipBox: Size) {
        val shipViewBoxSizeDp: DpSize = shipBox.toDpSize()
        val shipSize: Size = shipBox
        val shipSizeDp: DpSize = shipSize.toDpSize()
        val stroke: Float = shipBox.width * 0.06F
    }

    class PointsSquareShip(shipSizes: SizesSquareShip) {
        val center: Offset = shipSizes.shipSize.center
        val startCentralBar: Offset = Offset(x = center.x, y = 0F)
        val bottomCentralBar: Offset = Offset(center.x, center.y * 0.70F)
//        val topLeftShip: Offset = Offset(x = )
    }

    class MagazineShip(sizes: SizesSquareShip) {
        private val padding: Float = sizes.shipSize.height * 0.12F
        val ammoSize: Size = ((sizes.shipSize.height - (2F * padding) + sizes.stroke) / 3F).toSize()
        val innerSize: Size = ammoSize * 0.85F
        private val halfWidth: Float = ammoSize.width / 2F
        val m1: Offset = Offset.Zero - Offset(x = padding + ammoSize.width + (sizes.stroke / 2F), y = sizes.stroke / 2F)
        val m2: Offset = m1 yPlus  (ammoSize.height + padding)
        val m3: Offset = m2 yPlus  (ammoSize.height + padding)
        val m4: Offset = Offset(x = sizes.shipSize.width + (sizes.stroke / 2F) + padding ,y = sizes.stroke / -2F)
        val m5: Offset = m4 yPlus (ammoSize.height + padding)
        val m6: Offset = m5 yPlus (ammoSize.height + padding)

        fun getAmmunitionOffset(n: Int): Offset = when (n) {
            1 -> m3
            2 -> m6
            3 -> m2
            4 -> m5
            5 -> m1
            6 -> m4
            else -> {
                eLog("SpaceShipVM::getAmmunitionOffset", "ERROR getMunition(ui, --> ${n})")
                Offset.Zero
            }
        }

//        init {
//            displayDataUI.let {
//                Log.e(TAG, "$TAG: ${magazine.size} ")
//                Log.e(TAG, "$TAG: ${magazine.} ")
//            }
//        }
    }
    data class ColorsSquareShip(
        val outline: Color = MyColor.applicationContrast,
        var body: Color = MyColor.applicationBackground,
    )
}