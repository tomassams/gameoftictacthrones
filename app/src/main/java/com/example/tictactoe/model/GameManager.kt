package com.example.tictactoe.model

import android.util.Log
import kotlin.random.Random

/**
 * a game manager to handle app interactions with the board
 */
class GameManager {

    val board: Board = Board()

    var currentGameState = GameState.PLAYING
    var currentPlayer = Seed.CIRCLE
    var currentGameMode = GameMode.SINGLE_PLAYER

    val bot: Bot = Bot(board, currentPlayer, opposite(currentPlayer))

    /**
     * starts and initializes the board and game
     */
    fun startGame(mode: GameMode) {
        board.init()

        currentGameState = GameState.PLAYING
        currentPlayer = Seed.CIRCLE
        currentGameMode = mode
    }

    /**
     * play a human initiated move on a cell on the board
     *
     * @params the player making the move and which cell he is attempting to pick
     * @return whether or not a valid move was made and executed
     */
    fun playHumanMove(player: Seed, cellNum: Int): Boolean {

        // shouldn't happen but lets be safe
        if(cellNum < 0 || cellNum > 8) {
            return false
        }

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
        }
        else if(board.checkForDraw()) {
            currentGameState = GameState.DRAW
        }

        return true

    }

    /**
     * play a computer initiated move on a cell on the board
     */
    fun playBotMove() {

        // OPTIMALLY it should probably pick a move somewhat like this:
        // val botMove = bot.getBestMove(board)
        // board.cells[botMove].data = currentPlayer

        // BUT for now, just pick a random empty cell :o)

        if(currentGameState != GameState.PLAYING) {
            return
        }

        //val botMove = bot.getRandomMove()
        val botMove = bot.getBestMove()
        board.cells[botMove].data = currentPlayer

        if(board.checkForVictory(currentPlayer)) {
            when(currentPlayer) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
        } else if(board.checkForDraw()) {
            currentGameState = GameState.DRAW
        }

    }

    fun opposite(seed: Seed): Seed {
        return when(seed) {
            Seed.CIRCLE -> Seed.CROSS
            Seed.CROSS  -> Seed.CIRCLE
            else        -> Seed.EMPTY
        }
    }


}