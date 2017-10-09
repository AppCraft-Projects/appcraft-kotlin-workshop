# Kotlin alapok

Ez a modul bevezető információkkal szolgál a Kotlin használatához. Az alábbiakban részletezni fogjuk a Java -> Kotlin
konverter használatát és pár alepvető eszközt, amit a Kotlin ad nekünk.

## A Java -> Kotlin konverter használata

Amennyiben a fejlesztéshez IDEA-t használunk, úgy rendelkezésünkre áll a [Java -> Kotlin konverter](https://www.jetbrains.com/help/idea/converting-a-java-file-to-kotlin-file.html).
Ennek a használata rendkívül egyszerű:

- Jelöljünk ki egy java osztályt.
- Másoljuk a tartalmát a vágólapra.
- Illesszük be a kívánt Kotlin (`.kt`) fájlba a tartalmat.
- Rövid várakozás után megjelenik a konvertált kód.

> A Kotlin konvertáló nem mindig ad tökéletes eredményt, néha nem forduló kód az eredmény. Ebben az esetben próbáljuk meg a Java osztályt függvényenként konvertálni.

## Kulcsszó argumentumok

A Kotlin lehetővé teszi számunkra *kulcsszó argumentumok* használatát. Ez a jellemző már ismert lehet más nyelvekből (pl. Python).
Használatuk nagyon egyszerű. Nézzük meg az alábbi példát:

```kotlin
// definiálunk egy függvényt két paraméterrel
fun foo(param1: String, param2: Int) {
    // ...
}

// majd meghívjuk a függvényt, de a függvényhívásban a paramétereket a megszokott mód helyett kulcsszavakkal adjuk át:
foo(param2 = 1, param1 = "Qux")
```

A kulcsszó paraméterek hasznosak lehetnek több esetben

- Ha komibnálva használjuk alapértelmezett argumentumokkal (lásd alább)
- Ha egy függvénynek sok paramétere van és szeretnénk látni, hogy melyik paraméter melyik névhez tartozik
- Ha fel akarunk készülni arra, hogy az implementáció változni fog (paraméterek sorrendje változik, vagy új paraméterek kerülnek be)

## Alapértelmezett argumentumok

Az *alapértelmezett argumentumok* lehetővé teszik számunkra, hogy a függvényeinkben és az osztályainkban megadhassunk alapértelmezett értékeket
a paramétereiknek. Ez hasonló a Java-ból már ismert *túltöltésre*, de annál lényegesen könnyebben használható és kevésebb lehetőséget ad hibákra:

```kotlin
fun foo(param1: String = "bar", param2: Int = 0) {
    // ...
}

// használat:
foo(param1 = "qux")
```

A fenti példában átadjuk a `param1` paramétert kulcsszó paraméterként, és a `param2` megadását elhagyjuk.
Ilyenkor az alapértelmezett érték (`0`) kerül behelyettesítésre függvényhíváskor.

## Lambdák

A Kotlin támogatja a *Java 8*-ból már ismert *lambdákat*. Ezek gyakorlatilag névtelen függvények (Java 8 előtt SAM (Single Abstract Method) interfészekkel működött).
Példa:

```kotlin
lateinit val lambda: (number: Int) -> Boolean // típus deklaráció

val lambda = { num: Int -> num % 2 == 1 } // értékadás
```

Egy lambdának lehet több paramétere is, ilyenkor a parmétereket vesszővel választjuk el:

```kotlin
val lambda = { num: Int, otherNum: Int -> num % 2 == 1 }
```

## String template-k

A `String` template-k lehetővé teszik számunkra, hogy változók értékeire:

```kotlin
val number = 5
val string = "The number is $number"
```

Használhatunk függvényeket is egy String template-ben:

```kotlin
fun getNumber() = 5
val string = "The number is ${getNumber()}"
```

Amennyiben egyszerű változók értékeire hivatkozunk, a kapcsos zárójelek elhagyhatók (első példa).

## Adat osztályok

Az adat osztályok használatával lényegesen leegyszerűsíthetjük olyan osztályainkat, melyeket csak adatok hordozására használunk, például:

```java
public static class Address {
    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}
```

A fenti osztály adat osztályként megírva lényegesen leegyszerűsödik:

```kotlin
data class Address(val street: String, city: String)
```

Az adat osztályok az alábbiakat nyújtják:

- `equals`/`hashCode` függvény pár,
- `toString`,
- `copy` függvény másoláshoz
- getterek és setterek

## Nullozható típusok

Kotlinban a típusainkat kétféleképpen hivatkozhatjuk az alapján, hogy az adott változó/paraméter/egyéb lehet-e `null`, vagy sem:

```kotlin
val string: String? // lehet null

val string: String // nem lehet null
```

Érdemes arra törekedni, hogy a lehető legkevesebb helyen jelenhessen meg a `null`. Ahol mégis szükség van rá (például, ha Java kóddal szeretnénk
együttműködni), akkor az alábbi módon tudjuk azt biztonságosan megtenni:

```kotlin
data class Person(val addresses: List<Address>?, val name: String?)

data class Address(val city: String?)

fun getCity(person: Person): String? {
    return person?.addresses?.firstOrNull()?.city
}
```

Illetve használható a `!!` operátor is:

```kotlin
fun getName(person: Person): String {
    return person.name!!
}
```

Ez akkor javasolt, ha `null`ozható referenciával rendelkezünk, de tudjuk, hogy az értéke az adott kontextusban nem lehet `null`.
