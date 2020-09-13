package homework.homework1.task1

class ImportErrorException(message: String, val innerException: Exception) : Exception(message)
