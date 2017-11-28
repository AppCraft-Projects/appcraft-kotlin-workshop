package workshop.kotlin._10_destructuring

fun main(args: Array<String>) {
    data class Position(val column: Int,
                        val row: Int)

    fun twoValuesReturn(): Pair<Int, Int> {
        val position = Position(3, 4)
        val (column, row) = position
        return Pair(column, row)
    }

    val (column, row) = twoValuesReturn()
}