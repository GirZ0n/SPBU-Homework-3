package homework.homework1.task3

import kotlinx.coroutines.*

class RequestServer(private val parking: Parking) {
    suspend fun tryToEnter(): Boolean {
        return GlobalScope.async {
            parking.tryToEnter()
        }.await()
    }

    suspend fun leave(): Boolean {
        return GlobalScope.async {
            parking.leave()
        }.await()
    }
}
