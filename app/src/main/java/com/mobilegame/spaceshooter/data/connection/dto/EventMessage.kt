package com.mobilegame.spaceshooter.data.connection.dto

import com.google.gson.Gson

//todo: implemante an blazingly faster solution https://www.youtube.com/watch?v=MuCK81q1edU
class EventMessage(val type: String, val sender: String, val message: String = "") {
    fun toJson(): String = Gson().toJson(this)

    companion object {
        const val MESSAGE_TERMINATOR = "\r\n"

        @JvmStatic
        fun fromJson(json: String): EventMessage = Gson().fromJson(json, EventMessage::class.java)

        val NONE = EventMessage(EventMessageType.None.name, "", "")
    }
}