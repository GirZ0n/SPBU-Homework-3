package homework.homework1.task1

import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException
import java.util.Scanner
import kotlin.NoSuchElementException

class Simulation {
    val computers = emptyList<Computer>().toMutableList()
    val viruses = emptyList<Virus>().toMutableList()

    fun run(numberOfMoves: Int, moveFrequency: Long) {
        for (i in 1..numberOfMoves) {
            viruses.forEach { it.spread() }
            println("Move №$i:")
            computers.forEach { it.printStatistics() }
            Thread.sleep(moveFrequency)
        }
    }

    fun import(file: File): Boolean {
        var isCaughtProblem = false
        try {
            // File parsing
            val scan = Scanner(file)
            val numberOfComputers = getNumberOfComputers(scan)
            val network = getNetwork(scan, numberOfComputers)
            val computersOperatingSystem = getComputersOperatingSystem(scan, numberOfComputers)
            val numberOfViruses = getNumberOfViruses(scan)
            val virusesNames = getVirusesNames(scan, numberOfViruses)
            val infectedComputersByViruses = getInfectedComputersByViruses(scan, numberOfViruses, numberOfComputers)

            // Simulation initialization
            for (i in 1..numberOfComputers) {
                computers += Computer(i, computersOperatingSystem[i - 1])
            }

            for (i in 1..numberOfViruses) {
                viruses += Virus(virusesNames[i - 1])
            }

            connectComputers(numberOfComputers, network)
            infectInitialComputers(numberOfViruses, infectedComputersByViruses)
        } catch (exception: NumberFormatException) {
            println("Expected number")
            println(exception.message)
            isCaughtProblem = true
        } catch (exception: NoSuchElementException) {
            println("No line found")
            isCaughtProblem = true
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            isCaughtProblem = true
        } catch (exception: FileNotFoundException) {
            println(exception.message)
            isCaughtProblem = true
        }

        return !isCaughtProblem
    }

    private fun getNumberOfComputers(scan: Scanner): Int {
        val line = scan.nextLine()
        val numberOfComputers = line.toInt()
        if (numberOfComputers <= 0) {
            throw IllegalArgumentException("The number must be greater than 0. \nProblem line: $line")
        }
        return numberOfComputers
    }

    private fun getNetwork(scan: Scanner, numberOfComputers: Int): Array<IntArray> {
        val network = Array(numberOfComputers) { IntArray(numberOfComputers) }
        for (i in 0 until numberOfComputers) {
            val line = scan.nextLine()
            network[i] = line.split(' ').map { it.toInt() }.toIntArray()
            for (currentNumber in network[i]) {
                if (currentNumber < 0 || currentNumber > 1) {
                    throw IllegalArgumentException("The number must be either 0 or 1. \nProblem line: $line")
                }
            }
        }
        return network
    }

    private fun getComputersOperatingSystem(scan: Scanner, numberOfComputers: Int): Array<OperatingSystem> {
        val computersOperatingSystem = Array(numberOfComputers) { OperatingSystem.WINDOWS }
        for (i in 0 until numberOfComputers) {
            val line = scan.nextLine()
            computersOperatingSystem[i] = when (line.toLowerCase()) {
                "windows" -> OperatingSystem.WINDOWS
                "linux" -> OperatingSystem.LINUX
                "macos" -> OperatingSystem.MACOS
                else -> throw IllegalArgumentException("This OS is not supported. \nProblem line: $line")
            }
        }
        return computersOperatingSystem
    }

    private fun getNumberOfViruses(scan: Scanner): Int {
        val line = scan.nextLine()
        val numberOfViruses = line.toInt()
        if (numberOfViruses <= 0) {
            throw IllegalArgumentException("The number must be greater than 0. \nProblem line: $line")
        }
        return numberOfViruses
    }

    private fun getVirusesNames(scan: Scanner, numberOfViruses: Int): Array<String> {
        val virusesNames = Array(numberOfViruses) { "" }
        for (i in 0 until numberOfViruses) {
            virusesNames[i] = scan.nextLine()
        }
        return virusesNames
    }

    private fun getInfectedComputersByViruses(
        scan: Scanner,
        numberOfViruses: Int,
        numberOfComputers: Int
    ): Array<List<Int>> {
        val infectedComputersByViruses = Array(numberOfViruses) { emptyList<Int>() }
        for (i in 0 until numberOfViruses) {
            val line = scan.nextLine()
            val infectedComputers = line.split(' ').map { it.toInt() }.toList()
            for (currentNumber in infectedComputers) {
                if (currentNumber <= 0 || currentNumber > numberOfComputers) {
                    throw IllegalArgumentException(
                        "The number must be greater than 0 and less or equal than $numberOfComputers. " +
                                "\nProblem line: $line"
                    )
                }
            }
            infectedComputersByViruses[i] = infectedComputers
        }
        return infectedComputersByViruses
    }

    private fun connectComputers(numberOfComputers: Int, network: Array<IntArray>) {
        for (i in 0 until numberOfComputers) {
            for (j in 0 until numberOfComputers) {
                if (network[i][j] == 1) {
                    computers[i].connectedComputers += computers[j]
                }
            }
        }
    }

    private fun infectInitialComputers(numberOfViruses: Int, infectedComputersByViruses: Array<List<Int>>) {
        for (indexOfVirus in 0 until numberOfViruses) {
            for (indexOfComputer in infectedComputersByViruses[indexOfVirus]) {
                viruses[indexOfVirus].infectedComputers += computers[indexOfComputer - 1]
                computers[indexOfComputer - 1].viruses += viruses[indexOfVirus].name
            }
        }
    }
}
