package homework.homework1.task1

class ImportError(message: String, val innerException: Exception) : Error(message)
