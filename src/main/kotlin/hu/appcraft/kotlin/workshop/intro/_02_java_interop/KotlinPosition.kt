package hu.appcraft.kotlin.workshop.intro._02_java_interop

/**
 * You can check this class in its original version at:
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/Position.kt
 */
data class KotlinPosition(val column: Int,
                          val row: Int) {


    companion object {

        /**
         * Constant for the top-left corner (0x0)
         */
        @JvmField
        val TOP_LEFT_CORNER = KotlinPosition(0, 0)

    }
}