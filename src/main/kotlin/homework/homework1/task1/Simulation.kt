package homework.homework1.task1

import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException
import kotlin.NoSuchElementException

class Simulation {
    private val computers = mutableListOf<Computer>()
    private val viruses = mutableListOf<Virus>()
    private val network = mutableMapOf<Computer, List<Computer>>()

    fun run(numberOfMoves: Int, moveFrequency: Long) {
        for (i in 1..numberOfMoves) {
            spreadViruses()
            println("Move $i:")
            computers.forEach { println(it) }
            Thread.sleep(moveFrequency)
        }
    }

    fun import(file: File): Boolean {
        var isCaughtProblem = false
        try {
            // File parsing
            val parser = Parser(file)
            val numberOfComputers = parser.getNumberOfComputers()
            val matrix = parser.getMatrix(numberOfComputers)
            val computersOperatingSystem = parser.getComputersOperatingSystem(numberOfComputers)
            val numberOfViruses = parser.getNumberOfViruses()
            val virusesNames = parser.getVirusesNames(numberOfViruses)
            val infectedComputersByViruses = parser.getInfectedComputersByViruses(numberOfViruses, numberOfComputers)

            // Simulation initialization
            addViruses(numberOfViruses, virusesNames)
            addComputers(numberOfComputers, numberOfViruses, infectedComputersByViruses, computersOperatingSystem)
            createNetwork(numberOfComputers, matrix)
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

    private fun spreadViruses() {
        for (virus in viruses) {
            val infectedComputers = computers.filter { computer -> computer.isInfectedBy(virus) }
            for (infectedComputer in infectedComputers) {
                network[infectedComputer]?.forEach { computer -> computer.installVirus(virus) }
            }
        }
    }

    private fun addViruses(numberOfViruses: Int, virusesNames: Array<String>) {
        for (i in 1..numberOfViruses) {
            viruses += Virus(virusesNames[i - 1])
        }
    }

    private fun addComputers(
        numberOfComputers: Int,
        numberOfViruses: Int,
        infectedComputersByViruses: Array<List<Int>>,
        computersOperatingSystem: Array<OperatingSystem>
    ) {
        for (indexOfComputer in 1..numberOfComputers) {
            val listOfViruses = mutableListOf<Virus>()
            for (indexOfVirus in 1..numberOfViruses) {
                if (infectedComputersByViruses[indexOfVirus - 1].contains(indexOfComputer)) {
                    listOfViruses.add(viruses[indexOfVirus - 1])
                }
            }
            computers +=
                    Computer("Device №$indexOfComputer", computersOperatingSystem[indexOfComputer - 1], listOfViruses)
        }
    }

    private fun createNetwork(numberOfComputers: Int, matrix: Array<IntArray>) {
        for (i in 0 until numberOfComputers) {
            val connectedComputers = mutableListOf<Computer>()
            for (j in 0 until numberOfComputers) {
                if (matrix[i][j] == 1) {
                    connectedComputers += computers[j]
                }
            }
            network[computers[i]] = connectedComputers
        }
    }
}
