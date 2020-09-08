package homework.homework1.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {
    @Test
    fun run_3ComputersInRow_checkComputers_mustWork() {
        val test = Simulation()
        test.import(File("./src/test/resources/kotlin/homework/homework1/task1/3ComputersInRow.txt"))
        test.run(1, 0)
        val isComputer1Infected = test.computers[0].viruses.contains("WannaCry")
        val isComputer3Infected = test.computers[2].viruses.contains("WannaCry")
        assert(isComputer1Infected && !isComputer3Infected)
    }

    @Test
    fun run_3ComputersInRow_checkViruses_mustWork() {
        val test = Simulation()
        test.import(File("./src/test/resources/kotlin/homework/homework1/task1/3ComputersInRow.txt"))
        test.run(1, 0)
        val isComputer1Infected = test.viruses[0].infectedComputers.contains(test.computers[0])
        val isComputer3Infected = test.viruses[0].infectedComputers.contains(test.computers[2])
        assert(isComputer1Infected && !isComputer3Infected)
    }

    @Test
    fun run_4Computers_checkComputers_mustWork() {
        val test = Simulation()
        test.import(File("./src/test/resources/kotlin/homework/homework1/task1/4Computers.txt"))
        test.run(1, 0)
        val isComputer1Infected = test.computers[0].viruses.contains("WannaCry")
        val isComputer3Infected = test.computers[2].viruses.contains("WannaCry")
        val isComputer4Infected = test.computers[3].viruses.contains("WannaCry")
        assert(isComputer1Infected && !isComputer3Infected && !isComputer4Infected)
    }

    @Test
    fun run_4Computers_checkViruses_mustWork() {
        val test = Simulation()
        test.import(File("./src/test/resources/kotlin/homework/homework1/task1/4Computers.txt"))
        test.run(1, 0)
        val isComputer1Infected = test.viruses[0].infectedComputers.contains(test.computers[0])
        val isComputer3Infected = test.viruses[0].infectedComputers.contains(test.computers[2])
        val isComputer4Infected = test.viruses[0].infectedComputers.contains(test.computers[3])
        assert(isComputer1Infected && !isComputer3Infected && !isComputer4Infected)
    }

    @Test
    fun run_5Computers_checkComputers_mustWork() {
        val test = Simulation()
        test.import(File("./src/test/resources/kotlin/homework/homework1/task1/5Computers.txt"))
        test.run(1, 0)
        val isComputer1Infected = test.computers[0].viruses.contains("WannaCry")
        val isComputer3Infected = test.computers[2].viruses.contains("WannaCry")
        val isComputer4Infected = test.computers[3].viruses.contains("WannaCry")
        val isComputer5Infected = test.computers[4].viruses.contains("WannaCry")
        assert(isComputer1Infected && !isComputer3Infected && !isComputer4Infected && !isComputer5Infected)
    }

    @Test
    fun run_5Computers_checkViruses_mustWork() {
        val test = Simulation()
        test.import(File("./src/test/resources/kotlin/homework/homework1/task1/5Computers.txt"))
        test.run(1, 0)
        val isComputer1Infected = test.viruses[0].infectedComputers.contains(test.computers[0])
        val isComputer3Infected = test.viruses[0].infectedComputers.contains(test.computers[2])
        val isComputer4Infected = test.viruses[0].infectedComputers.contains(test.computers[3])
        val isComputer5Infected = test.viruses[0].infectedComputers.contains(test.computers[4])
        assert(isComputer1Infected && !isComputer3Infected && !isComputer4Infected && !isComputer5Infected)
    }
}
