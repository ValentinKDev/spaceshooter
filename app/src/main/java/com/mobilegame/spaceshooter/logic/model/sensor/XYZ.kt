package com.mobilegame.spaceshooter.logic.model.sensor

class XYZ (
    var x: Float = 0F,
    var y: Float = 0F,
    var z: Float = 0F,
) {
    override fun toString(): String {
        return "x $x y $y z $z"
    }
    companion object {
        val ZERO = XYZ(0F, 0F, 0F)

        fun listZero(n: Int): MutableList<XYZ> {
            val mutableList = mutableListOf<XYZ>()
            for (i in 0 until n) {
                mutableList += ZERO
            }
            return mutableList
        }
    }
}

fun XYZ.notZero(): Boolean = this.x != 0F && this.y != 0F && this.z != 0F
fun XYZ.isZero(): Boolean = this.x == 0F && this.y == 0F && this.z == 0F
fun XYZ.clone(): XYZ = XYZ(this.x, this.y, this.z)

fun MutableList<XYZ>.getSumX(): Float {
    var sum = 0F
    for (i in 0 until this.size) {
        sum += this[i].x
    }
    return sum
}
fun MutableList<XYZ>.getSumY(): Float {
    var sum = 0F
    for (i in 0 until this.size) {
        sum += this[i].y
    }
    return sum
}
fun MutableList<XYZ>.getSumZ(): Float {
    var sum = 0F
    for (i in 0 until this.size) {
        sum += this[i].z
    }
    return sum
}

fun MutableList<XYZ>.smooth(smoothRatio: Int) {
    var value = this[0]
    for ( i in 1 until this.lastIndex) {
        val current = this[i]
        value = (current minus value) div smoothRatio
        this[i] = value
    }
}

infix fun XYZ.minus(xyz: XYZ) = XYZ (
    x = this.x - xyz.x,
    y = this.y - xyz.y,
    z = this.z - xyz.z,
)

infix fun XYZ.plus(xyz: XYZ) = XYZ (
    x = this.x + xyz.x,
    y = this.y + xyz.y,
    z = this.z + xyz.z,
)

infix fun XYZ.div(d: Int) = XYZ (
    x = this.x / d,
    y = this.y / d,
    z = this.z / d,
)
