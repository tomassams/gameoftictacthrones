package com.example.tictactoe.model

import kotlin.random.Random

/**
 * represents a computer player and is used to generate new moves based on a given board
 */
class Bot(board: Board) {

    val board: Board = board
    var cells = board.cells

    val winningCombinations: Array<Int> = arrayOf(
        0b111000000, 0b000111000, 0b000000111,
        0b100100100, 0b010010010, 0b001001001,
        0b100010001, 0b001010100
    )

    fun getBestMove(): Int {



        return -1
    }

    fun getRandomMove(): Int {

        var random = Random.nextInt(board.cells.size)

        while(true) {
            if (board.cells[random].data == Seed.EMPTY) {
                return random
            } else {
                random = Random.nextInt(board.cells.size)
            }
        }
    }
}