package photoalbum

import photoalbum.core.*

suspend fun main() {
    val apiClient = ApiClient()
    val consoleManager: ConsoleManager = RealConsoleManager()
    val inputHandler = InputHandler(consoleManager)
    val photoAlbumRunner = PhotoAlbumRunner(apiClient, inputHandler, consoleManager)

    consoleManager.printMessage(
        """
            Welcome! This application allows you to print your favorite photo album information!
            If you're not familiar with what data we're referring to, please navigate to https://jsonplaceholder.typicode.com/photos.
        """.trimIndent()
    )

    while (true) {
        photoAlbumRunner.handleCurrentRun()
    }
}

