package homework.homework1.task1

import kotlin.random.Random

class Computer(private val id: Int, private val operatingSystem: OperatingSystem) {
    val connectedComputers = emptyList<Computer>().toMutableList()
    val viruses = emptyList<String>().toMutableList()

    fun printStatistics() {
        println(
            when (viruses.isEmpty()) {
                true -> "Device №$id (${operatingSystem.name}): not infected"
                false -> "Device №$id (${operatingSystem.name}): ${viruses.joinToString(", ")}"
            }
        )
    }

    fun isInfectedBy(virusName: String) = viruses.contains(virusName)

    fun installVirus(virusName: String): Boolean {
        val randomNumber = Random.nextDouble(0.0, 1.0)
        if (!isInfectedBy(virusName) && operatingSystem.probabilityOfInfection > randomNumber) {
            viruses.add(virusName)
            return true
        }
        return false
    }
}
