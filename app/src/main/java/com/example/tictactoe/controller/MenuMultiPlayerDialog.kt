package com.example.tictactoe.controller

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tictactoe.R
import kotlinx.android.synthetic.main.fragment_menu_multi_player_dialog.*
import kotlinx.android.synthetic.main.fragment_menu_single_player_dialog.*

class MenuMultiPlayerDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_multi_player_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startMultiPlayerGameBtn.setOnClickListener { view ->

            val playerOne = playerOneNameEditText.text.toString()
            val playerTwo = playerTwoNameEditText.text.toString()

            if(
                playerOne.isNotEmpty() &&
                playerTwo.isNotEmpty() &&
                playerOne != playerTwo
            ) {
                val intent = Intent(activity, GameActivity::class.java)
                intent.putExtra("GameMode", "MULTI_PLAYER")
                intent.putExtra("PlayerOneName", playerOne)
                intent.putExtra("PlayerTwoName", playerTwo)
                startActivity(intent)
            }
        }

    }

}
