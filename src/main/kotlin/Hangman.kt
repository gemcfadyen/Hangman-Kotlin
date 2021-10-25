import kotlin.random.Random

val dictionary =
    listOf(
        "palindrome",
        "cycle",
        "banana",
        "computer",
        "exciting",
        "bungee",
        "scooter",
        "okra",
        "skipping"
    )

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
//    println(wordToGuess)
    displayObfuscatedWord(wordToGuess)

    val initialGuess = handlePlayersGuesses(wordToGuess, wordToGuess.obfuscate())
    loopGame(
        wordToGuess,
        initialGuess.currentStateOfGuessedWord,
        calculateRemainingLives(Lives.INITIAL_NUMBER_OF_LIVES, initialGuess.containedGuessedLetter)
    )
}

private fun handlePlayersGuesses(wordToGuess: String, stateOfGuess: String): WordBeingGuessed {
    promptForGuess()
    val playersInput = readValidInput()

    val updatedGuess = processGuess(playersInput, wordToGuess, stateOfGuess)
    if (!updatedGuess.containedGuessedLetter) {
        displayIncorrectGuess(playersInput)
    }
    displayWordBeingGuessed(updatedGuess.currentStateOfGuessedWord)

    return updatedGuess
}

private fun loopGame(
    wordToGuess: String,
    currentStateOfGuessedWord: String,
    remainingLives: Int
) {
    displayRemainingLives(remainingLives)
    if (isInProgress(remainingLives, currentStateOfGuessedWord)) {
        val guessResult = handlePlayersGuesses(wordToGuess, currentStateOfGuessedWord)
        val updatedLivesRemaining = calculateRemainingLives(remainingLives, guessResult.containedGuessedLetter)
        loopGame(
            wordToGuess,
            guessResult.currentStateOfGuessedWord,
            updatedLivesRemaining
        )
    } else {
        if (hasWon(currentStateOfGuessedWord)) {
            displayCongratulationsMessage(wordToGuess)
        } else {
            displayGameOver(wordToGuess)
        }
    }
}
