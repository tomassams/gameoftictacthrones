package com.example.tictactoe.model

/**
 * represents a cell on a game board
 */
class Cell {
    var data: Seed = Seed.EMPTY

    fun clear() {
        data = Seed.EMPTY
    }
}