package homework.homework1.task1

class Virus(val name: String) {
    val infectedComputers = emptyList<Computer>().toMutableList()

    fun spread() {
        val newInfectedComputers = mutableListOf<Computer>()
        for (infectedComputer in infectedComputers) {
            for (computer in infectedComputer.connectedComputers) {
                if (tryToInfect(computer)) {
                    newInfectedComputers += computer
                }
            }
        }
        infectedComputers += newInfectedComputers
    }

    private fun tryToInfect(computer: Computer): Boolean {
        if (!computer.isInfectedBy(name) && computer.installVirus(name)) {
            return true
        }
        return false
    }
}
