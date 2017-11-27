package workshop.kotlin._03_data_classes

import java.util.*

/**
 * You can check this class in its original version at:
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/Size.kt
 */
// TODO: create a `data class` `Size` based on the java Size class
class Size(val columns: Int, val rows: Int) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val size = o as Size?
        return columns == size!!.columns && rows == size.rows
    }

    override fun hashCode(): Int {
        return Objects.hash(columns, rows)
    }

    override fun toString(): String {
        return "Size{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}'
    }
}