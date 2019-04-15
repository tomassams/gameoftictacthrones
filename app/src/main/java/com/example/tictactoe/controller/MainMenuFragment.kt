package com.example.tictactoe.controller

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tictactoe.R
import kotlinx.android.synthetic.main.fragment_main_menu.*

class MainMenuFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

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
            val intent = Intent(activity, GameActivity::class.java)
            intent.putExtra("GameMode", "MULTI_PLAYER")
            startActivity(intent)
        }

    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
