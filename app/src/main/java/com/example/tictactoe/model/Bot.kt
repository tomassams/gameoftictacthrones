package com.example.tictactoe.model

class Bot(board: Board) {

    var cells = board.cells

    val winningCombinations: Array<Int> = arrayOf(
        0b111000000, 0b000111000, 0b000000111,
        0b100100100, 0b010010010, 0b001001001,
        0b100010001, 0b001010100
    )

}