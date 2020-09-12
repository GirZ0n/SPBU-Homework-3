package homework.homework1.task1

import java.io.File
import java.lang.IllegalArgumentException
import java.util.Scanner

class Parser(file: File) {
    private val scan = Scanner(file)

    fun getNumberOfComputers(): Int {
        val line = scan.nextLine()
        val numberOfComputers = line.toInt()
        if (numberOfComputers <= 0) {
            throw IllegalArgumentException("The number must be greater than 0. \nProblem line: $line")
        }
        return numberOfComputers
    }

    fun getMatrix(numberOfComputers: Int): Array<IntArray> {
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

    fun getComputersOperatingSystem(numberOfComputers: Int): Array<OperatingSystem> {
        val computersOSNames = Array(numberOfComputers) { "" }
        for (i in 0 until numberOfComputers) {
            computersOSNames[i] = scan.nextLine()
        }
        val computersOSProbabilityOfInfection = Array(numberOfComputers) { 0.0 }
        for (i in 0 until numberOfComputers) {
            val line = scan.nextLine()
            val probability = line.toDouble()
            if (probability < 0.0 || probability > 1.0) {
                throw IllegalArgumentException("The probability must be between 0 and 1. \nProblem line: $line")
            }
            computersOSProbabilityOfInfection[i] = probability
        }
        val computersOS = Array(numberOfComputers) { OperatingSystem("", 0.0) }
        for (i in 0 until numberOfComputers) {
            computersOS[i] = OperatingSystem(computersOSNames[i], computersOSProbabilityOfInfection[i])
        }
        return computersOS
    }

    fun getNumberOfViruses(): Int {
        val line = scan.nextLine()
        val numberOfViruses = line.toInt()
        if (numberOfViruses <= 0) {
            throw IllegalArgumentException("The number must be greater than 0. \nProblem line: $line")
        }
        return numberOfViruses
    }

    fun getVirusesNames(numberOfViruses: Int): Array<String> {
        val virusesNames = Array(numberOfViruses) { "" }
        for (i in 0 until numberOfViruses) {
            virusesNames[i] = scan.nextLine()
        }
        return virusesNames
    }

    fun getInfectedComputersByViruses(numberOfViruses: Int, numberOfComputers: Int): Array<List<Int>> {
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
}
