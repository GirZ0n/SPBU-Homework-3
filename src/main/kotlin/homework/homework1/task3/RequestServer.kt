package homework.homework1.task3

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch

class RequestServer(private val parking: Parking) {
    fun tryToEnter() = parking.tryToEnter()

    fun leave() = parking.leave()
}
