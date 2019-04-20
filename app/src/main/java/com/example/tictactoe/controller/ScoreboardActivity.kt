package com.example.tictactoe.controller

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.activity_scoreboard.*
import kotlinx.android.synthetic.main.recyclerview_item.*


class ScoreboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        val myCustomFont : Typeface? = ResourcesCompat.getFont(this, R.font.gameofthrones)
        scoreboardHeaderText.typeface = myCustomFont
    }
}
