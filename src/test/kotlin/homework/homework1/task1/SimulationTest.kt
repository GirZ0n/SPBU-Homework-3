package homework.homework1.task1

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {
    private val path = "./src/test/resources/kotlin/homework/homework1/task1/simulation"

    /*
    Граф:
    1---2---3
    Заражён: 1
    Вероятность заражение везде равна 100%
     */
    @Test
    fun run_3ComputersInRowVer1_OneMove_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/3ComputersInRowVer1.txt"))
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
        simulation.import(File("$path/3ComputersInRowVer1.txt"))
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
        simulation.import(File("$path/3ComputersInRowVer2.txt"))
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
    1---2---3
    Заражён: 1
    Вероятность заражения устройства 1: 0.8
    Вероятность заражения устройства 2: 0.5
    Вероятность заражения устройства 3: 0.2
    */
    @Test
    fun run_3ComputersInRowVer3_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/3ComputersInRowVer3.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройство №3 не заражено
        isInfected = simulation.computers[2].isInfectedBy(virus)
        isProblem = !isProblem && isInfected

        // Устройство №2 проверить нельзя, так как оно может заразиться, а может и нет
        assertFalse(isProblem)
    }

    /*
    Граф:
    1---2---3
    Заражён: 1
    Вероятность заражения устройства 1: 0
    Вероятность заражения устройства 2: 0
    Вероятность заражения устройства 3: 0
    */
    @Test
    fun run_3ComputersInRowVer4_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/3ComputersInRowVer4.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройство №3 не заражено
        for (i in 2..3) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
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
    fun run_4ComputersVer1_OneMove_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/4ComputersVer1.txt"))
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
        simulation.import(File("$path/4ComputersVer1.txt"))
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
        simulation.import(File("$path/4ComputersVer2.txt"))
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
        4
        |
    1---2---3
    Заражён: 1
    Вероятность заражения устройства 1: 0.8
    Вероятность заражения устройства 2: 0.8
    Вероятность заражения устройства 3: 0.5
    Вероятность заражения устройства 4: 0.2
    */
    @Test
    fun run_4ComputersVer3_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/4ComputersVer3.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройства №3 и №4 не заражены
        for (i in 3..4) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
        }

        // Устройство №2 проверить нельзя, так как оно может заразиться, а может и нет
        assertFalse(isProblem)
    }

    /*
    Граф:
        4
        |
    1---2---3
    Заражён: 1
    Вероятность заражения устройства 1: 0
    Вероятность заражения устройства 2: 0
    Вероятность заражения устройства 3: 0
    Вероятность заражения устройства 4: 0
    */
    @Test
    fun run_4ComputersVer4_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/4ComputersVer4.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройства №2, №3 и №4 не заражены
        for (i in 2..4) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
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
    fun run_5ComputersVer1_OneMove_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersVer1.txt"))
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
        simulation.import(File("$path/5ComputersVer1.txt"))
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
        simulation.import(File("$path/5ComputersVer2.txt"))
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
    Заражён: 1
    Вероятность заражения устройства 1: 0.8
    Вероятность заражения устройства 2: 0.8
    Вероятность заражения устройства 3: 0.2
    Вероятность заражения устройства 4: 0.5
    Вероятность заражения устройства 5: 0.2
    */
    @Test
    fun run_5ComputersVer3_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersVer3.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройства №3, №4 и №5 не заражены
        for (i in 3..5) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
        }

        // Устройство №2 проверить нельзя, так как оно может заразиться, а может и нет
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
    Вероятность заражения устройства 1: 0
    Вероятность заражения устройства 2: 0
    Вероятность заражения устройства 3: 0
    Вероятность заражения устройства 4: 0
    Вероятность заражения устройства 5: 0
    */
    @Test
    fun run_5ComputersVer4_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersVer4.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройства №2, №3, №4 и №5 не заражены
        for (i in 2..5) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
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
    fun run_5ComputersInRowVer1_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersInRowVer1.txt"))
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

    /*
    Граф:
    1---2---3---4---5
    Заражён: 1
    Вероятность заражения устройства 1: 0.8
    Вероятность заражения устройства 2: 0.8
    Вероятность заражения устройства 3: 0.5
    Вероятность заражения устройства 4: 0.2
    Вероятность заражения устройства 5: 0.2
    */
    @Test
    fun run_5ComputersInRowVer2_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersInRowVer2.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройства №3, №4 и №5 не заражены
        for (i in 3..5) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
        }

        // Устройство №2 проверить нельзя, так как оно может заразиться, а может и нет
        assertFalse(isProblem)
    }

    /*
    Граф:
    1---2---3---4---5
    Заражён: 1
    Вероятность заражения устройства 1: 0
    Вероятность заражения устройства 2: 0
    Вероятность заражения устройства 3: 0
    Вероятность заражения устройства 4: 0
    Вероятность заражения устройства 5: 0
    */
    @Test
    fun run_5ComputersInRowVer3_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersInRowVer3.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        isProblem = !isProblem && !isInfected

        // Проверяем, что устройства №2, №3, №4 и №5 не заражены
        for (i in 3..5) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            isProblem = !isProblem && isInfected
        }

        assertFalse(isProblem)
    }
}
