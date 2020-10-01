package homework.homework1.task3

class ParkingMachine(parking: Parking) {
    private val request = RequestServer(parking)

    fun tryToEnter() = request.tryToEnter()

    fun leave() = request.leave()
}
