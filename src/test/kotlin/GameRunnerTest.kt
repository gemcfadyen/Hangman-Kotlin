import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class GameRunnerTest {

    @Test
    fun `word is selected and player makes a successful guess`() {
        val prompt = tapSystemOut {
            withTextFromSystemIn("t\n")
                .execute {
                    startGame(Random(1))
                }
        }

        assertEquals("_ _ _ _ _ _ _ _\nPlease guess a letter: \n_ _ _ _ _ t _ _\n", prompt)
    }
}