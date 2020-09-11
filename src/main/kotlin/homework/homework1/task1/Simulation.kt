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
            val parser = Parser(file)
            val numberOfComputers = parser.getNumberOfComputers()
            val network = parser.getNetwork(numberOfComputers)
            val computersOperatingSystem = parser.getComputersOperatingSystem(numberOfComputers)
            val numberOfViruses = parser.getNumberOfViruses()
            val virusesNames = parser.getVirusesNames(numberOfViruses)
            val infectedComputersByViruses = parser.getInfectedComputersByViruses(numberOfViruses, numberOfComputers)

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
