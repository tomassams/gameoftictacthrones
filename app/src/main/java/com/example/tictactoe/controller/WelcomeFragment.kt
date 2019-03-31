package com.example.tictactoe.controller


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.R
import com.example.tictactoe.model.GameMode
import kotlinx.android.synthetic.main.fragment_welcome.*

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false)
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

        // testing fragments in this one
        btnShowScoreboard.setOnClickListener {
            val fm = activity?.supportFragmentManager
            val frag = StartGameDialogFragment()
            frag.show(fm, "tag")
        }

    }

}
