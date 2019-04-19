package com.example.tictactoe.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.tictactoe.controller.PlayerRepository
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext


class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: PlayerRepository
    val allPlayers: LiveData<List<Player>>

    init {
        val playerDao = PlayerRoomDatabase.getDatabase(application, scope).playerDao()
        repository = PlayerRepository(playerDao)
        allPlayers = repository.allPlayers
    }

    fun insert(player: Player) = scope.launch(Dispatchers.IO) {
        repository.insert(player)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}