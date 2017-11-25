package hu.appcraft.kotlin.workshop.intro._07_nullability

import java.util.*

class QueueTrouble {

    val queue: Queue<Double> = LinkedList()

    // TODO: fix me to pass the test!
    fun fetchFirst() = queue.peek().toInt()
}