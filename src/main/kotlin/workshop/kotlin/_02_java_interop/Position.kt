package workshop.kotlin._02_java_interop

/**
 * You can check this class in its original version at:
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/Position.kt
 */
data class Position(val column: Int,
                    val row: Int) {


    companion object {

        // TODO: fill in the missing elements from the java `Position`
        // TODO: use @JvmField and @JvmStatic

        /**
         * Constant for the top-left corner (0x0)
         */
        @JvmField
        val TOP_LEFT_CORNER = Position(0, 0)

    }
}