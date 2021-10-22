import kotlin.random.Random

val dictionary =
    listOf("palindrome", "cycle", "banana", "computer", "exciting", "bungee", "scooter", "okra", "skipping")

class Lives {
    companion object {
        const val INITIAL_NUMBER_OF_LIVES = 6
    }
}

fun main(args: Array<String>) {
    startGame(Random.Default)
}

fun startGame(random: Random) {
    val wordToGuess = selectWordFrom(dictionary, random)
    displayObfuscatedWord(wordToGuess)

    val initialGuess = handlePlayersGuesses(wordToGuess, wordToGuess.obfuscate())
    loopGame(
        wordToGuess,
        initialGuess.currentStateOfGuessedWord,
        calculateRemainingLives(Lives.INITIAL_NUMBER_OF_LIVES, initialGuess.containedGuessedLetter)
    )

    // select word to be guessed
    // DO:  prompt user to enter letter
    //      read in users guess
    //      if letter is in word - show letter in correct places
    //      else if letter is wrong - deduct a life
    //  WHILE there are lives left
}

private fun handlePlayersGuesses(wordToGuess: String, stateOfGuess: String): WordBeingGuessed {
    promptForGuess()
    val playersInput = readInput()
    //todo validate the input better
    val char = playersInput.toCharArray().first()
    val updatedGuess = processGuess(char, wordToGuess, stateOfGuess)
    displayWordBeingGuessed(updatedGuess.currentStateOfGuessedWord)

    return updatedGuess
}

private fun loopGame(
    wordToGuess: String,
    currentStateOfGuessedWord: String,
    remainingLives: Int
) {
    if (isInProgress(remainingLives, currentStateOfGuessedWord)) {
        val guessResult = handlePlayersGuesses(wordToGuess, currentStateOfGuessedWord)
        loopGame(
            wordToGuess,
            guessResult.currentStateOfGuessedWord,
            calculateRemainingLives(remainingLives, guessResult.containedGuessedLetter)
        )
    } else {
        displayGameOver()
    }
}
