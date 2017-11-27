package workshop.kotlin._12_functional_programming

import workshop.java._02_java_interop.Position
import workshop.kotlin._03_data_classes.Size

fun positionToSize(positions: List<Position>) : List<Size> =
        positions.map { Size(it.column, it.row) }

fun flattenPositions(positionGroups: List<List<Position>>): List<Position> =
        positionGroups.flatMap { it }

fun filterOdds(numbers: List<Int>): List<Int> = numbers.filter { it %2 == 0 }

fun sumNumbers(numbers: List<Int>): Int = numbers.fold(0, Int::plus)


fun Size.fetchPositions(): List<Position> = (0 until rows).flatMap { row ->
    (0 until columns).map { column ->
        Position.of(column, row)
    }
}