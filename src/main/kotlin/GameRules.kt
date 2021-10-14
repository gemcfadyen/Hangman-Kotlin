data class WordBeingGuessed(
    val containedGuessedLetter: Boolean,
    val currentStateOfGuessedWord: String
)

fun processGuess(letter: Char, wordToGuess: String, currentStateOfWordBeingGuessed: String): WordBeingGuessed {
    return if (wordToGuess.contains(letter)) {
        val updatedWord = substituteLetter(wordToGuess, letter, currentStateOfWordBeingGuessed)
        WordBeingGuessed(true, String(updatedWord))
    } else {
        WordBeingGuessed(false, currentStateOfWordBeingGuessed)
    }
}

private fun substituteLetter(
    wordToGuess: String,
    letter: Char,
    currentStateOfWordBeingGuessed: String
): CharArray {
    val indexOfMatchingPositions = wordToGuess.toCharArray().withIndex().filter { i -> i.value == letter }

    val updatedWord = currentStateOfWordBeingGuessed.toCharArray()
    for (position in indexOfMatchingPositions) {
        updatedWord[position.index] = position.value
    }
    return updatedWord
}
