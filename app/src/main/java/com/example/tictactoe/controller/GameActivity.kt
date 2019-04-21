package com.example.tictactoe.controller

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton
import com.example.tictactoe.R
import com.example.tictactoe.model.*
import com.example.tictactoe.room.Winner
import com.example.tictactoe.room.WinnerViewModel
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.fragment_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }




    /*
    TODO: decide when to start and stop the timer based on these activity lifecycle methods
    it isnt necessarily intended to stop on app pause..
     */
    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
