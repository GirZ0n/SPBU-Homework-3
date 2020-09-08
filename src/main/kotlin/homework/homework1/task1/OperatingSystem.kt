package homework.homework1.task1

private object ProbabilityOfInfection {
    const val WINDOWS = 0.8 // The probability is 80%
    const val MACOS = 0.5 // The probability is 50%
    const val LINUX = 0.1 // The probability is 10%
}

enum class OperatingSystem(val probabilityOfInfection: Double) {
    WINDOWS(ProbabilityOfInfection.WINDOWS),
    MACOS(ProbabilityOfInfection.MACOS),
    LINUX(ProbabilityOfInfection.LINUX)
}
