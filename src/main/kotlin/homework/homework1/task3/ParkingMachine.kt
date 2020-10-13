package homework.homework1.task3

class ParkingMachine(parking: Parking) {
    private val request = RequestServer(parking)

    suspend fun tryToEnter() = request.tryToEnter()

    suspend fun leave() = request.leave()
}
