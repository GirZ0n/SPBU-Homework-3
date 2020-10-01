package homework.homework1.task3

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ParkingMachine(parking: Parking) {
    private val request = RequestServer(parking)

    fun tryToEnter() = request.tryToEnter()

    fun leave() = request.leave()
}