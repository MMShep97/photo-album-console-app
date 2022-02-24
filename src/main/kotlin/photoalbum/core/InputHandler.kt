package photoalbum.core

private val EXIT_OPTIONS = listOf("exit", "quit", "q", ":q", ":qa!")

class InputHandler(private val consoleManager: ConsoleManager) {
    fun handleUserInput(): String {
        consoleManager.printMessage(
            message = "Please supply an album id (or exit with one of the following: $EXIT_OPTIONS): ",
            newline = false
        )
        val userInput = consoleManager.readInput()

        if (userInput in EXIT_OPTIONS) consoleManager.exitProcess(0)
        return userInput
    }
}