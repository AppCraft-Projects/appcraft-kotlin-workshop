# Típus kikövetkeztetés, `Any` és `Nothing`

A Kotlin támogatja a típusok kikövetkeztetését, ami azt jelenti, hogy sok esetben a kontextusból ki tudja deríteni a fordító a típusok megjelölése nélkül is azt, hogy egy-egy referencia milyen típussal rendelkezik. Ez kicsit olyan, mint a gyémántjelölés Java-ban, csak sokkal sokoldalúbb! Nézzük meg a következő példát:

```kotlin
data class TextCharacterBuilder(
        private var character: Char,
        private var foregroundColor: Color,
        private var tags: Set<String>,
        private var customData: Any) {

    fun getCharacter(): Char {
        return character
    }

    fun getForegroundColor(): Color {
        return foregroundColor
    }

    fun getTags(): Set<String> {
        return tags
    }

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

    fun createCopy() : TextCharacterBuilder {
        TODO()
    }
}
```

Az fenti példában több helyen is kikövetkeztethető egy-egy függvény visszatérési értéke, így a kód egyszerűsíthető:

```kotlin
data class TextCharacterBuilder(
        private var character: Char,
        private var foregroundColor: Color,
        private var tags: Set<String>,
        private var customData: Any) {

    fun getCharacter() = character

    fun getForegroundColor() = foregroundColor

    fun getTags() = tags

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

    fun createCopy() : TextCharacterBuilder {
        TODO()
    }
}
```

Ezeken kívül a Kotlin ad számunkra pár hasznos beépített függvényt, amik tipikusan előkerülő problémákat oldanak meg, mint
például az `also`, ami mindig `this`-t ad vissza a kódblokk futtatása után:

```kotlin
data class TextCharacterBuilder(
        private var character: Char,
        private var foregroundColor: Color,
        private var tags: Set<String>,
        private var customData: Any) {

    fun getCharacter() = character

    fun getForegroundColor() = foregroundColor

    fun getTags() = tags

    fun setCharacter(character: Char) = also { this.character = character }

    fun setForegroundColor(foregroundColor: Color) = also { this.foregroundColor = foregroundColor }

    fun setTags(tags: Set<String>) = also { this.tags = tags.toSet() }

    fun createCopy() : TextCharacterBuilder {
        TODO()
    }
}
```

A fenti példa így már lényegesen egyszerűbb.

## Any és Object

Az `Any` típus az `Object` megfelelője Kotlin-ban, viszont csak azokat a függvényeket tartalmazza, amik feltétlenül szükségesek és gyakran használtak:

```kotlin
public open class Any {

    public open operator fun equals(other: Any?): Boolean


    public open fun hashCode(): Int


    public open fun toString(): String
}
```

Az `Object`-hez hasonlóan az `Any` minden osztály őse, így a fenti három függvény minden Kotlin osztályunkban elérhető.

## Mindennek az alja: a `Nothing`

A `Nothing` egy talán szokatlan osztály a Kotlin-ban: minden egyéb osztálynak a gyermeke. Ez azt jelenti, hogy minden
függvényből visszatérhetünk `Nothing` típussal, viszont azt fontos tudnunk, hogy a `Nothing`-nak nem lehet egyetlen
példánya sem, tehát mindig kivételt kell, hogy jelezzen egy függvény, ami `Nothing`-ot ad vissza.
A `Nothing` használatával tudjuk tehát jelezni a nem létezést.

Ez hasznos lehet a `TODO` függvény használatakor, amelyet meghívhatunk bármilyen függvény utolsó sorában (mivel
`Nothing`-ot ad vissza, ami minden típus gyermeke):

```kotlin
fun createCopy() : TextCharacterBuilder {
    TODO("Implement me please!")
}
```

A fenti példa mindig kivételt fog dobni, a megjelölt hibaüzenettel.