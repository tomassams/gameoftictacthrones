package com.example.tictactoe.controller


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val intent = Intent(activity, GameActivity::class.java)

        btnSingleplayer.setOnClickListener {
            intent.putExtra("GameMode", "SINGLE_PLAYER")
            startActivity(intent)
        }

        btnMultiplayer.setOnClickListener {
            intent.putExtra("GameMode", "MULTI_PLAYER")
            startActivity(intent)
        }

        btnShowScoreboard.setOnClickListener {

        }

    }

}
