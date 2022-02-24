package photoalbum

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import photoalbum.core.ConsoleManager
import photoalbum.core.InputHandler

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InputHandlerTest {
    private val consoleManager = mockk<ConsoleManager>()
    private val inputHandler = InputHandler(consoleManager)

    @BeforeEach
    fun setup() {
        every { consoleManager.printMessage(any(), any()) } answers { callOriginal() }
    }

//    If you want to be extra
//    @Test
//    fun `should print proper message with exit options`() {
//
//    }

    @Test
    fun `given exit input, should gracefully exit program`() {
        every { consoleManager.readInput() } returns "exit"
        every { consoleManager.exitProcess(0) } throws Exception("exited process")

        assertThrows<Exception> {
            inputHandler.handleUserInput()
        }

        verify(exactly = 1) { consoleManager.exitProcess(0) }
    }

    @Test
    fun `given a good input, should return input`() {
        val expectedInput = "good input"
        every { consoleManager.readInput() } returns expectedInput

        val actualInput = inputHandler.handleUserInput()

        assertEquals(expectedInput, actualInput)
    }

}