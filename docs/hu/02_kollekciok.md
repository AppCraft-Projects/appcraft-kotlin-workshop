# Kotlin kollekciók

## Bevezető

Kotlin egy példás Standard Libraryval érkezik, számos gyakran alkalmazott funkciót és annotációt foglal magába.

Kifejzetten pici a mérete, mindössze 750KB, így nem növeli meg túlzottan az alkalamzás méretét. Ráadásul az Android fejlesztőkre is gondoltak, a metódusok számát is igyekeztek alacsonyan tartani.

Több stílust is tartalmaz: 
- High-order funkciók, amelyek a funkcionális programozást segítik elő.
- Kiegészítő (extension) funkciókkal bővíti a meglévő Java osztályok képességeit. 

Megvan az összes megszokott kollekció:
- Lists
- Arrays (beleértve az ekvivalens "primitív típusokat is")
- Maps
- Sets
- HashMap
- HashSet
- Stb.

## Deklaráció és type inference

Lássunk is hozzá:
```kotlin
// Sima kollekció deklaráció
val list: List<String> = ArrayList<String>()
```

Elsőre talán nem tűnik fel, de az IDE jelezni fog némi problémát.
```kotlin
// Az ArrayList után nincs szükség a típusra, a type inference megoldja
val list: List<String> = ArrayList()
```


## Mutable vs Immutable

Továbbá érdemes Cmd / Ctrl + klikkel egy pillantást vetni a forráskódra, a következő szerepel ott. 
```kotlin
/**
 * A generic ordered collection of elements. Methods in this interface support only read-only access to the list;
 * read/write access is supported through the [MutableList] interface.
 * @param E the type of elements contained in the list. The list is covariant on its element type.
 */
```

Csak olvasási hozzáférést ad, ha írni is akarjuk, akkor a MutableList-et kell bevezetni. 

Az összes kollekciónak tehát két verziója van, egyaránt biztosít mutable és immutable interface-eket. 

> TODO: Ábrát ide.


## A Java kollekciókra épít

Ez a Cmd / Ctrl + klikk jó cucc, az összes osztálynak a forrása megtekinthető így. Lásd például az ArrayList-et.
```kotlin
@SinceKotlin("1.1") public typealias ArrayList<E> = java.util.ArrayList<E>
```

Figyeljétek a sor végét, itt egy újabb fontos megállapítás. A Kotlin nem definiál saját kollekciókat, a Java alap osztályait egészíti ki új képességekkel. Legyenek azek a fent látott interfacek (mutable / immutable), vagy kiegészítő funckiók.


## Lista létrehozása

Egy listát jellemzően nem a fenti módon szoktunk létrehozni, inkább a `listOf()` funckióval.
```kotlin
// Számok
val numbers = 1..100 

// Városok
val cities = listOf("London", "Párizs", "Róma")
```

Érdemes egyébként a `listOf()` funckió kódjában is elmerülni (Cmd / Ctrl + klikk):
```kotlin
/** Returns a new read-only list of given elements.  The returned list is serializable (JVM). */
public fun <T> listOf(vararg elements: T): List<T> = if (elements.size > 0) elements.asList() else emptyList()
```

Mit is mond ez? Ha több elem van mint 0, akkor adjon vissza egy listát, azonban ellenkezőleg, ha 0, akkor viszont egy üres listát.

Lehetne még mélyebbre is navigálni, de ebből már érthető lesz.

Egyébként ha változtatható listát szeretnél létrehozni, az is nagyon simán megy, mindössze egy mutable prefix kell majd a funkció nevébe.
```kotlin
val mutableCities = mutableListOf("London", "Párizs", "Róma")

mutableCities.add("Budapest")

```


## Listák kiiratása

Egyszerű, a println kifejezetten okos.
```kotlin
val numbers = 1..100 
val cities = listOf("London", "Párizs", "Róma")
val empty = emptyList<String>() // Üres lista

// Kollekciók kiiratása
println(numbers)
println(cities)
println(empty)

// Típusok kiíratása
println(numbers.javaClass)
println(cities.javaClass)
println(empty.javaClass)
```


## Más kollekciókkal sem bonyolultabb dolgozni

Mutatok néhány példát:
```kotlin
val hashMaps = hashMapOf(Pair("Anglia", London"), Pair("Francia", Párizs"), Pair("Olasz", "Róma"))
 
val booleans = booleanArrayOf(true, false, true)
val characters = charArrayOf('A', 'B', 'C')
```


## Koan #13 - Konvertálás kollekció típusok között

Számos olyan funkció van, ami az egyik fajta kollekcióból a másikba alakít át. Közös jellemzőjük, hogy mindegyik a `to` szócskával kezdődik.

Mutatok is két példát:
```kotlin
fun example0(list: List<Int>) {
    list.toSet()

    list.toCollection(HashSet<Int>())
}
```

Részletesebben:
- Az első `List-ből` `Set-et` állít elő.
- A második pedig a `List-ből` `HashSet-et`.

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
// Adja vissza a 
fun Shop.getSetOfCustomers(): Set<Customer> = TODO()
```

A `Customer` osztály és a `customers` egyébként a `Shop.kt` fileban találhatóak.

A megoldás pedig:
```kotlin
fun Shop.getSetOfCustomers(): Set<Customer> = customers.toSet()
```

A funkció visszatérési értéke alapján következtettem ki, hogy a másik oldalon mit adjunk vissza.


## Koan #14 - Filter map

A high order funkcióknak köszönhetően a nyelv funkcionális nyelvi eszközökkel gazdagodott. Ez pedig sokat segít kollekciók bejárásában, illetve szűrésében.

> High order funkció (functor): olyan funkciót jelent, amire következő kettő közül legalább az egyik teljesül:
- Egy vagy több paramétere funkció.
- Visszatérési értékként funkciót ad vissza.

Ezek közül a kettő leggyakrabban használttal fogunk megismerkedni:
- Filter: bejárja a kollekció összes elemét, és azokat adja vissza, amelyek megfelelnek a funkcióban adott feltételnek. Az alábbi példában a 0-nál nagyobbakat.
- Map: bejárja a kollekció összes elemét, és visszaad egy olyan kollekciót, amelyenek minden elemén alkalmazta a funkcióban megadott műveletet.

```kotlin
val numbers = listOf(1, -1, 2)

// Szűrjük ki a 0-nál nagyobbakat
numbers.filter { it > 0 } // == listOf(1, 2)

// Szorozzuk önmagával
numbers.map { it * it } // == listOf(1, 1, 4)
```

Nézzünk is rá a feladatra.
```kotlin
// Return the set of cities the customers are from
fun Shop.getCitiesCustomersAreFrom(): Set<City> = TODO()

// Return a list of the customers who live in the given city
fun Shop.getCustomersFrom(city: City): List<Customer> = TODO()
```

A `City` osztály és a `customers` egyébként a `Shop.kt` fileban találhatóak.

Azt kell észrevenni, hogy a Customereknek van egy City eleme. Utána már nem bonyolult a sztori:
- Az első esetben könnyű visszaadni a városok listáját. 
- A másikban pedig a városra szűrni.

Figyeljetek a visszatérési értékre. Ami egyébként azért is fontos, hogy ne legyen ismétlődő érték.

```kotlin
fun Shop.getCitiesCustomersAreFrom(): Set<City> = customers.map { it.city }.toSet()

fun Shop.getCustomersFrom(city: City): List<Customer> = customers.filter { it.city == city }
```


## Koan #15 - All, Any és a többi prédikátum


## FlatMap


## mapOf, listOf
