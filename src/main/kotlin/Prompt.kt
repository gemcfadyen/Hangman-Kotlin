// process users guess - if the readinput errors output a reprompt
fun promptForGuess() {
    println("Please guess a letter: ")
}

// reader
fun readInput(): String {
    return try {
        readLine() ?: ""
    } catch (e: Exception) {
        println("Sorry, we had a blip!  \uD83D\uDC7B")
        ""
    }
}

fun displayWordBeingGuessed(word: String) {
    println(formatWithSpaces(word.toCharArray(), ""))
}

fun displayObsficatedWord(word: String) {
   displayWordBeingGuessed("_".repeat(word.length))
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