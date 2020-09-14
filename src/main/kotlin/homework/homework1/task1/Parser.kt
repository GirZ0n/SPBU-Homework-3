package homework.homework1.task1

import java.io.File
import java.util.Scanner

class Parser(file: File) {
    private val scan = Scanner(file)

    fun getNumberOfComputers(): Int {
        val line = scan.nextLine()
        val numberOfComputers = line.toInt()
        require(numberOfComputers > 0) { "The number must be greater than 0. \nProblem line: $line" }
        return numberOfComputers
    }

    fun getMatrix(numberOfComputers: Int): Array<IntArray> {
        val network = Array(numberOfComputers) { IntArray(numberOfComputers) }
        for (i in 0 until numberOfComputers) {
            val line = scan.nextLine()
            network[i] = line.split(' ').map { it.toInt() }.toIntArray()
            for (currentNumber in network[i]) {
                require(currentNumber == 0 || currentNumber == 1) {
                    "The number must be either 0 or 1. \nProblem line: $line"
                }
            }
        }
        return network
    }

    fun getComputersOperatingSystem(numberOfComputers: Int): Array<OperatingSystem> {
        val computersOSNames = Array(numberOfComputers) { scan.nextLine() }
        val computersOSProbabilityOfInfection = Array(numberOfComputers) { 0.0 }
        for (i in 0 until numberOfComputers) {
            val line = scan.nextLine()
            val probability = line.toDouble()
            require(probability in 0.0..1.0) {
                "The probability must be between 0 and 1. \nProblem line: $line"
            }
            computersOSProbabilityOfInfection[i] = probability
        }
        return Array(numberOfComputers) { i ->
            OperatingSystem(computersOSNames[i], computersOSProbabilityOfInfection[i])
        }
    }

    fun getNumberOfViruses(): Int {
        val line = scan.nextLine()
        val numberOfViruses = line.toInt()
        require(numberOfViruses > 0) { "The number must be greater than 0. \nProblem line: $line" }
        return numberOfViruses
    }

    fun getVirusesNames(numberOfViruses: Int): Array<String> {
        return Array(numberOfViruses) { scan.nextLine() }
    }

    fun getInfectedComputersByViruses(numberOfViruses: Int, numberOfComputers: Int): Array<List<Int>> {
        val infectedComputersByViruses = Array(numberOfViruses) { emptyList<Int>() }
        for (i in 0 until numberOfViruses) {
            val line = scan.nextLine()
            val infectedComputers = line.split(' ').map { it.toInt() }.toList()
            for (currentNumber in infectedComputers) {
                require(currentNumber in 1..numberOfComputers) {
                    "The number must be greater than 0 and less or equal than $numberOfComputers. \nProblem line: $line"
                }
            }
            infectedComputersByViruses[i] = infectedComputers
        }
        return infectedComputersByViruses
    }
}
