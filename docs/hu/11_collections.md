# Kollekciók

## Bevezető

Kotlinban is megtalálható az összes jól megszokott kollekció:
- Lists
- Arrays (beleértve az ekvivalens "primitív típusokat is")
- Maps
- Sets
- HashMap
- HashSet
- Stb.

## Mutable vs Immutable

Tekintsünk egy sima List deklarációt, ott érdemes **Cmd / Ctrl + klikkel** egy pillantást vetni a forráskódra, a következő szerepel ott. 
```kotlin
/**
 * A generic ordered collection of elements. Methods in this interface support only read-only access to the list;
 * read/write access is supported through the [MutableList] interface.
 * @param E the type of elements contained in the list. The list is covariant on its element type.
 */
```

Csak olvasási hozzáférést ad, ha írni is akarjuk, akkor a `MutableList`-et kell bevezetni. 

Az összes kollekciónak tehát két verziója van, egyaránt biztosít *mutable* és *immutable interface-eket*. Fontos, nem a kollekció maga immutable, csak kínál egy olyan `interface`-t, ahol nincs `add`, `remove` és hasonló mutáló operátorok.

## A Java kollekciókra épít

Másik jó **Cmd / Ctrl + klikkes** cuccot az `ArrayList`-nél látod:
```kotlin
@SinceKotlin("1.1") public typealias ArrayList<E> = java.util.ArrayList<E>
```

Figyeljétek a sor végét, itt egy újabb fontos megállapítás. A Kotlin jellemzően nem definiál saját kollekciókat, a Java alap osztályait egészíti ki új képességekkel. Legyenek azek a fent látott interfacek (mutable / immutable), vagy kiegészítő funckiók.

## Kollekciók létrehozása (listOf, mapOf)

Egy listát egyébként a `listOf()` funckióval szokás létrehozni.
```kotlin
// Számok
val numbers = 1..100 

// Városok
val cities = listOf("London", "Párizs", "Róma")
```

Érdemes egyébként a `listOf()` funckió kódjában is elmerülni (*Cmd / Ctrl + klikk*):
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

De a többi kollekció esetében is megvannak a hasonló kreátor funkciók:
```kotlin
val hashMaps = hashMapOf(Pair("Anglia", London"), Pair("Francia", Párizs"), Pair("Olasz", "Róma"))
 
val booleans = booleanArrayOf(true, false, true)
val characters = charArrayOf('A', 'B', 'C')
```

## Kollekciók kiíratása

Egyszerű, a `println()` kifejezetten okos, rögtön mutatok is néhány példát erre:
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

## Konvertálás kollekció típusok között

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

## Néhány hasznos funkció

Még néhány gyakraasznált funkciót érdemes megismerni:
- `.any()`: *True* értéket ad vissza, ha a kollekciónak legalább egy eleme egyezik a prédikátummal.
- `.all()`: *True* értéket ad vissza, ha a kollekció minden eleme megegyezik a prédikátummal.
- `.count()`: A kollekció elemeinek a számát.
- `.find()`: Az első a prédikátummal egyező elemet adja vissza, vagy null-t, ha nem talál semmit.

```kotlin
val numbers = listOf(-1, 0, 2)
val isZero: (Int) -> Boolean = { it == 0 }
numbers.any(isZero) == true
numbers.all(isZero) == false
numbers.count(isZero) == 1
numbers.find { it > 0 } == 2
```

Innen pedig már nem lesz nehéz a feladat, innen indulunk.
```kotlin
// Return true if all customers are from the given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = TODO()

// Return true if there is at least one customer from the given city
fun Shop.hasCustomerFrom(city: City): Boolean = TODO()

// Return the number of customers from the given city
fun Shop.countCustomersFrom(city: City): Int = TODO()

// Return a customer who lives in the given city, or null if there is none
fun Shop.findAnyCustomerFrom(city: City): Customer? = TODO()
```

Érdemes alaposan elolvasni a kommentekben leírt feladatokat, onnantól simán adni fogja magát, hogy melyiknél és mit válassz.
```kotlin
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = customers.all { it.city == city }

fun Shop.hasCustomerFrom(city: City): Boolean = customers.any { it.city == city }

fun Shop.countCustomersFrom(city: City): Int = customers.count { it.city == city }

fun Shop.findAnyCustomerFrom(city: City): Customer? = customers.find { it.city == city }
```

## Kollekciók legkisebb és legnagyobb eleme

Szintén mindennapi feladat, hogy kiválasszuk egy kollekció legnagyobb vagy épp legkisebb elemét. Ebben segít a következő négy funkció:
- `.min()`: Vissza adja a legkisebb elemet, vagy `null`-t, ha a kollekció üres.
- `.minBy()`: Adott selectornak megfelelően vissza adja a legkisebb elemet, vagy `null`-t, ha a kollekció üres.
- `.max()`: Vissza adja a legnagyobb elemet, vagy `null`-t, ha a kollekció üres.
- `.maxBy()`: Adott selectornak megfelelően vissza adja a legnagyobb elemet, vagy `null`-t, ha a kollekció üres.

