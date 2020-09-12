package homework.homework1.task1

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {
    /*
    Граф:
    1---2---3
    Заражён: 1
    Вероятность заражение везде равна 100%
     */
    @Test
    fun run_3ComputersInRowVer1_OneMove_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/3ComputersInRowVer1.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1 и №2 заражены
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }
        // Проверяем, что устройство №3 не заражено
        val isInfected = simulation.computers[2].isInfectedBy(virus)
        isProblem = !isProblem && isInfected

        assertFalse(isProblem)
    }

    /*
    Граф:
    1---2---3
    Заражён: 1
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_3ComputersInRowVer1_TwoMoves_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/3ComputersInRowVer1.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2 и №3 заражены
        for (i in 1..3) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
    1---2---3
    Заражён: 2
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_3ComputersInRowVer2_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/3ComputersInRowVer2.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3 заражены
        for (i in 1..3) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
        4
        |
    1---2---3
    Заражён: 1
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_4ComputersVer1_OneMove_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/4ComputersVer1.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1 и №2 заражены
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }
        // Проверяем, что устройства №3 и №4 не заражены
        for (i in 3..4) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
        4
        |
    1---2---3
    Заражён: 1
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_4ComputersVer1_TwoMoves_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/4ComputersVer1.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3 и №4 заражены
        for (i in 1..4) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
        4
        |
    1---2---3
    Заражён: 2
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_4ComputersVer2_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/4ComputersVer2.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3 и №4 заражены
        for (i in 1..4) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
        3
        |
    1---2---4
        |
        5
    Заражён: 1
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_5ComputersVer1_OneMove_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/5ComputersVer1.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1 и №2 заражены
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        // Проверяем, что устройства №3, №4 и №5 не заражены
        for (i in 3..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
        3
        |
    1---2---4
        |
        5
    Заражён: 1
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_5ComputersVer1_TwoMoves_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/5ComputersVer1.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3, №4, №5 заражены
        for (i in 1..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
        3
        |
    1---2---4
        |
        5
    Заражён: 2
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_5ComputersVer2_mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/5ComputersVer2.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3, №4, №5 заражены
        for (i in 1..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }

    /*
    Граф:
    1---2---3---4---5
    Заражён: 1
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_5ComputersInRow__mustWork() {
        val simulation = Simulation()
        simulation.import(File("./src/test/resources/kotlin/homework/homework1/task1/5ComputersInRow.txt"))
        simulation.run(4, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3, №4, №5 заражены
        for (i in 1..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && !isInfected
        }

        assertFalse(isProblem)
    }
}
