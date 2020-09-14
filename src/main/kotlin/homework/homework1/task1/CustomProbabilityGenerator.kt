package homework.homework1.task1

import kotlin.random.Random

class CustomProbabilityGenerator(private val arrayOfProbabilities: DoubleArray) : ProbabilityGenerator {
    private var index = 0

    override fun getProbability(): Double {
        return if (index in arrayOfProbabilities.indices) {
            require(arrayOfProbabilities[index] in 0.0..1.0)
            arrayOfProbabilities[index++]
        } else {
            Random.nextDouble()
        }
    }
}
