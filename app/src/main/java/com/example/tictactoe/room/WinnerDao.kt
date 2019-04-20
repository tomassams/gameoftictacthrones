package com.example.tictactoe.room

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface WinnerDao {

    @Query("SELECT name, count(1) AS wins FROM winner_table GROUP BY name ORDER BY count(1) desc LIMIT 10")
    fun getTopTenWinners(): LiveData<List<Winner>>

    @Insert
    fun insert(winner: Winner)

    @Query("DELETE FROM winner_table")
    fun deleteAll()
}