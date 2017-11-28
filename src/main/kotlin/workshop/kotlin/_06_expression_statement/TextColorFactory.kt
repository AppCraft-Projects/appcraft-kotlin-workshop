package workshop.kotlin._06_expression_statement

import java.awt.Color
import java.util.regex.Pattern

object TextColorFactory {

    // TODO: simplify this method by using the `if` with a `return` statement
    fun fromString(value: String) =
        if (RGB_COLOR_PATTERN.matcher(value).matches()) {
            val r = Integer.parseInt(value.substring(1, 3), 16)
            val g = Integer.parseInt(value.substring(3, 5), 16)
            val b = Integer.parseInt(value.substring(5, 7), 16)
            Color(r, g, b)
        } else {
            throw IllegalArgumentException("Unknown color definition \"" + value + "\"")
        }

    private val RGB_COLOR_PATTERN = Pattern.compile("#[0-9a-fA-F]{6}")
}