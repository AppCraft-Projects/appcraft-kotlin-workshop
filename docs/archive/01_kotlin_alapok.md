# Kotlin alapok

Ez a modul bevezető információkkal szolgál a Kotlin használatához. Az alábbiakban részletezni fogjuk
a Java -> Kotlin konverter használatát és pár alepvető eszközt, amit a Kotlin ad nekünk.

## A Java -> Kotlin konverter

Amennyiben a fejlesztéshez IDEA-t használunk, úgy rendelkezésünkre áll a
 [Java -> Kotlin konverter](https://www.jetbrains.com/help/idea/converting-a-java-file-to-kotlin-file.html).
Ennek a használata rendkívül egyszerű:

- Jelöljünk ki egy java osztályt.
- Másoljuk a tartalmát a vágólapra.
- Illesszük be a kívánt Kotlin (`.kt`) fájlba a tartalmat.
- Rövid várakozás után megjelenik a konvertált kód.

> A Kotlin konvertáló nem mindig ad tökéletes eredményt, néha nem forduló kód az eredmény.
 Ebben az esetben próbáljuk meg a Java osztályt függvényenként konvertálni.
 
## A konverter használata (1. koan)

Ha megnézzük az első koan-unkat, akkor az alábbi kódot fogjuk látni:

```kotlin
fun todoTask1(collection: Collection<Int>): Nothing = TODO(
    """
        Task 1.
        Convert the Java method 'task1' of the class 'JavaCode1' into Kotlin.
        In IntelliJ IDEA or Android Studio, you can copy the Java code,
        paste it into the Kotlin file and let IDE convert it.
        Please use automatic conversion for this task only.
    """,
    references = { JavaCode1().task1(collection) })


fun task1(collection: Collection<Int>): String {
    todoTask1(collection)
}
```

A koan-ok használata minden esetben ugyanúgy alakul. Amikor nekilátunk láthatunk egy `todoTask*`
függvényt, amely tájékoztat minket a feladat részleteiről, illetve egy `task*` függvényt, amelyet
ki kell egészítenünk a feladat megoldásához, illetve ki kell törölnünk a hívást a `todoTask*` függvényre.

Most pedig próbáljuk meg kimálsolni az első példában található `JavaCode1` osztályból a `task1` metódust
és írjuk vele felül a Kotlinos `task1` függvényünket.

Az eredmény a következő lesz:

```kotlin
fun task1(collection: Collection<Int>): String {
    val sb = StringBuilder()
    sb.append("{")
    val iterator = collection.iterator()
    while (iterator.hasNext()) {
        val element = iterator.next()
        sb.append(element)
        if (iterator.hasNext()) {
            sb.append(", ")
        }
    }
    sb.append("}")
    return sb.toString()
}
```

## Kulcsszó argumentumok (2. koan)

A Kotlin lehetővé teszi számunkra *kulcsszó argumentumok* használatát. Ez már ismert
lehet más nyelvekből (pl. Python). Használatuk nagyon egyszerű. Nézzük meg az alábbi példát:

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
- Ha fel akarunk készülni arra, hogy az implementáció változni fog (paraméterek sorrendje változik, vagy
új paraméterek kerülnek be)

Most, hogy megismertük a kulcsszó paraméterek használatát, tekintsük meg a koant:

```kotlin
fun todoTask2(): Nothing = TODO(
    """
        Task 2.
        Print out the collection contents surrounded by curly braces using the library function 'joinToString'.
        Specify only 'prefix' and 'postfix' arguments.

        Don't forget to remove the 'todoTask2()' invocation which throws an exception.
    """,
    documentation = doc2(),
    references = { collection: Collection<Int> -> task1(collection); collection.joinToString() })

fun task2(collection: Collection<Int>): String {
    return collection.joinToString()
}
```

A feladat egyszerű: írjuk ki a gyűjtemény tartalmát úgy, hogy kapcsos zárójelek veszik körbe. A megoldás
a következőképpen alakul:

```kotlin
fun task2(collection: Collection<Int>): String {
    return collection.joinToString(prefix = "{", postfix = "}")
}
```

## Alapértelmezett argumentumok (3. koan)

Az *alapértelmezett argumentumok* lehetővé teszik számunkra, hogy a függvényeinkben és az osztályainkban
megadhassunk alapértelmezett értékeket a paramétereiknek.
Ez hasonló a Java-ból már ismert *túltöltésre*, de annál lényegesen könnyebben használható és
kevésebb lehetőséget ad hibákra:

```kotlin
fun foo(param1: String = "bar", param2: Int = 0) {
    // ...
}

// használat:
foo(param1 = "qux")
```

A fenti példában átadjuk a `param1` paramétert kulcsszó paraméterként, és a `param2` megadását elhagyjuk.
Ilyenkor az alapértelmezett érték (`0`) kerül behelyettesítésre függvényhíváskor.

A 3. koan az alábbi feladatot rejti:

```kotlin
fun foo(name: String): String = todoTask3()

fun task3(): String {
    todoTask3()
//    return (foo("a") +
//            foo("b", number = 1) +
//            foo("c", toUpperCase = true) +
//            foo(name = "d", number = 2, toUpperCase = true))
}
```

A megoldáshoz úgy kell átalakítanunk a `foo` függvényt a `JavaCode3` osztály alapján, hogy a kód leforduljon.
Ehhez hozzá kell adnunk a `number` paramétert alapértelmezett `42`-es értékkel és a `toUpperCase` paramétert
alapértelmezett `false` értékkel az alábbi módon:

```kotlin
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String {
    return if(toUpperCase) {
        name.toUpperCase()
    } else {
        name
    } + number
}
``` 

## Lambdák (4. koan)

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

Ebben a koan-ban a feladatunk az, hogy az alábbi függvényt módosítsuk úgy, hogy az `true` értékkel térjen vissza, ha
a paramétere tartalmaz páros számot:

```kotlin
fun task4(collection: Collection<Int>): Boolean = todoTask4(collection)
```

Ha a fejlesztői környezetünkben megnézzük, hogy milyen függvényeket tudunk meghívni a `collection` paraméteren, akkor megtalálhatjuk
az `any` függvényt, melynek a dokumentációjában ezt találhatjuk:

> Returns `true` if at least one element matches the given [predicate].

A mi esetünkben a predikátumot egy lambda formájában adjuk meg:

```kotlin
fun task4(collection: Collection<Int>): Boolean = collection.any { x -> x % 2 == 0 }
```

## String template-k (5. koan)

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

Ennek fényében a következő feladatunk az alábbi módon alakul:

```kotlin
fun task5(): String = """\d{2} $month \d{4}""" // használhatjuk a `month` változót
```

## Adat osztályok (6. koan)

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

A fentiek használatával már könnyedén készíthetünk egy adat osztályt, ami megoldást nyújt a koanunkra:

```kotlin
data class Person(val name: String, val age: Int)

fun task6(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}
```

## Nullozható típusok (7. koan)

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

Ezek fényében a `sendMessageToClient` implementációja így alakul:

```kotlin
fun sendMessageToClient(
        client: Client?, message: String?, mailer: Mailer
) {
    val email = client?.personalInfo?.email
    if (email != null && message != null) {

        mailer.sendMessage(email, message)
    }
}
```

## Kiegészítő funkciók (9. koan)

A Kotlin támogat kiegészítő funkciókat. Ezek gyakorlatilag a dekorátor mintát valósítják meg, de működnek
olyan helyen is, ahol a klasszikus dekorátorok nem (például `final` osztályok).

Nézzünk meg egy Java dekorátort:

```java
public class ListPresenterDecorator<T> extends AbstractList<T> {

    private List<T> list;

    public ListPresenterDecorator(List<T> list) {
        this.list = list;
    }

    public String present() {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
```

Ugyanez Kotlinban kiegészítő funkcióval:

```kotlin
fun <T> List<T>.present() = this.joinToString(", ")
```

Ez a funkció dekorátorként viselkedik minden `List` példányon.

Sok esetben érdemes kiegészítő funkciókat deklarálni olyan műveletek megfogalmazására, melyek az egész rendszerünkben igazak, például:

```kotlin
    fun isPhoneNumber(s: String) = 
        s.length == 7 &&
        s.all {it.isDigit()}
```

helyett

```kotlin
    fun String.isPhoneNumber() = 
        length == 7 &&
        all {it.isDigit()}
```

A feladatunk megoldása tehát a következő lesz:

```kotlin
fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)
```