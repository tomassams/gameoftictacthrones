package com.example.tictactoe.room

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread


class WinnerRepository(private val winnerDao: WinnerDao) {

    val allWinners: LiveData<List<Winner>> = winnerDao.getTopTenWinners()

    @WorkerThread
    suspend fun insert(winner: Winner) {
        winnerDao.insert(winner)
    }
}