package homework.homework1.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException

internal class ParserTest {
    private val path = "./src/test/resources/kotlin/homework/homework1/task1/parser"

    // getNumberOfComputers

    @Test
    fun getNumberOfComputers_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException::class.java) {
            val parser = Parser(File("$path/incorrectNumberOfComputers.txt"))
            parser.getNumberOfComputers()
        }
    }

    @Test
    fun getNumberOfComputers_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException::class.java) {
            val parser = Parser(File("$path/emptyFile.txt"))
            parser.getNumberOfComputers()
        }
    }

    @Test
    fun getNumberOfComputers_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            val parser = Parser(File("$path/illegalNumberOfComputers.txt"))
            parser.getNumberOfComputers()
        }
    }

    @Test
    fun getNumberOfComputers_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException::class.java) {
            val parser = Parser(File("$path/thisFileDoesNotExist.txt"))
            parser.getNumberOfComputers()
        }
    }

    // getMatrix

    @Test
    fun getMatrix_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException::class.java) {
            val parser = Parser(File("$path/incorrectMatrix.txt"))
            parser.getMatrix(3)
        }
    }

    @Test
    fun getMatrix_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException::class.java) {
            val parser = Parser(File("$path/emptyFile.txt"))
            parser.getMatrix(3)
        }
    }

    @Test
    fun getMatrix_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            val parser = Parser(File("$path/illegalMatrix.txt"))
            parser.getMatrix(3)
        }
    }

    @Test
    fun getMatrix_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException::class.java) {
            val parser = Parser(File("$path/thisFileDoesNotExist.txt"))
            parser.getMatrix(3)
        }
    }

    // getComputersOperatingSystem

    @Test
    fun getComputersOperatingSystem_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException::class.java) {
            val parser = Parser(File("$path/incorrectListOfComputersOperatingSystem.txt"))
            parser.getComputersOperatingSystem(3)
        }
    }

    @Test
    fun getComputersOperatingSystem_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException::class.java) {
            val parser = Parser(File("$path/emptyFile.txt"))
            parser.getComputersOperatingSystem(3)
        }
    }

    @Test
    fun getComputersOperatingSystem_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            val parser = Parser(File("$path/illegalListOfComputersOperatingSystem.txt"))
            parser.getComputersOperatingSystem(3)
        }
    }

    @Test
    fun getComputersOperatingSystem_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException::class.java) {
            val parser = Parser(File("$path/thisFileDoesNotExist.txt"))
            parser.getComputersOperatingSystem(3)
        }
    }

    // getNumberOfViruses

    @Test
    fun getNumberOfViruses_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException::class.java) {
            val parser = Parser(File("$path/incorrectNumberOfViruses.txt"))
            parser.getNumberOfViruses()
        }
    }

    @Test
    fun getNumberOfViruses_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException::class.java) {
            val parser = Parser(File("$path/emptyFile.txt"))
            parser.getNumberOfViruses()
        }
    }

    @Test
    fun getNumberOfViruses_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            val parser = Parser(File("$path/illegalNumberOfViruses.txt"))
            parser.getNumberOfViruses()
        }
    }

    @Test
    fun getNumberOfViruses_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException::class.java) {
            val parser = Parser(File("$path/thisFileDoesNotExist.txt"))
            parser.getNumberOfViruses()
        }
    }

    // getVirusesNames

    @Test
    fun getVirusesNames_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException::class.java) {
            val parser = Parser(File("$path/emptyFile.txt"))
            parser.getVirusesNames(3)
        }
    }

    @Test
    fun getVirusesNames_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException::class.java) {
            val parser = Parser(File("$path/thisFileDoesNotExist.txt"))
            parser.getVirusesNames(3)
        }
    }

    // getInfectedComputersByViruses

    @Test
    fun getInfectedComputersByViruses_ThrowsNumberFormatException() {
        assertThrows(NumberFormatException::class.java) {
            val parser = Parser(File("$path/incorrectListOfInfectedComputersByViruses.txt"))
            parser.getInfectedComputersByViruses(2, 3)
        }
    }

    @Test
    fun getInfectedComputersByViruses_ThrowsNoSuchElementException() {
        assertThrows(NoSuchElementException::class.java) {
            val parser = Parser(File("$path/emptyFile.txt"))
            parser.getInfectedComputersByViruses(2,3)
        }
    }

    @Test
    fun getInfectedComputersByViruses_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            val parser = Parser(File("$path/illegalListOfInfectedComputersByViruses.txt"))
            parser.getInfectedComputersByViruses(2,3)
        }
    }

    @Test
    fun getInfectedComputersByViruses_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException::class.java) {
            val parser = Parser(File("$path/thisFileDoesNotExist.txt"))
            parser.getInfectedComputersByViruses(2, 3)
        }
    }
}
