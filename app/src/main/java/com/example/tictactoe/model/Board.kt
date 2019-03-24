package com.example.tictactoe.model

class Board {

    var cells = arrayOf(
        Cell(0,0), Cell(0,1), Cell(0,2),
        Cell(1,0), Cell(1,1), Cell(1,2),
        Cell(2,0), Cell(2,1), Cell(2,2)
    )

    init {
        init()
    }

    fun init() {
        for(cell in cells) {
            cell.clear()
        }
    }

    fun checkForVictory(player: Seed): Boolean {

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

    fun checkForDraw(): Boolean {

        // if there are empty cells, there is still moves to be made
        for(cell in cells) {
            if(cell.data == Seed.EMPTY)
                return false
        }

        return true
    }

}