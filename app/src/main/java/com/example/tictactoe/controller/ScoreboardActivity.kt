package com.example.tictactoe.controller

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.tictactoe.R
import com.example.tictactoe.model.PlayerListAdapter
import com.example.tictactoe.model.PlayerViewModel
import kotlinx.android.synthetic.main.activity_scoreboard.*


class ScoreboardActivity : AppCompatActivity() {

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        val recyclerView = recyclerview
        val adapter = PlayerListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)

        playerViewModel.allPlayers.observe(this, Observer { players ->
            // Update the cached copy of the words in the adapter.
            players?.let { adapter.setPlayers(it) }
        })
    }
}
