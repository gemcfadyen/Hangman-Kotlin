// process users guess - if the readinput errors output a reprompt
fun promptForGuess() {
    println("Please guess a letter: ")
}

fun repromptForGuess(input: Char) {
    println("Your input `$input` was invalid.")
    promptForGuess()
}

fun repromptForGuess(input: String) {
    println("Your input `$input` was invalid.")
    promptForGuess()
}

// reader
fun readValidInput(): Char {
    val input = readLine() ?: ""
    return try {
        val character = input.single()
        if (!character.isLetter()) {
            repromptForGuess(character)
            readValidInput()
        }
        character
    } catch (e: Exception) {
        repromptForGuess(input)
        readValidInput()
    }
}

//writer
fun displayWordBeingGuessed(word: String) {
    println(formatWithSpaces(word.toCharArray(), ""))
}

fun displayObfuscatedWord(word: String) {
    displayWordBeingGuessed(word.obfuscate())
}

fun displayGameOver() {
    println("Game Over")
}

fun displayCongratulationsMessage(word: String) {
    println("Congratulations you won! \uD83C\uDF89 You correctly guessed $word ✨")
}

fun formatWithSpaces(guess: CharArray, accumulator: String): String {
    return if (guess.size == 1) {
        "$accumulator${guess[0]}"
    } else {
        val letter = guess[0]
        val tail = guess.drop(1)
        formatWithSpaces(tail.toCharArray(), "$accumulator$letter ")
    }
}

fun String.obfuscate() = "_".repeat(this.length)
