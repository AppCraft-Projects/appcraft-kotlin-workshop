package workshop.kotlin._06_expression_statement

fun main(args: Array<String>) {
    val score = 90 + 25
    println(score)

    val a = 12
    val b = 13

    val max = if (a > b) a else b
    println(max)
}