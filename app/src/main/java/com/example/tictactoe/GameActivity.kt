package com.example.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
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
            cell_0, cell_1, cell_2,
            cell_3, cell_4, cell_5,
            cell_6, cell_7, cell_8
        )

        for(button in buttons) {
            button.setOnClickListener(cellClickHandler)
        }

        game.startGame()

    }

    val cellClickHandler = View.OnClickListener {
        view ->

        when(view.id) {
            cell_0.id -> game.playMove(game.currentPlayer, 0)
            cell_1.id -> game.playMove(game.currentPlayer, 1)
            cell_2.id -> game.playMove(game.currentPlayer, 2)
            cell_3.id -> game.playMove(game.currentPlayer, 3)
            cell_4.id -> game.playMove(game.currentPlayer, 4)
            cell_5.id -> game.playMove(game.currentPlayer, 5)
            cell_6.id -> game.playMove(game.currentPlayer, 6)
            cell_7.id -> game.playMove(game.currentPlayer, 7)
            cell_8.id -> game.playMove(game.currentPlayer, 8)
        }

        updateBoard()

        when(game.currentGameState) {
            GameState.CROSS_WINS    -> Toast.makeText(this, "Cross wins!", Toast.LENGTH_LONG).show()
            GameState.CIRCLE_WINS   -> Toast.makeText(this, "Circle wins!", Toast.LENGTH_LONG).show()
            GameState.DRAW          -> Toast.makeText(this, "Its a draw!", Toast.LENGTH_LONG).show()
            GameState.PLAYING       -> null
        }

    }

    fun updateBoard() {

        for(i in 0..8) {

            val currentButton = buttons[i]
            val currentCell = game.board.cells[i]

            if(game.currentGameState != GameState.PLAYING) {
                currentButton.isEnabled = false
            }

            val character = when(currentCell.data) {
                Seed.CROSS  -> "X"
                Seed.CIRCLE -> "O"
                else -> ""
            }

            currentButton.text = character
        }

    }

    fun restartBoard(view: View) {

        for(i in 0..8) {

            buttons[i].isEnabled = true
            buttons[i].text = ""

        }

        game.startGame()

    }

}
