package com.mobilegame.spaceshooter.data.sensor

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
        val ALMOST_ZERO = XYZ(0.1F, 0.1F, 0.1F)

        fun listZero(n: Int): MutableList<XYZ> {
            val mutableList = mutableListOf<XYZ>()
            for (i in 0 until n) {
                mutableList += ZERO
            }
            return mutableList
        }
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
