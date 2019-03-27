package com.example.tictactoe.model

import android.util.Log
import com.example.tictactoe.model.Board
import com.example.tictactoe.model.Cell
import com.example.tictactoe.model.GameState
import com.example.tictactoe.model.Seed
import kotlin.random.Random

class GameManager {

    val board: Board = Board()
    val bot: Bot = Bot(board)

    var currentGameState = GameState.PLAYING
    var currentPlayer = Seed.CIRCLE

    fun startGame() {
        board.init()

        currentGameState = GameState.PLAYING
        currentPlayer = Seed.CIRCLE
    }

    fun playMove(player: Seed, cellnum: Int) {

        val cell: Cell = board.cells[cellnum]

        var gameEnded: Boolean = false

        if(cell.data == Seed.EMPTY) {
            cell.data = player

            gameEnded = evaluateGameStatus(player)

            if(!gameEnded) {
                when(player) {
                    Seed.CROSS  -> currentPlayer = Seed.CIRCLE
                    Seed.CIRCLE -> currentPlayer = Seed.CROSS
                }
            }
        }

        var random = Random.nextInt(board.cells.size)
        while(!gameEnded) {
            if(board.cells[random].data == Seed.EMPTY) {
                board.cells[random].data = currentPlayer
                break
            } else {
                random = Random.nextInt(board.cells.size)
            }
        }

        gameEnded = evaluateGameStatus(currentPlayer)
        if(!gameEnded) {
            when(currentPlayer) {
                Seed.CROSS  -> currentPlayer = Seed.CIRCLE
                Seed.CIRCLE -> currentPlayer = Seed.CROSS
            }
        }


    }

    fun evaluateGameStatus(player: Seed): Boolean {
        if(board.checkForVictory(player)) {
            when(player) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
            return true
        }

        if(board.checkForDraw()) {
            currentGameState = GameState.DRAW
            return true
        }

        return false
    }


}