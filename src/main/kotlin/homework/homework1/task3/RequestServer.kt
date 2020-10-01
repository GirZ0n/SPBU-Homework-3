package homework.homework1.task3

class RequestServer(private val parking: Parking) {
    fun tryToEnter() = parking.tryToEnter()

    fun leave() = parking.leave()
}
