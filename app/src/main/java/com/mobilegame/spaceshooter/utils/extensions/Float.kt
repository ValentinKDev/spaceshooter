package com.mobilegame.spaceshooter.utils.extensions

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

inline fun Float.toDp(densityF: Float): Dp = (this / densityF).dp
inline fun Float.toSp(densityF: Float): TextUnit = (this / densityF).sp
