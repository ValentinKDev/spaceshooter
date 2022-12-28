package com.mobilegame.spaceshooter.logic.model.data.sensor

class XYZ (
    var x: Float = 0F,
    var y: Float = 0F,
    var z: Float = 0F,
) {
    override fun toString(): String {
        return "x $x y $y z $z"
    }
}

fun XYZ.notZero(): Boolean = this.x != 0F && this.y != 0F && this.z != 0F
//fun XYZ.Zero(): XYZ = XYZ(0F, 0F, 0F)