import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class PromptTest {
    @Test
    fun `asks the user to enter a guess`() {
        val prompt = tapSystemOut {
            promptForGuess()
        }
        assertEquals("Please guess a letter: \n", prompt)
    }

    @Test
    fun `reprompts user to enter another guess when a digit is entered`() {
        val prompt = tapSystemOut {
            repromptForGuess('3')
        }
        assertEquals("Your input `3` was invalid.\nPlease guess a letter: \n", prompt)
    }

    @Test
    fun `reprompts user to enter a guess when input is not a character`() {
        val prompt = tapSystemOut {
            repromptForGuess("star")
        }
        assertEquals("Your input `star` was invalid.\nPlease guess a letter: \n", prompt)
    }

    @Test
    fun `reads in players guess`() {
        withTextFromSystemIn("A")
            .execute {
                val guess = readValidInput()
                assertEquals('a', guess)
            }
    }

    @Test
    fun `returns lowercase value of players guess`() {
        withTextFromSystemIn("A")
            .execute {
                val guess = readValidInput()
                assertEquals('a', guess)
            }
    }

    @Test
    fun `re-prompts when a number is input`() {
        val output = tapSystemOut {
            withTextFromSystemIn("1", "a")
                .execute {
                    readValidInput()
                }
        }

        assertEquals("Your input `1` was invalid.\nPlease guess a letter: \n", output)
    }

    @Test
    fun `re-prompts player if a word is input`() {
        val output = tapSystemOut {
            withTextFromSystemIn("cat", "a")
                .execute {
                    readValidInput()
                }
        }

        assertEquals("Your input `cat` was invalid.\nPlease guess a letter: \n", output)
    }

    @Test
    fun `re-prompts player if no input entered`() {
        val output = tapSystemOut {
            withTextFromSystemIn("", "a")
                .execute {
                    readValidInput()
                }
        }

        assertEquals("Your input `` was invalid.\nPlease guess a letter: \n", output)
    }

    @Test
    fun `re-prompts player if a special character is input`() {
        val output = tapSystemOut {
            withTextFromSystemIn("*", "a")
                .execute {
                    readValidInput()
                }
        }

        assertEquals("Your input `*` was invalid.\nPlease guess a letter: \n", output)
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
    fun displayIncorrectGuessMessage() {
        val display = tapSystemOut {
            displayIncorrectGuess('k')
        }
        assertEquals("There is no `k` in the word\n\n", display)

    }

    @Test
    fun `displays game over message`() {
        val displayedWord = tapSystemOut {
            displayGameOver("computer")
        }
        assertEquals("Game Over\nThe word was computer \uD83E\uDD21\n", displayedWord)
    }

    @Test
    fun `display congratulations message`() {
        val displayedMessage = tapSystemOut {
            displayCongratulationsMessage("computer")
        }

        assertEquals("Congratulations you won! \uD83C\uDF89 You correctly guessed computer ✨\n", displayedMessage)
    }

    @Test
    fun `display remaining lives`() {
        val displayedMessage = tapSystemOut {
            displayRemainingLives(4)
        }

        assertEquals("You have 4 lives remaining\n\n", displayedMessage)
    }
}