package com.example.tictactoe.controller

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.tictactoe.R
import com.example.tictactoe.model.WinnerListAdapter
import com.example.tictactoe.model.WinnerViewModel
import kotlinx.android.synthetic.main.activity_scoreboard.*


class ScoreboardActivity : AppCompatActivity() {

    private lateinit var winnerViewModel: WinnerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        val recyclerView = recyclerview
        val adapter = WinnerListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        winnerViewModel = ViewModelProviders.of(this).get(WinnerViewModel::class.java)

        winnerViewModel.allWinners.observe(this, Observer { winners ->
            // Update the cached copy of the words in the adapter.
            winners?.let { adapter.setWinners(it) }
        })
    }
}
