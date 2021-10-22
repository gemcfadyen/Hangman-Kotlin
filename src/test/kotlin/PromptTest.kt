import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.IOException


internal class PromptTest {
    @Test
    fun `asks the user to enter a guess`() {
        val prompt = tapSystemOut {
            promptForGuess()
        }
        assertEquals("Please guess a letter: \n", prompt)
    }

    @Test
    fun `reads in players guess`() {
        withTextFromSystemIn("A")
            .execute {
                val guess = readInput()
                assertEquals("A", guess)
            }
    }

    @Test
    fun `reports error message to player if exception thrown whilst reading input`() {
        withTextFromSystemIn()
            .andExceptionThrownOnInputEnd(IOException("Error for test"))
            .execute {
                val prompt = tapSystemOut {
                    readInput()
                }
                assertEquals("Sorry, we had a blip!  \uD83D\uDC7B\n", prompt)
            }
    }

    @Test
    fun `defaults guess to empty string when exception thrown whilst reading input`() {
        withTextFromSystemIn()
            .andExceptionThrownOnInputEnd(IOException("Error for test"))
            .execute {
                val guess = readInput()
                assertEquals("", guess)
            }
    }

    @Test
    fun `displays word with some letters populated currently being guessed`() {
        val displayedWord = tapSystemOut {
            displayWordBeingGuessed("_e__o")
        }
        assertEquals("_ e _ _ o\n", displayedWord)
    }

    @Test
    fun `displays obsfiscated word`() {
        val displayedWord = tapSystemOut {
            displayObfuscatedWord("skeleton")
        }
        assertEquals("_ _ _ _ _ _ _ _\n", displayedWord)
    }

    @Test
    fun `displays game over message`() {
        val displayedWord = tapSystemOut {
            displayGameOver()
        }
        assertEquals("Game Over\n", displayedWord)
    }

    @Test
    fun `display congratulations message`() {
        val displayedMessage = tapSystemOut {
            displayCongratulationsMessage("computer")
        }

        assertEquals("Congratulations you won! \uD83C\uDF89 You correctly guessed computer ✨\n", displayedMessage)

    }
}