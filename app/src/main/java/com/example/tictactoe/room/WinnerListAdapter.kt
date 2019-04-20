package com.example.tictactoe.room

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tictactoe.R

class WinnerListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WinnerListAdapter.WinnerViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var winners = emptyList<Winner>() // Cached copy of words

    inner class WinnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val winnerItemView: TextView = itemView.findViewById(R.id.nameTextView)
        val winsItemView: TextView = itemView.findViewById(R.id.winsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinnerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WinnerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WinnerViewHolder, position: Int) {
        val current = winners[position]
        holder.winnerItemView.text = current.name
        holder.winsItemView.text = current.wins.toString()
    }

    internal fun setWinners(winners: List<Winner>) {
        this.winners = winners
        notifyDataSetChanged()
    }

    override fun getItemCount() = winners.size
}