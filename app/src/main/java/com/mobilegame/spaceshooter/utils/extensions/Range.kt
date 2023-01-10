package com.mobilegame.spaceshooter.utils.extensions

fun ClosedFloatingPointRange<Float>.toRadian(): ClosedFloatingPointRange<Float> {
    return (this.endInclusive * -1F).toRad()..(this.start * -1F).toRad()
//    return (this.start ).toRad()..(this.endInclusive ).toRad()
}