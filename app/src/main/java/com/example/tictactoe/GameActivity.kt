package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.tictactoe.controller.GameManager
import com.example.tictactoe.model.GameState
import com.example.tictactoe.model.Seed
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    val game: GameManager = GameManager()
    var buttons = arrayOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        buttons = arrayOf(
            cell_0x0, cell_0x1, cell_0x2,
            cell_1x0, cell_1x1, cell_1x2,
            cell_2x0, cell_2x1, cell_2x2
        )

        for(button in buttons) {
            button.setOnClickListener(cellClickHandler)
        }

        game.startGame()

    }

    val cellClickHandler = View.OnClickListener {
        view ->

        when(view.id) {
            cell_0x0.id -> {
                game.playMove(game.currentPlayer, 0)
            }
            cell_0x1.id -> game.playMove(game.currentPlayer, 1)
            cell_0x2.id -> game.playMove(game.currentPlayer, 2)
            cell_1x0.id -> game.playMove(game.currentPlayer, 3)
            cell_1x1.id -> game.playMove(game.currentPlayer, 4)
            cell_1x2.id -> game.playMove(game.currentPlayer, 5)
            cell_2x0.id -> game.playMove(game.currentPlayer, 6)
            cell_2x1.id -> game.playMove(game.currentPlayer, 7)
            cell_2x2.id -> game.playMove(game.currentPlayer, 8)
        }

        updateBoard()

        when(game.currentGameState) {
            GameState.CROSS_WINS    -> null
            GameState.CIRCLE_WINS   -> null
            GameState.DRAW          -> null
            GameState.PLAYING       -> null
        }
    }

    fun updateBoard() {

        for(i in 0..8) {
            val currentButton = buttons[i]
            val currentCell = game.board.cells[i]

            val character = when(currentCell.data) {
                Seed.CROSS  -> "X"
                Seed.CIRCLE -> "O"
                else -> ""
            }

            currentButton.text = character
        }

    }

}
