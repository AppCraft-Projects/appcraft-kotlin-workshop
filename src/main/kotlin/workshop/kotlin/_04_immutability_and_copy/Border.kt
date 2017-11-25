package workshop.kotlin._04_immutability_and_copy

enum class BorderType {
    SOLID, DOTTED, DASHED
}

enum class BorderPosition {
    TOP, RIGHT, BOTTOM, LEFT
}

/**
 * You can check this class in its original version at:
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/modifier/Border.kt
 * and
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/modifier/Border.kt
 */
data class Border internal constructor(val borderType: BorderType,
                                       val borderPositions: Set<BorderPosition>) {

    // TODO: add a method which creates a defensive copy

    companion object {

        // TODO: fix this method to pass the test
        fun of(borderType: BorderType = BorderType.SOLID,
               borderPositions: Set<BorderPosition> = BorderPosition.values().toSet()) =
                Border(borderType, borderPositions)
    }
}