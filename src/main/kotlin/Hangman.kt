import kotlin.random.Random

val words = listOf("palindrome", "cycle", "banana", "computer", "exciting", "bungee", "scooter", "okra", "skipping")

fun main(args: Array<String>) {
    startGame(Random.Default)
}

fun startGame(random: Random) {
    val wordToGuess = selectWordFrom(words, random)
    displayObsficatedWord(wordToGuess)
    handlePlayersGuesses(wordToGuess)

    // select word to be guessed
    // DO:  prompt user to enter letter
    //      read in users guess
    //      if letter is in word - show letter in correct places
    //      else if letter is wrong - deduct a life
    //  WHILE there are lives left
}

private fun handlePlayersGuesses(wordToGuess: String) {
    //this will be the bit that loops until the game is over
    promptForGuess()
    val playersInput = readInput()
    //todo validate the input better
    val char = playersInput.toCharArray().first()
    val updatedGuess = processGuess(char, wordToGuess, "_".repeat(wordToGuess.length))
    displayWordBeingGuessed(updatedGuess.currentStateOfGuessedWord)
}