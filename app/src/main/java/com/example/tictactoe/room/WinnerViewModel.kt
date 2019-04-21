package com.example.tictactoe.room

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class WinnerViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: WinnerRepository
    val allWinners: LiveData<List<Winner>>

    init {
        val winnerDao = WinnerRoomDatabase.getDatabase(application, scope).winnerDao()
        repository = WinnerRepository(winnerDao)
        allWinners = repository.allWinners
    }

    fun insert(winner: Winner) = scope.launch(Dispatchers.IO) {
        repository.insert(winner)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}