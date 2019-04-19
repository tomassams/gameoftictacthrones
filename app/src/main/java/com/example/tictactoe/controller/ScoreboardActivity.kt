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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
    }
}
