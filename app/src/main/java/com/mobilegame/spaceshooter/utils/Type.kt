package com.mobilegame.spaceshooter.utils

import com.google.gson.reflect.TypeToken
import com.mobilegame.spaceshooter.logic.model.screen.inGameScreens.GameResult
import com.mobilegame.spaceshooter.logic.model.screen.tryAgainScreen.TryAgainStats

val TypeListInt = object : TypeToken<List<Int>>() {}.type
val TypeListString = object : TypeToken<List<String>>() {}.type
val TypeListGameResult = object : TypeToken<List<GameResult>>() {}.type
val TypePairStringInt = object : TypeToken<Pair<String, Int>>() {}.type
val TypeListPairStringInt = object : TypeToken<List<Pair<String, Int>>>() {}.type
val TypeTryAgainStats = object : TypeToken<TryAgainStats>() {}.type
//val TypeListString = object : TypeToken<List<Pair<String, Int>>>() {}.type
