package homework.homework1.task1

private const val WINDOWS_PROBABILITY_OF_INFECTION = 0.8
private const val MACOS_PROBABILITY_OF_INFECTION = 0.5
private const val LINUX_PROBABILITY_OF_INFECTION = 0.1

enum class OperatingSystem(val probabilityOfInfection: Double) {
    WINDOWS(WINDOWS_PROBABILITY_OF_INFECTION), // The probability is 80%
    MACOS(MACOS_PROBABILITY_OF_INFECTION), // The probability is 50%
    LINUX(LINUX_PROBABILITY_OF_INFECTION) // The probability is 10%
}
