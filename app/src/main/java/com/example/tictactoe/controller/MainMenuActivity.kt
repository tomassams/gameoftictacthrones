package com.example.tictactoe.controller

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_main_menu.*

/**
 * the main / welcome activity which the user enters upon starting the app
 * contains a MainMenuFragment and a simple heading / title text
 */
class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // set the custom font on our header text
        val gotFont : Typeface? = ResourcesCompat.getFont(this, R.font.gameofthrones)
        headerTextView.typeface = gotFont
    }
}
