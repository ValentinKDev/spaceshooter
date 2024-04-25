package com.mobilegame.spaceshooter.presentation.ui.navigation

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats
import com.mobilegame.spaceshooter.logic.uiHandler.screens.tryAgainScreen.TryAgainUI

private val ListIntType = object : TypeToken<List<Int>>() {}.type
private val ListStringType = object : TypeToken<List<String>>() {}.type

class StrArgumentNav() {
    companion object {
//        val ARG_KEY_IN_GAME = "nav_arg_to_in_game"
        fun serializeArgToInGame(userShipTypeName: String, tryAgainStats: TryAgainStats): String {
            val gson = Gson()
            val strStats = tryAgainStats.serialize()
            val listStr = listOf(userShipTypeName, strStats)
            return gson.toJson(listStr)
        }
        fun deserializeArgToInGame(argStr: String): Pair<ShipType, TryAgainStats> {
            val gson = Gson()
            val listStr: List<String> = gson.fromJson(argStr, ListStringType)
            val userShipTypeName = listStr[0]
            val userShipType = ShipType.getType(userShipTypeName)
            val strStats = listStr[1]
            val stats = TryAgainStats.deserialize(strStats)
//            val strListInt = listStr[1]
//            val listInt = gson.fromJson<List<Int>>(strListInt, ListIntType)
//            val tryAgainStats = TryAgainStats(listInt[0], listInt[1], listInt[2])
            return Pair(userShipType, stats)
        }
        val ARG_KEY_TRY_AGAIN = "nav_arg_to_try_again"
        fun serializeArgToTryAgain(arg: TryAgainStats): String = arg.serialize()
        fun deserializeArgToTryAgain(argStr: String): TryAgainStats = TryAgainStats.deserialize(argStr)
        fun serializeArgToShipMenu(arg: TryAgainStats): String = arg.serialize()
        fun deserializeArgToShipMenu(argStr: String): TryAgainStats = TryAgainStats.deserialize(argStr)
    }
}