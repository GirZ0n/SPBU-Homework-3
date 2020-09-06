package homework.homework1.task1

import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.NoSuchElementException

class Simulation {
    val computers = emptyList<Computer>().toMutableList()
    val viruses = emptyList<Virus>().toMutableList()

/*    init {
        val comp1 = Computer(1, OperatingSystem.LINUX)
        val comp2 = Computer(2, OperatingSystem.LINUX)
        val comp3 = Computer(3, OperatingSystem.WINDOWS)
        val comp4 = Computer(4, OperatingSystem.MACOS)
        val vir1 = Virus("vir1")
        val vir2 = Virus("vir2")
        comp1.connectedComputers += listOf(comp2, comp3)
        comp2.connectedComputers += comp1
        comp3.connectedComputers += listOf(comp2, comp4)
        comp4.connectedComputers += comp3
        vir1.infectedComputers += comp2
        vir2.infectedComputers += comp2
        comp2.viruses += vir1.name
        comp2.viruses += vir2.name
        computers += listOf(comp1, comp2, comp3, comp4)
        viruses += listOf(vir1, vir2)
    }*/

    fun run(numberOfMoves: Int, moveFrequency: Long) {
        for (i in 1..numberOfMoves) {
            viruses.forEach { it.spread() }
            println("Move №$i:")
            computers.forEach { it.printStatistics() }
            Thread.sleep(moveFrequency)
        }
    }

    fun import(file: File): Boolean {
        if (!file.exists()) {
            print("Can't open the file")
            return false
        }

        val scan = Scanner(file)
        var line = ""
        val numberOfComputers: Int
        val network: Array<IntArray>
        val numberOfViruses: Int
        val computerOperatingSystem: Array<OperatingSystem>
        val virusesNames: Array<String>
        val infectedComputersByViruses: Array<IntArray>
        try {
            line = scan.nextLine()
            numberOfComputers = line.toInt()
            if (!isNaturalNumber(numberOfComputers)) {
                throw IllegalArgumentException("The number must be greater than 0")
            }
            network = Array(numberOfComputers) { IntArray(numberOfComputers) }
            for (i in 0 until numberOfComputers) {
                line = scan.nextLine()
                network[i] = line.split(' ').map { it.toInt() }.toIntArray()
                for (currentNumber in network[i]) {
                    if (currentNumber < 0 || currentNumber > 1) {
                        throw IllegalArgumentException("The number must be either 0 or 1")
                    }
                }
            }
            computerOperatingSystem = Array(numberOfComputers) { OperatingSystem.WINDOWS }
            for (i in 0 until numberOfComputers) {
                line = scan.nextLine()
                computerOperatingSystem[i] = when (line.toLowerCase()) {
                    "windows" -> OperatingSystem.WINDOWS
                    "linux" -> OperatingSystem.LINUX
                    "macos" -> OperatingSystem.MACOS
                    else -> throw IllegalArgumentException("This OS is not supported")
                }
            }
            line = scan.nextLine()
            numberOfViruses = line.toInt()
            if (!isNaturalNumber(numberOfViruses)) {
                throw IllegalArgumentException("The number must be greater than 0")
            }
            virusesNames = Array(numberOfViruses) { "" }
            for (i in 0 until numberOfViruses) {
                virusesNames[i] = scan.nextLine()
            }

            for (i in 0 until numberOfViruses) {
                line = scan.nextLine()
                val numberOfInfectedComputers = line.toInt()
                if (!isNaturalNumber(numberOfInfectedComputers)) {
                    throw IllegalArgumentException("The number must be greater than 0")
                }
                for (j in 0 until numberOfInfectedComputers) {

                }
            }
        } catch (exception: NumberFormatException) {
            println("Expected number")
            println("Problem line:")
            println(line)
        } catch (exception: NoSuchElementException) {
            println("No line found")
            println("Last line:")
            println(line)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            println("Problem line:")
            println(line)
        }

        return true
    }

    private fun isNaturalNumber(number: Int) = number > 0
}

fun main() {
    val test = Simulation()
    val file = File("./src/main/resources/kotlin/homework/homework1/task1/config.txt")
    test.import(file)
}
