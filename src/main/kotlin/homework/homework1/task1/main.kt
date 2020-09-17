package homework.homework1.task1

import java.io.File
import java.lang.NumberFormatException

fun main() {
    val path = "./src/main/resources/kotlin/homework/homework1/task1/config.txt"
    val file = File(path)
    println("Перед началом симуляции, если это необходимо, измените конфигурационные данные")
    println("Путь к конфигурационному файлу: $path")
    println("Рядом с этим файлом находится инструкция по его заполнению")
    println("Когда будете готовы, нажмите 'enter'")
    readLine()
    val networkSimulation = Simulation()
    try {
        networkSimulation.import(file)
    } catch (error: ImportError) {
        println(error.message)
        print(error.innerException.message)
        return
    }
    println("Конфигурационный файл успешно импортирован!")

    var choice = ""
    while (choice != "exit") {
        var numberOfMoves = -1
        while (numberOfMoves == -1) {
            println("Введите количество ходов:")
            try {
                numberOfMoves = readLine()?.toInt() ?: 0
            } catch (exception: NumberFormatException) {
                println("Ожидалось число")
            }
        }

        var frequency: Long = -1
        while (frequency == -1L) {
            println("Введите частоту хода (в миллисекундах):")
            try {
                frequency = readLine()?.toLong() ?: -1
            } catch (exception: NumberFormatException) {
                println("Ожидалось число")
            }
        }

        networkSimulation.run(numberOfMoves, frequency)

        println("Для завершения симуляции введите 'exit', для продолжения нажмите 'enter'")
        choice = readLine() ?: "exit"
    }
}
