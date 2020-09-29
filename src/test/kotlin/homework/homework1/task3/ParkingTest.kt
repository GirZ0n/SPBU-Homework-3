package homework.homework1.task3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

internal class ParkingTest {

    private fun identifyProblem(value: Boolean, expectedValue: Boolean, isProblem: Boolean): Boolean {
        return isProblem || value != expectedValue
    }

    @Test
    fun parking_OneThread_SimpleTest_MustWork() {
        val parking = Parking(5)
        var isProblem = false
        isProblem = identifyProblem(parking.leave(), false, isProblem) // На парковке машин нет
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 1 машина на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 3 машины на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 1 машина на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 3 машины на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 4 машины на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 5 машин на парковке
        isProblem = identifyProblem(parking.tryToEnter(), false, isProblem) // Нет мест для ещё одной машины
        isProblem = identifyProblem(parking.tryToEnter(), false, isProblem) // Нет мест для ещё одной машины
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 4 машины на парковке
        isProblem = identifyProblem(parking.tryToEnter(), true, isProblem) // 5 машин на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 4 машины на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 3 машины на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // 1 машина на парковке
        isProblem = identifyProblem(parking.leave(), true, isProblem) // Последняя машина выехала
        isProblem = identifyProblem(parking.leave(), false, isProblem) // На парковке нет машин

        assertFalse(isProblem)
    }

    @RepeatedTest(100)
    fun parking_OneThread_MoreCarsArriveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val numberOfCars = numberOfParkingSpaces + (0..10000).random()
        val parking = Parking(numberOfParkingSpaces)
        var numberOfSuccessfulEntries = 0
        repeat(numberOfCars) {
            if (parking.tryToEnter()) {
                numberOfSuccessfulEntries++
            }
        }
        assertEquals(numberOfParkingSpaces, numberOfSuccessfulEntries)
    }

    @RepeatedTest(100)
    fun parking_OneThread_MoreCarsLeaveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val numberOfCars = numberOfParkingSpaces + (0..10000).random()

        // Полностью заполняем парковку
        val parking = Parking(numberOfParkingSpaces)
        repeat(numberOfParkingSpaces) { parking.tryToEnter() }

        var numberOfSuccessfulExists = 0
        repeat(numberOfCars) {
            if (parking.leave()) {
                numberOfSuccessfulExists++
            }
        }
        assertEquals(numberOfParkingSpaces, numberOfSuccessfulExists)
    }

    @RepeatedTest(100)
    fun parking_ManyThreads_MoreCarsArriveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val parking = Parking(numberOfParkingSpaces)
        val numberOfSuccessfulEntries = AtomicInteger(0)

        val arrayOfThreads = Array(100) {
            Thread {
                val numberOfCars = numberOfParkingSpaces + (0..10000).random()
                repeat(numberOfCars) {
                    if (parking.tryToEnter()) {
                        numberOfSuccessfulEntries.incrementAndGet()
                    }
                }
            }
        }

        arrayOfThreads.forEach { it.start() }
        arrayOfThreads.forEach { it.join() }

        assertEquals(numberOfParkingSpaces, numberOfSuccessfulEntries.get())
    }

    @RepeatedTest(100)
    fun parking_ManyThreads_MoreCarsLeaveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()

        val parking = Parking(numberOfParkingSpaces)
        repeat(numberOfParkingSpaces) { parking.tryToEnter() }

        val numberOfSuccessfulExists = AtomicInteger(0)

        val arrayOfThreads = Array(100) {
            Thread {
                val numberOfCars = numberOfParkingSpaces + (0..10000).random()
                repeat(numberOfCars) {
                    if (parking.leave()) {
                        numberOfSuccessfulExists.incrementAndGet()
                    }
                }
            }
        }

        arrayOfThreads.forEach { it.start() }
        arrayOfThreads.forEach { it.join() }

        assertEquals(numberOfParkingSpaces, numberOfSuccessfulExists.get())
    }

    @RepeatedTest(100)
    fun parking_ManyThreads_TryToEnterAndLeave_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val numberOfCars = numberOfParkingSpaces + (0..10000).random()
        val parking = Parking(numberOfParkingSpaces)

        val listOfThreads = mutableListOf<Thread>()
        val numberOfCarsInParking = AtomicInteger(0)
        listOfThreads.add(
                Thread {
                    repeat(numberOfCars) {
                        if (parking.tryToEnter()) {
                            numberOfCarsInParking.incrementAndGet()
                        }
                    }
                })
        listOfThreads.add(
                Thread {
                    repeat(numberOfCars) {
                        if (parking.leave()) {
                            numberOfCarsInParking.decrementAndGet()
                        }
                    }
                })

        listOfThreads.forEach { it.start() }
        listOfThreads.forEach { it.join() }

        assert(numberOfCarsInParking.get() in 0..numberOfParkingSpaces)
    }
}
