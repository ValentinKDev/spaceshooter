package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val densityRef = 3.0F
fun Float.toDp(densityF: Float): Dp = (this / densityF).dp
fun Float.toSp(densityF: Float): TextUnit = (this / densityF).sp
//todo : might better to use the ratio PixelNumberWidht/PixelNumberHeight for the accuracy to the reference
fun Float.toSpRef(densityF: Float): TextUnit = ((this / densityRef) * (densityRef / densityF)).sp
