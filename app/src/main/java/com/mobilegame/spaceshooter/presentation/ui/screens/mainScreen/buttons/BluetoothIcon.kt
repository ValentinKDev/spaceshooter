package com.mobilegame.spaceshooter.presentation.ui.screens.mainScreen.buttons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.mobilegame.spaceshooter.domain.model.screen.mainScreen.MainScreenViewModel
import com.mobilegame.spaceshooter.presentation.theme.MyColor
import com.mobilegame.spaceshooter.presentation.ui.screens.utils.CenterComposable

@Composable
fun BluetoothIcon(vm: MainScreenViewModel) {
    Box(
        Modifier
            .size(vm.ui.buttonBluetooth.sizes.squareHeightDp)
            .border(
                width = vm.ui.buttonBluetooth.sizes.squareStrokeDp,
                shape = RoundedCornerShape(8.dp),
                color = MyColor.Platinium
            )
    ) {
        CenterComposable {
            Canvas(
                Modifier
                    .size(vm.ui.buttonBluetooth.sizes.canvasDp)
            ) {
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p2,
                    end = vm.ui.buttonBluetooth.points.p5,
                    color = MyColor.Platinium,
                    cap = StrokeCap.Round,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconBigStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p5,
                    end = vm.ui.buttonBluetooth.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.Platinium,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconBigStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p5,
                    end = vm.ui.buttonBluetooth.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.Platinium,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconBigStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p6,
                    end = vm.ui.buttonBluetooth.points.p1,
                    color = MyColor.Platinium,
                    cap = StrokeCap.Round,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconBigStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p1,
                    end = vm.ui.buttonBluetooth.points.p3,
                    color = MyColor.Platinium,
                    cap = StrokeCap.Round,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconBigStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p3,
                    end = vm.ui.buttonBluetooth.points.p4,
                    color = MyColor.Platinium,
                    cap = StrokeCap.Round,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconBigStroke
                )

                drawLine(
//                    start = p2,
                    start = vm.ui.buttonBluetooth.points.p2,
                    end = vm.ui.buttonBluetooth.points.p5,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p5,
                    end = vm.ui.buttonBluetooth.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p5,
                    end = vm.ui.buttonBluetooth.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p5,
                    end = vm.ui.buttonBluetooth.points.p6,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p6,
                    end = vm.ui.buttonBluetooth.points.p1,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p1,
                    end = vm.ui.buttonBluetooth.points.p3,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
                drawLine(
                    start = vm.ui.buttonBluetooth.points.p3,
                    end = vm.ui.buttonBluetooth.points.p4,
                    cap = StrokeCap.Round,
                    color = MyColor.applicationBackground,
                    strokeWidth = vm.ui.buttonBluetooth.sizes.iconSmallStroke
                )
            }
        }
    }
}