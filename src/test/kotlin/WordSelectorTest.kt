import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class WordSelectorTest {
    private val dictionary = listOf("cowboy", "skeleton", "trapdoor", "ghost", "princess")

    @Test
    fun `selects word for game`() {
        val testRandom = Random(1)
        val wordForGame = selectWordFrom(dictionary, testRandom)

        assertEquals("cowboy", wordForGame)
    }

    @Test
    fun `selects random word within range`() {
        val testRandom: Random = mockk()
        every { testRandom.nextInt(any(), any()) } returns 2

        selectWordFrom(dictionary, testRandom)

        verify { testRandom.nextInt(0, dictionary.size) }
    }
}