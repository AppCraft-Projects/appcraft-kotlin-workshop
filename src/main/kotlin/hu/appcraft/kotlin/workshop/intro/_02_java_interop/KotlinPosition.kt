package hu.appcraft.kotlin.workshop.intro._02_java_interop

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