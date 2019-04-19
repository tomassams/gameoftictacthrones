package com.example.tictactoe.controller

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.example.tictactoe.model.Winner
import com.example.tictactoe.model.WinnerDao


class WinnerRepository(private val winnerDao: WinnerDao) {

    val allWinners: LiveData<List<Winner>> = winnerDao.getTopTenWinners()

    @WorkerThread
    suspend fun insert(winner: Winner) {
        winnerDao.insert(winner)
    }
}