package com.example.tictactoe.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PlayerDao {

    @Query("SELECT * from player_table ORDER BY name DESC")
    fun getAllPlayers(): LiveData<List<Player>>

    @Insert
    fun insert(player: Player)

    @Query("DELETE FROM player_table")
    fun deleteAll()
}