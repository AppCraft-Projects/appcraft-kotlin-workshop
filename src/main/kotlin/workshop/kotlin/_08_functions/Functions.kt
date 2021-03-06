package workshop.kotlin._08_functions

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * This example shows how you can use functions which take functions
 * to add additional capabilities.
 *
 * TODO: look at how [run], [also] and [let] works
 */
fun <T> lock(lock: Lock = ReentrantLock(), body: () -> T): T {
    lock.lock()
    try {
        return body()
    }
    finally {
        lock.unlock()
    }
}