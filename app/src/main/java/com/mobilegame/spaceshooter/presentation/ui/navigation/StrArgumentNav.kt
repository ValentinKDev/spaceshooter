package com.mobilegame.spaceshooter.presentation.ui.navigation

import androidx.compose.runtime.Composable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.ship.types.ShipType

private val ListType = object : TypeToken<List<String>>() {}.type

class StrArgumentNav() {
    companion object {
        fun serializeToInGameArg(userShipTypeName: String, enemiesShipTypeName: String): String {
            val gson = Gson()
            return gson.toJson(listOf(userShipTypeName, enemiesShipTypeName))
        }
        fun deserializeToInGameArg(argStr: String): List<ShipType> {
            val gson = Gson()
            val listName: List<String> = gson.fromJson(argStr, ListType)
            return listName.map { ShipType.getType(it) }
        }
    }
}