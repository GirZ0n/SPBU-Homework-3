package homework.homework1.task1

import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException
import java.util.Scanner
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
        var line = ""
        val numberOfComputers: Int
        val network: Array<IntArray>
        val computerOperatingSystem: Array<OperatingSystem>
        val numberOfViruses: Int
        val virusesNames: Array<String>
        val infectedComputersByViruses: Array<List<Int>>
        try {
            val scan = Scanner(file)

            line = scan.nextLine()
            numberOfComputers = getNumberOfComputers(line)

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
            checkNaturalNumber(numberOfViruses)

            virusesNames = Array(numberOfViruses) { "" }
            infectedComputersByViruses = Array(numberOfViruses) { emptyList<Int>() }
            for (i in 0 until numberOfViruses) {
                virusesNames[i] = scan.nextLine()

                line = scan.nextLine()
                val infectedComputers = line.split(' ').map { it.toInt() }.toList()
                for (currentNumber in infectedComputers) {
                    if (currentNumber <= 0 || currentNumber > numberOfComputers) {
                        throw IllegalArgumentException(
                            "The number must be greater than 0 and less or equal than $numberOfComputers"
                        )
                    }
                }
                infectedComputersByViruses[i] = infectedComputers
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
        } catch (exception: FileNotFoundException) {
            println(exception.message)
        }

        return true
    }

    private fun checkNaturalNumber(number: Int) {
        if (number <= 0) {
            throw IllegalArgumentException("The number must be greater than 0")
        }
    }

    private fun getNumberOfComputers(scan: Scanner): Int {
        val line = scan.nextLine()
        val numberOfComputers = line.toInt()
        checkNaturalNumber(numberOfComputers)
        return numberOfComputers
    }

    private fun getNetwork()
}

fun main() {
    val test = Simulation()
    val file = File("./src/main/resources/kotlin/homework/homework1/task1/config.txt")
    test.import(file)
}
