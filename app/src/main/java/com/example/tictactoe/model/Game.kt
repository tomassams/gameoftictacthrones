package com.example.tictactoe.model

/**
 * a game manager to handle app interactions with the board
 */
class Game {

    val board: Board = Board()

    lateinit var currentGameState: GameState
    lateinit var currentPlayer: Seed
    lateinit var currentGameMode: GameMode

    private lateinit var bot: Bot

    /**
     * starts and initializes the board and game
     */
    fun startGame(mode: GameMode, firstPlayer: Seed) {
        board.init()

        currentGameState = GameState.PLAYING
        currentPlayer = firstPlayer
        currentGameMode = mode

        bot = Bot(board, currentPlayer, opposite(currentPlayer))
    }

    /**
     * play a human initiated move on a cell on the board
     *
     * @params the player making the move and which cell he is attempting to pick
     * @return whether or not a valid move was made and executed
     */
    fun playHumanMove(player: Seed, cellNum: Int): Boolean {

        if(cellNum < 0 || cellNum > 8) return false
        if(board.cells[cellNum].data != Seed.EMPTY) return false

        val cell: Cell = board.cells[cellNum]

        // set the selected seed in selected cell
        cell.data = player

        // check for win or draw
        if(board.hasPlayerWon(player)) {
            when(player) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
        }
        else if(board.isDraw()) {
            currentGameState = GameState.DRAW
        }

        return true
    }

    /**
     * play a computer initiated move on a cell on the board
     */
    fun playBotMove() {
        if(currentGameState != GameState.PLAYING) {
            return
        }

        //val botMove = bot.getRandomMove() // use for "easy" difficulty
        val botMove: Int = bot.getBestMove() // use for "expert" difficulty

        board.cells[botMove].data = currentPlayer

        if(board.hasPlayerWon(currentPlayer)) {
            when(currentPlayer) {
                Seed.CIRCLE     -> currentGameState = GameState.CIRCLE_WINS
                Seed.CROSS      -> currentGameState = GameState.CROSS_WINS
            }
        } else if(board.isDraw()) {
            currentGameState = GameState.DRAW
        }
    }

    /**
     * simple helper to get the opposite player seed
     */
    fun opposite(seed: Seed): Seed {
        return when(seed) {
            Seed.CIRCLE -> Seed.CROSS
            Seed.CROSS  -> Seed.CIRCLE
            else        -> Seed.EMPTY
        }
    }

}