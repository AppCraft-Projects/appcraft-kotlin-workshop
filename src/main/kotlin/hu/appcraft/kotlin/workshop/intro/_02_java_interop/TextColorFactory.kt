package hu.appcraft.kotlin.workshop.intro._02_java_interop

import java.awt.Color

/**
 * You can check this class in its original version at:
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/color/TextColorFactory.kt
 */
object TextColorFactory {

    // TODO: modify this method to use `@JvmOverloads`
    // TODO: and add a default value (255) for `alpha`
    fun fromRGB(red: Int, green: Int, blue: Int, alpha: Int): Color
            = Color(red, green, blue, alpha)

}