package com.example.tictactoe.controller

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.fragment_main_menu.*

/**
 * a fragment with our game's main menu consisting of 3 buttons:
 * - single player (opens a dialogfragment with additional choices)
 * - multi player (opens a dialogfragment with additional choices)
 * - scoreboard (starts the scoreboard activity)
 */
class MainMenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity: FragmentActivity = requireActivity()
        val fragmentManager: FragmentManager = activity.supportFragmentManager

        btnSingleplayer.setOnClickListener {
            val fragment = MenuSinglePlayerDialog()
            fragment.show(fragmentManager, "SINGLE_PLAYER_DIALOG")
        }

        btnMultiplayer.setOnClickListener {
            val fragment = MenuMultiPlayerDialog()
            fragment.show(fragmentManager, "MULTI_PLAYER_DIALOG")
        }

        btnShowScoreboard.setOnClickListener {
            val intent = Intent(activity, ScoreboardActivity::class.java)
            startActivity(intent)
        }
    }
}
