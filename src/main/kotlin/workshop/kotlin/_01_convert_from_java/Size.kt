package workshop.kotlin._01_convert_from_java

import java.util.*

// TODO: copy-paste the java variant of `Size` here
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