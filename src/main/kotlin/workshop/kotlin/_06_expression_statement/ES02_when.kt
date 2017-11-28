package workshop.kotlin._06_expression_statement

fun main(args: Array<String>) {
    val x = 3

    when (x) {
        1 -> print("x == 1")
        2, 3 -> print("x == 2 or x == 3")
        // parseInt(s) -> print("s encodes x")
        in 4..10 -> print("x is in the range")
        !in 4..10 -> print("x is outside the range")
        // x.isOdd() -> print("x is odd")
        // x.isEven() -> print("x is even")
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }
}