package com.mobilegame.spaceshooter.logic.uiHandler.screens.games.background

import androidx.compose.ui.geometry.Offset

data class Star (
    var type: StarType = StarType.Little,
    val anchor: Offset = Offset.Zero,
    var patternArray: Array<Offset> = Array(9){Offset.Zero}
    //todo: ?
) {
//    var offsetsList: Array<Offset> = emptyArray()
    fun bigOrNull(): Boolean? = if (type == StarType.Big) true else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Star

        if (type != other.type) return false
        if (anchor != other.anchor) return false
        if (!patternArray.contentEquals(other.patternArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + anchor.hashCode()
        result = 31 * result + patternArray.contentHashCode()
        return result
    }
}