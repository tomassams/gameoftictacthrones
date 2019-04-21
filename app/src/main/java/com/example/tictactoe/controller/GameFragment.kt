package com.example.tictactoe.controller

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.tictactoe.R
import com.example.tictactoe.model.*
import com.example.tictactoe.room.Winner
import com.example.tictactoe.room.WinnerViewModel
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {

    private lateinit var winnerViewModel: WinnerViewModel

    private val game: Game = Game()
    private var buttons: Array<ImageButton> = arrayOf()

    private lateinit var timer: Timer

    private lateinit var playerOneName: String
    private lateinit var playerTwoName: String

    private lateinit var playerOneSeed: Seed

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity: FragmentActivity = requireActivity()
        val intent: Intent = activity.intent

        timer = Timer(game_timer)
        timer.start()

        val gotFont : Typeface? = ResourcesCompat.getFont(activity, R.font.gameofthrones)
        turnInfoTextView.typeface = gotFont

        mainmenu_btn.setOnClickListener {
            val mainMenuIntent = Intent(activity, MainMenuActivity::class.java)
            startActivity(mainMenuIntent)
        }

        // declare and attach listeners to the 9 game cells
        buttons = arrayOf (cell_0, cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8)
        buttons.forEach { button ->
            button.setOnClickListener(cellClickHandler)
        }

        // fetch the selected gamemode based on main menu choice
        val gameMode = when(intent.getStringExtra("GameMode")) {
            "SINGLE_PLAYER" -> GameMode.SINGLE_PLAYER
            "MULTI_PLAYER" -> GameMode.MULTI_PLAYER
            else -> return
        }

        // fetch the selected seed for p1 based on main menu choice
        playerOneSeed = when(intent.getStringExtra("PlayerOneSeed")) {
            "CIRCLE" -> Seed.CIRCLE
            "CROSS" -> Seed.CROSS
            else -> return
        }

        // fetch the p1 and p2 names from main menu input
        playerOneName = intent.getStringExtra("PlayerOneName")
        playerTwoName = when(gameMode) {
            GameMode.SINGLE_PLAYER -> "TTTBot"
            GameMode.MULTI_PLAYER -> intent.getStringExtra("PlayerTwoName")
        }

        // start the game
        game.startGame(gameMode, playerOneSeed)
        updateBoard()
    }


    /**
     * Handles button clicks in the game cells, ie. when anyone attempts to play a move
     */
    private val cellClickHandler: View.OnClickListener = View.OnClickListener {
            view ->

        // decode which cell was clicked
        val cellNum: Int = when(view.id) {
            cell_0.id -> 0
            cell_1.id -> 1
            cell_2.id -> 2
            cell_3.id -> 3
            cell_4.id -> 4
            cell_5.id -> 5
            cell_6.id -> 6
            cell_7.id -> 7
            cell_8.id -> 8
            else      -> -1 // invalid
        }

        val moveMade: Boolean = game.playHumanMove(game.currentPlayer, cellNum)

        if(moveMade) {
            setNextPlayer()
            updateBoard()
        }

        // if its a single player game, the bot has to make its move too
        if (
            moveMade
            && game.currentGameMode == GameMode.SINGLE_PLAYER
            && game.currentGameState == GameState.PLAYING
        ) {
            game.playBotMove()

            setNextPlayer()
            updateBoard()
        }

        when(game.currentGameState) {
            GameState.CROSS_WINS    -> saveWinner(winner = seedToPlayerName(Seed.CROSS))
            GameState.CIRCLE_WINS   -> saveWinner(winner = seedToPlayerName(Seed.CIRCLE))
            else -> null
        }
    }

    /**
     * simple helper that returns the player name based on seed
     */
    private fun seedToPlayerName(seed: Seed): String {
        return when (seed) {
            playerOneSeed -> playerOneName
            else -> playerTwoName
        }
    }

    /**
     * store the winner in the Room database
     */
    private fun saveWinner(winner: String) {
        Log.w("winLog", "saving $winner as the winner!")
        winnerViewModel = ViewModelProviders.of(this).get(WinnerViewModel::class.java)
        winnerViewModel.insert(Winner(name = winner))
    }

    /**
     * changes turns
     */
    private fun setNextPlayer() {
        game.currentPlayer = game.opposite(game.currentPlayer)
    }

    /**
     * refreshes the visible board with the latest data
     */
    private fun updateBoard() {
        val player = seedToPlayerName(game.currentPlayer)

        turnInfoTextView.text = when(game.currentGameState) {
            GameState.CIRCLE_WINS -> {
                val player = seedToPlayerName(Seed.CIRCLE)
                "$player wins!"
            }
            GameState.CROSS_WINS -> {
                val player = seedToPlayerName(Seed.CROSS)
                "$player wins!"
            }
            GameState.DRAW -> "Its a draw!"
            GameState.PLAYING -> "Next move: $player"
        }

        for(i in 0..8) {
            val currentButton = buttons[i]
            val currentCell = game.board.cells[i]

            if(game.currentGameState != GameState.PLAYING) {
                currentButton.isEnabled = false
            }

            when(currentCell.data) {
                Seed.CROSS  -> currentButton.setImageResource(R.drawable.house_targaryen)
                Seed.CIRCLE -> currentButton.setImageResource(R.drawable.house_baratheon)
            }

        }
    }

}
