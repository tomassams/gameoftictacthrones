package com.example.tictactoe.controller

import android.util.Log
import com.example.tictactoe.model.Board
import com.example.tictactoe.model.Cell
import com.example.tictactoe.model.GameState
import com.example.tictactoe.model.Seed

class GameManager() {

    val board: Board = Board()

    var currentGameState = GameState.PLAYING
    var currentPlayer = Seed.CIRCLE

    fun startGame() {
        board.init()

        currentGameState = GameState.PLAYING
        currentPlayer = Seed.CIRCLE
    }

    fun playMove(player: Seed, cellnum: Int) {

        val cell: Cell = board.cells[cellnum]

        if(cell.data == Seed.EMPTY) {

            cell.data = player

            when(currentPlayer) {
                Seed.CROSS  -> currentPlayer = Seed.CIRCLE
                Seed.CIRCLE -> currentPlayer = Seed.CROSS
            }

        }

        if(board.checkForVictory(player)) {

            Log.w("victoryCheck", "victory!")

            when(player) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
        } else if(board.checkForDraw()) {

            Log.w("victoryCheck", "draw!")

            currentGameState = GameState.DRAW
        }

    }
}