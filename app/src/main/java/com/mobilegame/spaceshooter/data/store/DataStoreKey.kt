package com.mobilegame.spaceshooter.data.store

class DataStoreKey (val value: String) {
    companion object {
        val LAST_TEN_GAMES_KEY: DataStoreKey = DataStoreKey("last_ten_games_key")
        val VICTORIOUS_GAMES_KEY: DataStoreKey = DataStoreKey("victorious_games_key")
        val DEFEATED_GAMES_KEY: DataStoreKey = DataStoreKey("defeated_games_key")
        val LONGEST_STREAK_KEY: DataStoreKey = DataStoreKey("longest_streak_key")
        val WIN_RATE_KEY: DataStoreKey = DataStoreKey("win_rate_key")
        val PREFERRED_SHIP_KEY: DataStoreKey = DataStoreKey("preferred_ship_key")
        val OPPONENT_NAME_LIST_KEY: DataStoreKey = DataStoreKey("opponent_name_list_key")
        fun opponentHistoryKey(index: Int):DataStoreKey = DataStoreKey("list_i_${index}_history_key")
    }
}