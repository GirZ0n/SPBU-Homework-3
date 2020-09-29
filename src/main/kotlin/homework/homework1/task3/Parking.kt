package homework.homework1.task3

import java.util.concurrent.atomic.AtomicInteger

class Parking(private val numberOfParkingSpaces: Int) {
    private val numberOfFreeParkingSpaces = AtomicInteger(numberOfParkingSpaces)

    // Обрабатываем возможный заезд машины:
    // Если есть свободные места, то запускаем машину, возвращая true,
    // иначе не пускаем машину, возвращая false
    fun tryToEnter(): Boolean {
        val previousValue = numberOfFreeParkingSpaces.getAndUpdate { value -> if (value > 0) value - 1 else 0 }
        return previousValue > 0
    }

    // Обрабатываем выезд машины:
    // Если количество свободных мест меньше количества мест на парковке, то всё работает корректно.
    // В этом случае выпускаем машину, возвращая true,
    // иначе что-то пошло не так, поэтому возвращаем false
    fun leave(): Boolean {
        val previousValue = numberOfFreeParkingSpaces.getAndUpdate { value ->
            if (value < numberOfParkingSpaces) value + 1 else numberOfParkingSpaces
        }
        return previousValue < numberOfParkingSpaces
    }
}
