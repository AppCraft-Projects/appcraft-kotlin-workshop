package hu.appcraft.kotlin.workshop.intro._08_functions

import java.util.concurrent.locks.Lock

/**
 * This example shows how you can use functions which take functions
 * to add additional capabilities.
 *
 * TODO: look at how [run], [also] and [let] works
 */
fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    }
    finally {
        lock.unlock()
    }
}