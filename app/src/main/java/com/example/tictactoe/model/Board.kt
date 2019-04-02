package com.example.tictactoe.model

/**
 * represents a classic 3x3 tic tac toe board
 */
class Board {

    // not sure if we have any use for the x&y coordinates just yet, but lets go with it
    var cells = arrayOf(
        Cell(), Cell(), Cell(),
        Cell(), Cell(), Cell(),
        Cell(), Cell(), Cell()
    )

    init {
        init()
    }

    /**
     * clear the board on every initialization
     */
    fun init() {
        for(cell in cells) {
            cell.clear()
        }
    }

    /**
     * check if a player has won the game by having 3 consecutive horizontal, vertical or diagonal cells
     */
    fun hasPlayerWon(player: Seed): Boolean {

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

    /**
     * check if the board is in a draw state, which it is if there are no empty cells left
     * AND a victory check has returned false
     */
    fun isDraw(): Boolean {

        // if there are empty cells, there is still moves to be made
        for(cell in cells) {
            if(cell.data == Seed.EMPTY)
                return false
        }

        return true
    }

}