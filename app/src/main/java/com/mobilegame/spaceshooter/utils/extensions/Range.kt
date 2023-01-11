package com.mobilegame.spaceshooter.utils.extensions

fun ClosedFloatingPointRange<Float>.degreeToRadianRange(): ClosedFloatingPointRange<Float> {
    return (this.endInclusive * -1F).toRad()..(this.start * -1F).toRad()
//    return (this.start ).toRad()..(this.endInclusive ).toRad()
}

infix fun Float.sweepAngle(plusAngle: Float): ClosedFloatingPointRange<Float> = this..(this + plusAngle)