package homework.homework1.task3

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger

internal class ParkingTest {

    private fun identifyProblem(value: Boolean, expectedValue: Boolean, isProblem: Boolean): Boolean {
        return isProblem || value != expectedValue
    }

    @Test
    fun parking_OneParkingMachine_SimpleTest_MustWork() {
        val parkingMachine = ParkingMachine(Parking(5))
        var isProblem = false
        isProblem = identifyProblem(parkingMachine.leave(), false, isProblem) // На парковке машин нет
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 1 машина на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 3 машины на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 1 машина на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 3 машины на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 4 машины на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 5 машин на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), false, isProblem) // Нет мест для ещё одной машины
        isProblem = identifyProblem(parkingMachine.tryToEnter(), false, isProblem) // Нет мест для ещё одной машины
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 4 машины на парковке
        isProblem = identifyProblem(parkingMachine.tryToEnter(), true, isProblem) // 5 машин на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 4 машины на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 3 машины на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 2 машины на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // 1 машина на парковке
        isProblem = identifyProblem(parkingMachine.leave(), true, isProblem) // Последняя машина выехала
        isProblem = identifyProblem(parkingMachine.leave(), false, isProblem) // На парковке нет машин

        assertFalse(isProblem)
    }

    @RepeatedTest(100)
    fun parking_OneParkingMachine_MoreCarsArriveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val numberOfCars = numberOfParkingSpaces + (0..10000).random()
        val parkingMachine = ParkingMachine(Parking(numberOfParkingSpaces))
        var numberOfSuccessfulEntries = 0
        repeat(numberOfCars) {
            if (parkingMachine.tryToEnter()) {
                numberOfSuccessfulEntries++
            }
        }
        assertEquals(numberOfParkingSpaces, numberOfSuccessfulEntries)
    }

    @RepeatedTest(100)
    fun parking_OneParkingMachine_MoreCarsLeaveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val numberOfCars = numberOfParkingSpaces + (0..10000).random()

        // Полностью заполняем парковку
        val parkingMachine = ParkingMachine(Parking(numberOfParkingSpaces))
        repeat(numberOfParkingSpaces) { parkingMachine.tryToEnter() }

        var numberOfSuccessfulExists = 0
        repeat(numberOfCars) {
            if (parkingMachine.leave()) {
                numberOfSuccessfulExists++
            }
        }
        assertEquals(numberOfParkingSpaces, numberOfSuccessfulExists)
    }

    @RepeatedTest(100)
    fun parking_ManyParkingMachines_MoreCarsArriveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val parking = Parking(numberOfParkingSpaces)
        val numberOfSuccessfulEntries = AtomicInteger(0)

        val arrayOfParkingMachines = Array(100) { ParkingMachine(parking) }
        arrayOfParkingMachines.forEach { parkingMachine ->
            runBlocking {
                launch {
                    val numberOfCars = numberOfParkingSpaces + (0..10000).random()
                    repeat(numberOfCars) {
                        if (parkingMachine.tryToEnter()) {
                            numberOfSuccessfulEntries.incrementAndGet()
                        }
                    }
                }
            }
        }

        assertEquals(numberOfParkingSpaces, numberOfSuccessfulEntries.get())
    }

    @RepeatedTest(100)
    fun parking_ManyParkingMachines_MoreCarsLeaveThanNumberOfParkingPlaces_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()

        val parking = Parking(numberOfParkingSpaces)
        val initParkingMachine = ParkingMachine(parking)
        repeat(numberOfParkingSpaces) { initParkingMachine.tryToEnter() }

        val numberOfSuccessfulExists = AtomicInteger(0)

        val arrayOfParkingMachines = Array(100) { ParkingMachine(parking) }
        arrayOfParkingMachines.forEach { parkingMachine ->
            runBlocking {
                launch {
                    val numberOfCars = numberOfParkingSpaces + (0..10000).random()
                    repeat(numberOfCars) {
                        if (parkingMachine.leave()) {
                            numberOfSuccessfulExists.incrementAndGet()
                        }
                    }
                }
            }
        }

        assertEquals(numberOfParkingSpaces, numberOfSuccessfulExists.get())
    }

    @RepeatedTest(100)
    fun parking_TwoParkingMachines_TryToEnterAndLeave_MustWork() {
        val numberOfParkingSpaces = (0..10000).random()
        val numberOfCars = numberOfParkingSpaces + (0..10000).random()
        val parking = Parking(numberOfParkingSpaces)

        val parkingMachine1 = ParkingMachine(parking)
        val parkingMachine2 = ParkingMachine(parking)
        val numberOfCarsInParking = AtomicInteger(0)

        runBlocking {
            launch {
                repeat(numberOfCars) {
                    if (parkingMachine1.tryToEnter()) {
                        numberOfCarsInParking.incrementAndGet()
                    }
                }
            }

            launch {
                repeat(numberOfCars) {
                    if (parkingMachine2.leave()) {
                        numberOfCarsInParking.decrementAndGet()
                    }
                }
            }
        }

        assert(numberOfCarsInParking.get() in 0..numberOfParkingSpaces)
    }
}
