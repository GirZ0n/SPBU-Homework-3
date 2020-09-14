package homework.homework1.task1

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {
    private val path = "./src/test/resources/kotlin/homework/homework1/task1/simulation"

    /*
    Граф:
    1---2---3
    Заражён: 2
    Вероятность заражение везде равна 100%
    */
    @Test
    fun run_3ComputersInRowVer1_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/3ComputersInRowVer1.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3 заражены
        for (i in 1..3) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
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
    fun run_3ComputersInRowVer2_mustWork() {
        // Число 0.3 подобрано таким образом, чтобы устройство 2 обязательно заразилось, а компьютер 3 -- нет
        // 298 - именно столько чисел потребуется для симуляции 100 шагов
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(298) { 0.3 }))
        simulation.import(File("$path/3ComputersInRowVer2.txt"))
        simulation.run(100, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1 и №2 заражены
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

        // Проверяем, что устройство №3 не заражено
        val isInfected = simulation.computers[2].isInfectedBy(virus)
        if (!isProblem && isInfected) {
            isProblem = true
        }

        assertFalse(isProblem)
    }

    @Test
    fun run_3ComputersInRowVer2_try3_mustWork() {
        // "Идеальное" заражение (за наименьшее количество ходов)
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(4) { 0.1 }))
        simulation.import(File("$path/3ComputersInRowVer2.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2 и №3 заражены
        for (i in 1..3) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

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
    fun run_4ComputersVer1_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/4ComputersVer1.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1 и №2 заражены
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }
        // Проверяем, что устройства №3 и №4 не заражены
        for (i in 3..4) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
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
    fun run_4ComputersVer2_try1_mustWork() {
        // Число 0.6 подобрано таким образом, чтобы устройство 2 обязательно заразилось, а устройства 3 и 4 -- нет
        // 397 - именно столько чисел потребуется для симуляции 100 шагов
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(397) { 0.6 }))
        simulation.import(File("$path/4ComputersVer2.txt"))
        simulation.run(100, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 и №2 заражено
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

        // Проверяем, что устройства №3 и №4 не заражены
        for (i in 3..4) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
        }

        assertFalse(isProblem)
    }

    @Test
    fun run_4ComputersVer2_try2_mustWork() {
        // Число 0.3 подобрано таким образом, чтобы устройства 2 и 3 обязательно заразились, а устройство 4 -- нет
        // 495 - именно столько чисел потребуется для симуляции 100 шагов
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(495) { 0.3 }))
        simulation.import(File("$path/4ComputersVer2.txt"))
        simulation.run(100, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1, №2 и №3 заражено
        for (i in 1..3) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

        // Проверяем, что устройство №4 не заражено
        val isInfected = simulation.computers[3].isInfectedBy(virus)
        if (!isProblem && isInfected) {
            isProblem = true
        }

        assertFalse(isProblem)
    }

    @Test
    fun run_4ComputersVer2_try3_mustWork() {
        // "Идеальное" заражение (за наименьшее количество ходов)
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(5) { 0.1 }))
        simulation.import(File("$path/4ComputersVer2.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1, №2, №3 и №4 заражено
        for (i in 1..4) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

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
    fun run_4ComputersVer3_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/4ComputersVer3.txt"))
        simulation.run(1, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройство №1 заражено
        var isInfected = simulation.computers[0].isInfectedBy(virus)
        if (!isProblem && !isInfected) {
            isProblem = true
        }

        // Проверяем, что устройства №2, №3 и №4 не заражены
        for (i in 2..4) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
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
    fun run_5ComputersVer1_mustWork() {
        val simulation = Simulation()
        simulation.import(File("$path/5ComputersVer1.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3, №4, №5 заражены
        for (i in 1..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
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
    Вероятность заражения устройства 3: 0.5
    Вероятность заражения устройства 4: 0.2
    Вероятность заражения устройства 5: 0.2
    */
    @Test
    fun run_5ComputersVer2_try1_mustWork() {
        // Число 0.6 подобрано таким образом, чтобы устройство 2 обязательно заразилось, а устройства 3, 4 и 5 -- нет
        // 496 - именно столько чисел потребуется для симуляции 100 шагов
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(496) { 0.6 }))
        simulation.import(File("$path/5ComputersVer2.txt"))
        simulation.run(100, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1 и №2 заражены
        for (i in 1..2) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

        // Проверяем, что устройства №3, №4 и №5 не заражены
        for (i in 3..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
        }

        assertFalse(isProblem)
    }

    @Test
    fun run_5ComputersVer2_try2_mustWork() {
        // Число 0.3 подобрано таким образом, чтобы устройства 2 и 3 обязательно заразилось, а устройства 4 и 5 -- нет
        // 692 - именно столько чисел потребуется для симуляции 100 шагов
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(594) { 0.3 }))
        simulation.import(File("$path/5ComputersVer2.txt"))
        simulation.run(100, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2 и №3 заражены
        for (i in 1..3) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

        // Проверяем, что устройства №4 и №5 не заражены
        for (i in 4..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
        }

        assertFalse(isProblem)
    }

    @Test
    fun run_5ComputersVer2_try3_mustWork() {
        // "Идеальное" заражение (за наименьшее количество ходов)
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(6) { 0.1 }))
        simulation.import(File("$path/5ComputersVer2.txt"))
        simulation.run(2, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2, №3, №4 и №5 заражены
        for (i in 1..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
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
    Вероятность заражения устройства 1: 0
    Вероятность заражения устройства 2: 0
    Вероятность заражения устройства 3: 0
    Вероятность заражения устройства 4: 0
    Вероятность заражения устройства 5: 0
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
        if (!isProblem && !isInfected) {
            isProblem = true
        }

        // Проверяем, что устройства №2, №3, №4 и №5 не заражены
        for (i in 2..5) {
            isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
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
    Вероятность заражения устройства 5: 0.5
    */
    @Test
    fun run_5ComputersInRowVer1_mustWork() {
        // Число 0.3 подобрано таким образом, чтобы устройства 2 и 3 обязательно заразилось, а устройство 4 нет.
        // Устройство 5 не заразится из-за того, что оно соединено только с устройство 4, которое не может заразится
        val simulation = Simulation(CustomProbabilityGenerator(DoubleArray(10000) { 0.3 }))
        simulation.import(File("$path/5ComputersInRow.txt"))
        simulation.run(100, 0)
        val virus = simulation.viruses[0]
        var isProblem = false

        // Проверяем, что устройства №1, №2 и №3 заражены
        for (i in 1..3) {
            val isInfected = simulation.computers[0].isInfectedBy(virus)
            if (!isProblem && !isInfected) {
                isProblem = true
            }
        }

        // Проверяем, что устройства №4 и №5 не заражены
        for (i in 4..5) {
            val isInfected = simulation.computers[i - 1].isInfectedBy(virus)
            if (!isProblem && isInfected) {
                isProblem = true
            }
        }

        // Устройство №2 проверить нельзя, так как оно может заразиться, а может и нет
        assertFalse(isProblem)
    }
}
