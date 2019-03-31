package com.example.tictactoe.model

import android.util.Log
import kotlin.random.Random

/**
 * a game manager to handle app interactions with the board
 */
class GameManager {

    val board: Board = Board()
    val bot: Bot = Bot(board)

    var currentGameState = GameState.PLAYING
    var currentPlayer = Seed.CIRCLE
    var currentGameMode = GameMode.SINGLE_PLAYER

    /**
     * starts and initializes the board and game
     */
    fun startGame() {
        board.init()

        currentGameState = GameState.PLAYING
        currentPlayer = Seed.CIRCLE
        currentGameMode = GameMode.SINGLE_PLAYER
    }

    /**
     * play a human initiated move on a cell on the board
     *
     * @params the player making the move and which cell he is attempting to pick
     * @return whether or not a valid move was made and executed
     */
    fun playMove(player: Seed, cellNum: Int): Boolean {

        // shouldn't happen but lets be safe
        if(cellNum < 0 || cellNum > 8) {
            return false
        }

        player

        val cell: Cell = board.cells[cellNum]

        // clicked cell isn't empty, do nothing
        if(cell.data != Seed.EMPTY) {
            return false
        }

        // set the selected seed in selected cell
        cell.data = player

        // check for win or draw
        if(board.checkForVictory(player)) {
            when(player) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
            Log.w("gamestate", "current state is $currentGameState for player move $currentPlayer")
        }
        else if(board.checkForDraw()) {
            currentGameState = GameState.DRAW
            Log.w("gamestate", "current state is $currentGameState for player move $currentPlayer")
        }

        return true

    }

    /**
     * play a computer initiated move on a cell on the board
     *
     * TODO: implement this with an unbeatable algorithm for that sweet "extra hard" AI, e.g. the minimax algorithm
     */
    fun playBotMove() {

        if(currentGameState != GameState.PLAYING) {
            return
        }

        var random = Random.nextInt(board.cells.size)

        while(currentGameState == GameState.PLAYING) {
            if(board.cells[random].data == Seed.EMPTY) {
                board.cells[random].data = currentPlayer
                break
            }
            else {
                random = Random.nextInt(board.cells.size)
            }
        }

        if(board.checkForVictory(currentPlayer)) {
            when(currentPlayer) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
        } else if(board.checkForDraw()) {
            currentGameState = GameState.DRAW
        }

    }


}