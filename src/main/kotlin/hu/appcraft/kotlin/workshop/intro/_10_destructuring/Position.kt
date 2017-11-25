package hu.appcraft.kotlin.workshop.intro._10_destructuring

import intro._03_data_classes.Size

data class Position(val column: Int,
                    val row: Int) {

    /**
     * Converts this [Position] to a [Size] which
     * represents the area from the top left position (0, 0)
     * to `this` position.
     */
    fun toSize() = Size(column, row)

}