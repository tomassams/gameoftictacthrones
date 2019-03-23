package com.example.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val intent = Intent(this, GameActivity::class.java)

        btnPlayVsFriend.setOnClickListener {
            startActivity(intent)
        }

        btnPlayVsComputer.setOnClickListener {
            startActivity(intent)
        }

        btnShowScoreboard.setOnClickListener {
            startActivity(intent)
        }


    }
}
