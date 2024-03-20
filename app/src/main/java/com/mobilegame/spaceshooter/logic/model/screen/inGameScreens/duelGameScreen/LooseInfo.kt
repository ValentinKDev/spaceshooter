package com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.duelGameScreen

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobilegame.spaceshooter.data.connection.dto.EventMessage
import java.net.InetAddress
import java.util.Date

data class LooseInfo(
    val shooterIp: InetAddress,
    val shooterName: String,
    val deadPlayerName: String,
    val deadPlayerIp: InetAddress,
    val exactMoment: String,
) {
    private val TAG = "LooseInfo"
    fun toJson(): String {
        val gson = Gson()
        val list = mutableListOf<String>()
        list.add(gson.toJson(shooterIp))
        list.add(shooterName)
        list.add(deadPlayerName)
        list.add(gson.toJson(deadPlayerIp))
        list.add(exactMoment)
        val str = gson.toJson(list)
        Log.w(TAG, "toJson: $str")
        return str
    }

    companion object {
        const val MESSAGE_TERMINATOR = "\r\n"

        @JvmStatic
        fun fromJson(json: String): LooseInfo {
            val gson = Gson()
//            val list: List<String> = gson.fromJson<List<String>>(json)
            val list: List<String> = gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
            return LooseInfo(
                shooterIp =  gson.fromJson(list.get(0), InetAddress::class.java),
                shooterName = list.get(1),
                deadPlayerName = list.get(2),
                deadPlayerIp = gson.fromJson(list.get(3), InetAddress::class.java),
                exactMoment = list.get(4),
            )
        }
    }
}
