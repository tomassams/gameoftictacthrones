package com.example.tictactoe.controller


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.R
import com.example.tictactoe.model.WinnerListAdapter
import com.example.tictactoe.model.WinnerViewModel
import kotlinx.android.synthetic.main.fragment_scoreboard.*

class ScoreboardFragment : Fragment() {

    private lateinit var winnerViewModel: WinnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scoreboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = recyclerview
        val adapter = WinnerListAdapter(activity as Context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity as Context)

        winnerViewModel = ViewModelProviders.of(this).get(WinnerViewModel::class.java)

        winnerViewModel.allWinners.observe(this, Observer { winners ->
            // Update the cached copy of the words in the adapter.
            winners?.let { adapter.setWinners(it) }
        })
    }


}
