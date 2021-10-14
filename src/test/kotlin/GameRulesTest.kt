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

//    @Test
//    fun `formats guess with spaces`() {
//       assertEquals("_e__o", formatGuess(charArrayOf('_', 'e', '_', '_', 'o')))
//    }
}