package photoalbum.core

import kotlin.random.Random

interface ConsoleManager {
    fun printMessage(message: String, newline: Boolean = true)
    fun readInput(defaultVal: String = "-1"): String
    fun exitProcess(status: Int): Nothing
}

class RealConsoleManager : ConsoleManager {
    override fun printMessage(message: String, newline: Boolean) = if (newline) println(message) else print(message)
    override fun readInput(defaultVal: String) = readLine() ?: defaultVal
    override fun exitProcess(status: Int): Nothing = kotlin.system.exitProcess(status)
}

class FakeConsoleManager : ConsoleManager {
    private val outputs = mutableListOf<String>()

    override fun printMessage(message: String, newline: Boolean) {
        if (newline) outputs.add("$message\n") else outputs.add(message)
    }

    override fun readInput(defaultVal: String) = Random.nextInt(1, 10).toString()
    override fun exitProcess(status: Int): Nothing = throw Exception("Exited process with status: $status")

    override fun toString(): String = outputs.joinToString("")
    fun clearOutputs() = outputs.clear()
}