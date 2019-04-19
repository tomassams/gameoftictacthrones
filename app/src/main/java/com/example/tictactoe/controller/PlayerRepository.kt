package com.example.tictactoe.controller

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import android.util.Log
import com.example.tictactoe.model.Player
import com.example.tictactoe.model.PlayerDao

class PlayerRepository(private val playerDao: PlayerDao) {

    val allPlayers: LiveData<List<Player>> = playerDao.getAllPlayers()

    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }
}