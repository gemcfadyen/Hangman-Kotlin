import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GameRulesTest {
    @Test
    fun `first correct guess is inserted into word`() {
        val output = processGuess('a', "sailor", "______")

        assertTrue(output.containedGuessedLetter)
        assertEquals("_a____", output.currentStateOfGuessedWord)
    }

    @Test
    fun `second correct guess is inserted into word`() {
        val output = processGuess('r', "sailor", "_a____")

        assertTrue(output.containedGuessedLetter)
        assertEquals("_a___r", output.currentStateOfGuessedWord)
    }

    @Test
    fun `guessing a letter that appears multiple times is substituted correctly`() {
        val output = processGuess('a', "banana", "______")

        assertTrue(output.containedGuessedLetter)
        assertEquals("_a_a_a", output.currentStateOfGuessedWord)
    }

    @Test
    fun `guessing an incorrect letter when no letters have been found returns existing guessed state`() {
        val output = processGuess('t', "banana", "______")

        assertFalse(output.containedGuessedLetter)
        assertEquals("______", output.currentStateOfGuessedWord)
    }

    @Test
    fun `guessing a letter that doesn't appear in the word returns the existing guessed state`() {
        val output = processGuess('t', "banana", "_r____")

        assertFalse(output.containedGuessedLetter)
        assertEquals("_r____", output.currentStateOfGuessedWord)
    }

    @Test
    fun `game is in progress whilst there are blanks left to guess in word`() {
        val isInProgress = isInProgress(4, "_ a _ _")
        assertTrue(isInProgress)
    }

    @Test
    fun `game is not in progress if there are no blanks left in the word`() {
        val isInProgress = isInProgress(4, "b a b y")
        assertFalse(isInProgress)
    }

    @Test
    fun `game is not in progress if there are blanks left in the word but there are 0 lives left`() {
        val isInProgress = isInProgress(0, "_ a _ y")
        assertFalse(isInProgress)
    }

    @Test
    fun `number of lives is reduced when an incorrect guess is made`() {
        val numberOfLives = 5
        val isCorrectGuess = false

        val updatedNumberOfLives = calculateRemainingLives(numberOfLives, isCorrectGuess)
        assertEquals(4, updatedNumberOfLives)
    }

    @Test
    fun `number of lives is not changed when a correct guess is made`() {
        val numberOfLives = 5
        val isCorrectGuess = true

        val updatedNumberOfLives = calculateRemainingLives(numberOfLives, isCorrectGuess)
        assertEquals(numberOfLives, updatedNumberOfLives)
    }

    @Test
    fun `player has won when no blanks remaning`() {
        val hasWon = hasWon("baby")
        assertTrue(hasWon)
    }
}