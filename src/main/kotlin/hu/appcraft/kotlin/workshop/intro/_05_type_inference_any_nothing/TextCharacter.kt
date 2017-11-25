package hu.appcraft.kotlin.workshop.intro._05_type_inference_any_nothing

import java.awt.Color

/**
 * You can check this class in its original version at:
 * https://github.com/Hexworks/zircon/blob/master/src/main/kotlin/org/codetome/zircon/api/builder/TextCharacterBuilder.kt
 */
data class TextCharacterBuilder(
        private var character: Char,
        private var foregroundColor: Color,
        private var tags: Set<String>,
        // TODO: look at the source of `Any` to see how it differs from `Object`
        private var customData: Any) {

    // TODO: convert these methods to a one-liner
    fun getCharacter(): Char {
        return character
    }

    fun getForegroundColor(): Color {
        return foregroundColor
    }

    fun getTags(): Set<String> {
        return tags
    }

    // TODO: simplify these methods using type inference and `also`
    fun setCharacter(character: Char): TextCharacterBuilder {
        this.character = character
        return this
    }

    fun setForegroundColor(foregroundColor: Color): TextCharacterBuilder {
        this.foregroundColor = foregroundColor
        return this
    }

    fun setTags(tags: Set<String>): TextCharacterBuilder {
        this.tags = tags.toSet()
        return this
    }

    // TODO: look at the source of TODO to see why this works
    fun createCopy() : TextCharacterBuilder {
        TODO()
    }
}