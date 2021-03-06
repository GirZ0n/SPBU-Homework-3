package homework.homework1.task1

class Computer(private val name: String, private val os: OperatingSystem, val viruses: MutableList<Virus>) {
    override fun toString(): String = "$name (${os.name}): " +
            if (viruses.isNotEmpty()) viruses.joinToString(", ") { it.name } else "not infected"

    fun isInfectedBy(virus: Virus) = viruses.contains(virus)

    fun installVirus(virus: Virus, probabilityGenerator: ProbabilityGenerator) {
        val randomNumber = probabilityGenerator.getProbability()
        if (!isInfectedBy(virus) && os.probabilityOfInfection > randomNumber) {
            viruses.add(virus)
        }
    }
}
