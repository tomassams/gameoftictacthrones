package com.example.tictactoe.model

import android.util.Log
import kotlin.random.Random

/**
 * represents a computer player and is used to generate new moves based on a given board
 */
class Bot(board: Board, human: Seed, bot: Seed) {

    val cells = board.cells

    val bot: Seed = bot
    val human: Seed = human

    /**
     * generates and returns a random available move on the board.
     * ideal for an "easy" difficulty bot
     */
    fun getRandomMove(): Int {

        var random = Random.nextInt(cells.size)

        while(true) {
            if (cells[random].data == Seed.EMPTY) {
                return random
            } else {
                random = Random.nextInt(cells.size)
            }
        }
    }

    /**
     * calculates and returns the best available move on the board using a minimax algorithm
     * ideal for an "unbeatable" difficulty bot
     */
    fun getBestMove(): Int {

        val bestMove: Array<Int> = minimax(bot, 7)

        return bestMove[1]
    }

    /**
     * an implementation of the minimax algorithm used to decide the best bot move
     *
     * @returns an array with the best score [0] used in the recursive calls, and the best move [1] returned in the end
     */
    fun minimax(player: Seed, depth: Int): Array<Int> {

        val moves = getMoves()

        var bestMove: Int = 0

        var currentScore: Int = 0
        var bestScore: Int = 0

        when(player) {
            bot     -> bestScore = Int.MIN_VALUE
            human   -> bestScore = Int.MAX_VALUE
        }

        if(moves.isEmpty() || depth == 0) {
            bestScore = score()
        } else {

            moves.forEach {
                cells[it].data = player

                if(player == bot) {
                    currentScore = minimax(human, depth -1)[0]
                    if(currentScore > bestScore) {
                        bestScore = currentScore
                        bestMove = it
                    }
                }
                else {
                    currentScore = minimax(bot, depth - 1)[0]
                    if(currentScore < bestScore) {
                        bestScore = currentScore
                        bestMove = it
                    }
                }

                cells[it].data = Seed.EMPTY
            }
        }
        return arrayOf(bestScore, bestMove)

    }

    /**
     * generates a list of available moves to make (empty cells)
     */
    fun getMoves(): MutableList<Int> {
        val moves: MutableList<Int> = mutableListOf()

        // exit if anyone has won already
        if(evaluate(human) || evaluate(bot)) {
            return moves
        }

        for(c in 0..8) {
            if(cells[c].data == Seed.EMPTY) {
                moves.add(c)
            }
        }

        return moves
    }

    /**
     * returns a minimax score based on the board status
     */
    fun score(): Int {
        if(evaluate(human)) {
            return -10
        }
        else if(evaluate(bot)) {
            return 10
        }
        else {
            return 0
        }
    }

    /**
     * evaluate if a player has won based on the board cells
     */
    fun evaluate(player: Seed): Boolean {

        // horizontal
        if(
            (cells[0].data == player && cells[1].data == player && cells[2].data == player) ||
            (cells[3].data == player && cells[4].data == player && cells[5].data == player) ||
            (cells[6].data == player && cells[7].data == player && cells[8].data == player)
        ) {
            return true
        }

        // vertical
        if(
            (cells[0].data == player && cells[3].data == player && cells[6].data == player) ||
            (cells[1].data == player && cells[4].data == player && cells[7].data == player) ||
            (cells[2].data == player && cells[5].data == player && cells[8].data == player)
        ) {
            return true
        }

        // diagonal
        if(
            (cells[0].data == player && cells[4].data == player && cells[8].data == player) ||
            (cells[6].data == player && cells[4].data == player && cells[2].data == player)
        ) {
            return true
        }

        return false

    }
}