package workshop.kotlin._09_extension_functions

import java.io.File

/**
 * This is an example how you can implement `map`
 * as an extension function.
 *
 * The built-in variant works for all `Iterable`s. though.
 *
 * TODO: loot at how the [File] extension functions work.
 */
@Suppress("LoopToCallChain")
fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this)
        result.add(transform(item))
    return result
}