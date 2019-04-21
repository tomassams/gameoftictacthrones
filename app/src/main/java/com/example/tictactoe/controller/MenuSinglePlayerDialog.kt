package com.example.tictactoe.controller

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.fragment_menu_single_player_dialog.*

/**
 * a dialogfragment with game options when opting for a singleplayer game
 */
class MenuSinglePlayerDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_single_player_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pickCircleBtn.setOnClickListener(cellClickHandler)
        pickCrossBtn.setOnClickListener(cellClickHandler)
    }

    private val cellClickHandler = View.OnClickListener { view ->

        val activity: FragmentActivity = requireActivity()
        val seed: String = when(view.id) {
            pickCircleBtn.id -> "CIRCLE"
            pickCrossBtn.id -> "CROSS"
            else -> "ERROR"
        }

        val playerName: String = singlePlayerNameEditText.text.toString()
        if(playerName.isEmpty()) {
            Toast.makeText(activity, "Please fill in your name first!", Toast.LENGTH_LONG).show()
        }

        if(
            seed != "ERROR" &&
            playerName.isNotEmpty()
        ) {
            val intent = Intent(activity, GameActivity::class.java)
            intent.putExtra("GameMode", "SINGLE_PLAYER")
            intent.putExtra("PlayerOneSeed", seed)
            intent.putExtra("PlayerOneName", playerName)
            startActivity(intent)
        }
    }
}
