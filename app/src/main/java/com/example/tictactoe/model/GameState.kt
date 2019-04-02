package com.example.tictactoe.model

/**
 * represents the game state, which is either ongoing, a draw or a win on either side
 */
enum class GameState {
    PLAYING, DRAW, CIRCLE_WINS, CROSS_WINS
}