A gyakorlatban egyszerűen használhatóak, mutatok is rá két példát.
```kotlin
listOf(1, 42, 4).max() == 42
listOf("a", "ab").minBy { it.length } == "a"
```

Az első sor teljesen egyértelmű, kiválasztja a legnagyobbat a számok között, nem is magyarázom ezt túl. A második esetében az történik viszont, hogy a stringek közül a legrövidebbet választjuk, ami pedig az *"a"*.

Szerintem a .minBy() és a .maxBy() funkciók tényleges megértéséhez nem árt még egy példa. 
```kotlin
listOf(3, 4, 5).maxBy { -it } == -3
listOf(3, 4, 5).minBy { -it } == -5
```

Végig megy a kollekción, a szelektornak megfelelően negálja az összes elem értékét, és az első sor esetében a legnagyobbat választja ki, ami *-3*. A második sor esetében pedig a legkisebbet, ami viszont *-5*.

Jöhet is az aktuális feladatunk:
```kotlin
// Return a customer whose order count is the highest among all customers
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = TODO()

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = TODO()
```

Az első az egyszerűbb, ott mindössze annyival kell tisztában lenni, hogy a listák `.size` tulajdonsága adja vissza a méretét.

A második azonban trükkösebb lehet, itt mindenek előtt a `Product`-ok kollekcióját ki kell nyerni az `orders`-ből, ehhez pedig az előzőleg ismertetett `.flatMap()`-et érdemes használni. 

Gondoljatok csak vissza, mindez Java-ban sokkal masszívabb kód darabot eredményezett volna.

## Kollekciók rendezése

Gyakran használt művelet a különböző kollekciók rendezése is, szerencsére a Kotlin erre frankón kireszelt megoldásokat kínál.

```kotlin
listOf("bbb", "a", "cc").sorted() == listOf("a", "bbb", "cc")
listOf("bbb", "a", "cc").sortedBy { it.length } == listOf("a", "cc", "bbb")
```

Az első esetben a stringek tartalma szerint rendez, a második esetében viszont az elemek hossza alapján.

Ezek alapján lássunk is neki a soron következő feladatnak:
```kotlin
// Return a list of customers, sorted by the ascending number of orders they made
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = TODO()
```

Az egyes `Customer` objektumok `orders` kollekciójának mérete (`size` tulajdonsága) alapján kell végülis rendezni a `.sortBy()`-al. 

```kotlin
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = customers.sortedBy { it.orders.size }
```

Egy sor lesz ismét az egész. 

## Kollekciók összegzése

Kövektező nagyon gyakori művelet, hogy egy kollekció elemeinek bizonyos értékeit, valamilyen szabályosság szerint összegezni kell.

```kotlin
listOf(1, 5, 3).sum() == 9
listOf("a", "b", "cc").sumBy { it.length() } == 4
```

A következők történtek a kollekcióban található értékekkel:
1. Három számot adta össze.
2. Három `String` hosszát adta össze.

Ezek alapján lássunk is neki a soron következő feladatnak:
```kotlin
// Return the sum of prices of all products that a customer has ordered.
// Note: the customer may order the same product for several times.
fun Customer.getTotalOrderPrice(): Double = TODO()
```

Szokásos a történet, az `orders` kollekció `products` elemeinek a `price` értékeit kell összegezni a `.sumByDouble()` funkcióval.

```kotlin
fun Customer.getTotalOrderPrice(): Double = orders.flatMap { it.products }.sumByDouble { it.price }
```

## Kollekciók csoportosítása

Szintén megszokott probléma, hogy a kollekciók elemeit csoportosítani kell valamilyen szempont szerint. Például a vásárlókat, város vagy kor szerint. 

A Kotlin erre is kínál egy nagyon kellemes megoldást a `.groupBy()` használatával. Aki használt már SQL-t, feltételezem a többség igen, annak bizonyosan nagyon ismerős lesz.

Lássuk is a szokásos példánkat:
```kotlin
val result = listOf("a", "b", "ba", "ccc", "ad").groupBy { it.length() }

result == mapOf(1 to listOf("a", "b"), 2 to listOf("ba", "ad"), 3 to listOf("ccc"))
```

Az történik itt, hogy csoportosítsuk a kollekció elemeit a `String`-ek hossza alapján. Az eredmény ennek megfelelően pedig egy `Map` lesz, aminek a kulcsa a *hossz* lesz, az érték pedig a *megfelelő elemek listája*.

Ezek alapján lássunk is neki a soron következő feladatnak:
```kotlin
// Return a map of the customers living in each city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = TODO()
```

A megoldás adja magát, minden `Customer` objektumnak van egy `city` tulajdonsága, aszerint kell csoportosítani a most megtanult új funkcióval.

```kotlin
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = customers.groupBy { it.city }
```
