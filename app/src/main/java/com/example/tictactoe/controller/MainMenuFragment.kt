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
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnSingleplayer.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragment = MenuSinglePlayerDialog()

            fragment.show(fragmentManager, "SINGLE_PLAYER_DIALOG")
        }

        btnMultiplayer.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragment = MenuMultiPlayerDialog()

            fragment.show(fragmentManager, "MULTI_PLAYER_DIALOG")
        }

        btnShowScoreboard.setOnClickListener {
            val intent = Intent(activity, ScoreboardActivity::class.java)
            startActivity(intent)
        }
    }
}
