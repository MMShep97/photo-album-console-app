package photoalbum

import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import photoalbum.core.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhotoAlbumRunnerTest {
    private val apiClient = mockk<ApiClient>()
    private val inputHandler = mockk<InputHandler>()
    private val consoleManager = FakeConsoleManager()
    private val photoAlbumRunner = PhotoAlbumRunner(
        apiClient,
        inputHandler,
        consoleManager
    )

    private val validInput = "1"

    @BeforeEach
    fun setup() {
        consoleManager.clearOutputs()
    }

    @Test
    fun `given non-empty response, should output to console properly`() {
        val photoAlbumInfoResponse = listOf(
            PhotoAlbumInfo(1, "fake title 1"),
            PhotoAlbumInfo(2, "fake title 2"),
            PhotoAlbumInfo(3, "fake title 3"),
        )

        every { inputHandler.handleUserInput() } returns validInput
        coEvery { apiClient.getPhotoAlbumInfoByAlbumId(validInput) } returns photoAlbumInfoResponse

        runBlocking { photoAlbumRunner.handleCurrentRun() }

        val expectedConsoleOutput = """
Photo ID | Title
----------------
${photoAlbumInfoResponse.joinToString("\n", postfix = "\n") { "[${it.photoId}] ${it.title}" }}
            """.trimIndent()

        assertEquals(expectedConsoleOutput, consoleManager.toString())
    }

    @Test
    fun `given empty response, should output notification to user`() {
        val emptyPhotoAlbumInfoResponse = emptyList<PhotoAlbumInfo>()

        every { inputHandler.handleUserInput() } returns validInput
        coEvery { apiClient.getPhotoAlbumInfoByAlbumId(validInput) } returns emptyPhotoAlbumInfoResponse

        runBlocking { photoAlbumRunner.handleCurrentRun() }

        assertEquals("No matching album information\n", consoleManager.toString())
    }
}