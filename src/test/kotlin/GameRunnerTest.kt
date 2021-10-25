import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class GameRunnerTest {

    @Test
    fun `word is selected and player guesses all letters successfully`() {
        val prompt = tapSystemOut {
            withTextFromSystemIn("c", "o", "m", "p", "u", "t", "e", "r")
                .execute {
                    startGame(Random(1))
                }
        }

        assertEquals(
            "_ _ _ _ _ _ _ _\n" +
                    "Please guess a letter: \nc _ _ _ _ _ _ _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o _ _ _ _ _ _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o m _ _ _ _ _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o m p _ _ _ _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o m p u _ _ _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o m p u t _ _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o m p u t e _\n" +
                    "You have 6 lives remaining\n\n" +
                    "Please guess a letter: \nc o m p u t e r\n" +
                    "You have 6 lives remaining\n\n" +
                    "Congratulations you won! \uD83C\uDF89 You correctly guessed computer ✨\n", prompt
        )
    }

    @Test
    fun `six incorrect guesses causes game to be over`() {
        val prompt = tapSystemOut {
            withTextFromSystemIn("a", "a", "a", "a", "a", "a")
                .execute {
                    startGame(Random(1))
                }
        }

        assertEquals(
            "_ _ _ _ _ _ _ _\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 5 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 4 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 3 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 2 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 1 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 0 lives remaining\n\n" +
                    "Game Over\nThe word was computer \uD83E\uDD21\n", prompt
        )
    }

    @Test
    fun `correct guesses are substituted even though word is not guessed correctly overall`() {
        val prompt = tapSystemOut {
            withTextFromSystemIn("a", "c", "a", "a", "a", "a", "p", "a")
                .execute {
                    startGame(Random(1))
                }
        }

        assertEquals(
            "_ _ _ _ _ _ _ _\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "_ _ _ _ _ _ _ _\n" +
                    "You have 5 lives remaining\n\n" +
                    "Please guess a letter: \nc _ _ _ _ _ _ _\n" +
                    "You have 5 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "c _ _ _ _ _ _ _\n" +
                    "You have 4 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "c _ _ _ _ _ _ _\n" +
                    "You have 3 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "c _ _ _ _ _ _ _\n" +
                    "You have 2 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "c _ _ _ _ _ _ _\n" +
                    "You have 1 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "c _ _ p _ _ _ _\n" +
                    "You have 1 lives remaining\n\n" +
                    "Please guess a letter: \n" +
                    "There is no `a` in the word\n\n" +
                    "c _ _ p _ _ _ _\n" +
                    "You have 0 lives remaining\n\n" +
                    "Game Over\nThe word was computer \uD83E\uDD21\n", prompt
        )
    }
}