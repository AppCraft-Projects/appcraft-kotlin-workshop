package workshop.kotlin._06_expression_statement

fun Int.isOdd() = this % 2 == 1
fun Int.isEven() = this % 2 == 0

fun main(args: Array<String>) {
    val x = 3

    when (x) {
        1 -> print("x == 1")
        2, 3 -> print("x == 2 or x == 3")
        in 4..10 -> print("x is in the range")
        !in 4..10 -> print("x is outside the range")
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }

    when {
        x.isEven() -> print("x is even")
        x.isOdd() -> print("x is odd")
        else -> print("WAT?")
    }
}