# Együttműködés a Java-val

Sokakban felmerülhet a kérdés, hogy mennyire gördülékeny az együttműködés Java és Kotlin kód között? Néhányan úgy vélik,
hogy az együttműködés egyoldalú: Kotlin-ból lehet Java kódot használni, de ez fordítva nem igaz. Ez a feltételezés **hamis**
és az alábbiakban bemutatjuk, hogy hogyan működik a dolog.

## Válaszút

Amikor Kotlin kódot írunk, akkor el kell döntenünk, hogy akarunk-e együttműködni Java kóddal, vagy sem. Ez tipikusan
akkor fontos, ha a program, amin dolgozunk egy függvénykönyvtárnak készül. Egyéb esetben az alábbi dolgok nem számítanak.

## Kétoldalú együttműködés

Amennyiben szeretnénk, hogy Kotlin kódunk Java oldalról is használható legyen meg kell válogatnunk, hogy milyen eszközöket
használunk a Kotlin adta lehetőségek közül

### Reified generics

Amennyiben reified generics-et szeretnénk használni tudnunk kell, hogy az ilyen generikus típusparaméterekkel ellátott
kódok nem lesznek láthatók java oldalról.

### Builderek

Ha buildereket használunk, akkor tudni kell, hogy a klasszikus Kotlin builderek használatával:

```kotlin
class Person private constructor(val name: String, val surname: String) {

    private constructor(builder: Builder) : this(builder.name, builder.surname)

    companion object {
        @JvmStatic
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        lateinit var name: String
        lateinit var surname: String

        fun name(init: Builder.() -> String) = apply { name = init() }

        fun surname(init: Builder.() -> String) = apply { surname = init() }

        fun build() = Person(this)
    }
}
```

kódunk Kotlinból használva szép lesz:

```kotlin
Person.create {
    name = "John"
    surname = "Doe"
}
```

viszont Java oldalról `Unit.INSTANCE`-al kell visszatérnünk:

```java
Person result = Person.create((builder) -> {
    builder.name = "John";
    builder.surname = "Doe";
    return Unit.INSTANCE;
});
```

Ez ízlés kérdése, hogy mennyire elfogadható. A Kotlin builder-eknek van alternatívájuk, ami nem jár több kód írásával,
viszont könnyen felhasználható Java oldalról:

```kotlin
data class PersonWithClassicBuilder(val name: String, val surname: String) {
    
    class Builder(var name: String = "", var surname: String = "") {
        
        fun name(name: String) = also { this.name = name }
        
        fun surname(surname: String) = also { this.surname = surname }
        
        companion object {
            
            @JvmStatic
            fun create() = Builder()
        }
    }
}
```

és a felhasználása mind Kotlinból mind Java oldalról ugyanúgy néz ki:

```java
PersonWithClassicBuilder.Builder.create()
    .name("John")
    .surname("Doe")
    .build();
```

## Együttműködést segítő annotációk

A Kotlin fejlesztői a rendelkezésünkre bocsájtanak pár annotációt, amikkel fájdalommentessé tehető a Kotlin kód használata
Java oldalról ami valódi kölcsönös együttműködést tesz lehetővé:

- A `@JvmStatic` használatával az annotált elem a készülő Java bájtkódban `static` kulcsszóval jelenik meg, így azt statikusan elérhetjük (lsd. fenti példa)
- A `@JvmField` lehetővé teszi számunkra, hogy egy adott Kotlin property field-ként jelenjen meg a bájtkódban. Ez kifejezetten hasznos, ha statikus mezőket akarunk kiajánlani
- A `@JvmOverloads` használatával túltöltött metódusok hozhatók létre. Ezek a Kotlin default paraméterek alapján jönnek létre, így azok használatát teszi lehetővé Java oldalról.

Összességében elmondható, hogy ha figyelünk arra, hogy kódunk használható legyen Java oldalról, akkor a kölcsönös együttműködés
könnyűvé tehető és ennek érdekében csak 1-2 dologra kell odafigyelni Kotlin oldalról.