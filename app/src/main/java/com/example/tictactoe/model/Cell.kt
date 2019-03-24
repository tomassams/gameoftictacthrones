package com.example.tictactoe.model

class Cell(val r: Int, val c: Int) {
    var data: Seed = Seed.EMPTY

    fun clear() {
        data = Seed.EMPTY
    }
}