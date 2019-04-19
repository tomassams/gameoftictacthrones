package com.example.tictactoe.model

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tictactoe.R

class PlayerListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var players = emptyList<Player>() // Cached copy of words

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerItemView: TextView = itemView.findViewById(R.id.nameTextView)
        //val winsItemView: TextView = itemView.findViewById(R.id.winsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val current = players[position]
        holder.playerItemView.text = current.name
    }

    internal fun setPlayers(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    override fun getItemCount() = players.size
}