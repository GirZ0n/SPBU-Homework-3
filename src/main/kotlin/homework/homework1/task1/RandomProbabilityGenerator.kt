package homework.homework1.task1

import kotlin.random.Random

class RandomProbabilityGenerator : ProbabilityGenerator {
    override fun getProbability(): Double {
        return Random.nextDouble()
    }
}
