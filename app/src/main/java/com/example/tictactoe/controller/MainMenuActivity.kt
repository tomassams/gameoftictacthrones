package com.example.tictactoe.controller

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val myCustomFont : Typeface? = ResourcesCompat.getFont(this, R.font.gameofthrones)
        headerTextView.typeface = myCustomFont
        subHeaderTextView.typeface = myCustomFont
    }
}
