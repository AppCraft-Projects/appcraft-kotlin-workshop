# Kotlin kollekciók

## Bevezető

Kotlin egy körültekintően megépített *Standard Libraryval* érkezik, számos gyakran alkalmazott funkciót és annotációt foglal magába.

Kifejzetten pici a mérete, mindössze **750KB**, így nem növeli meg túlzottan az alkalamzás méretét. Ráadásul az Android fejlesztőkre is gondoltak, a metódusok számát is igyekeztek alacsonyan tartani.

Több stílust is tartalmaz: 
- High-order funkciók, amelyek a funkcionális programozást segítik elő.
- Kiegészítő (extension) funkciókkal bővíti a meglévő Java osztályok képességeit. 

Megtalálható az összes jól megszokott kollekció:
- Lists
- Arrays (beleértve az ekvivalens "primitív típusokat is")
- Maps
- Sets
- HashMap
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

Továbbá érdemes *Cmd / Ctrl + klikkel* egy pillantást vetni a forráskódra, a következő szerepel ott. 
```kotlin
/**
 * A generic ordered collection of elements. Methods in this interface support only read-only access to the list;
 * read/write access is supported through the [MutableList] interface.
 * @param E the type of elements contained in the list. The list is covariant on its element type.
 */
```

Csak olvasási hozzáférést ad, ha írni is akarjuk, akkor a `MutableList`-et kell bevezetni. 

Az összes kollekciónak tehát két verziója van, egyaránt biztosít *mutable* és *immutable interface-eket*. 

> TODO: Ábrát ide.

## A Java kollekciókra épít

Ez a Cmd / Ctrl + klikk jó cucc, az összes osztálynak a forrása megtekinthető így. Lásd például az `ArrayList`-et.
```kotlin
@SinceKotlin("1.1") public typealias ArrayList<E> = java.util.ArrayList<E>
```

Figyeljétek a sor végét, itt egy újabb fontos megállapítás. A Kotlin nem definiál saját kollekciókat, a Java alap osztályait egészíti ki új képességekkel. Legyenek azek a fent látott interfacek (mutable / immutable), vagy kiegészítő funckiók.

## Kollekciók létrehozása (listOf, mapOf)

Egy listát jellemzően nem a fenti módon szoktunk létrehozni, inkább a `listOf()` funckióval.
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

## Konvertálás kollekció típusok között (13. koan)

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

## Kollekciók bejárása és szűrése (14. koan)

A high order funkcióknak köszönhetően a nyelv kiterjedt funkcionális nyelvi eszköztárral gazdagodott. Ezek segítenek kollekciók bejárásában, illetve szűrésében, de emellett még számos további hasznos funkciót adnak hozzá.

>**High order funkció**: olyan funkciót jelent, amire következő kettő közül legalább az egyik teljesül:
>- Egy vagy több paramétere funkció.
>- Visszatérési értékként funkciót ad vissza.

Ezek közül a kettő leggyakrabban használttal fogunk megismerkedni:
- `.filter()`: bejárja a kollekció összes elemét, és azokat adja vissza, amelyek megfelelnek a funkcióban adott feltételnek. Az alábbi példában a 0-nál nagyobbakat.
- `.map()`: bejárja a kollekció összes elemét, és visszaad egy olyan kollekciót, amelyenek minden elemén alkalmazta a funkcióban megadott műveletet.

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

Azt kell észrevenni, hogy a `Customer` objektumnak van egy `city` eleme. Ha ez megvan, akkor már nem bonyolult a sztori:
- Az első esetben könnyű visszaadni a városok listáját. 
- A másikban pedig a városra szűrni.

Figyeljetek a visszatérési értékre. Ami egyébként azért is fontos, hogy ne legyen ismétlődő érték.

```kotlin
fun Shop.getCitiesCustomersAreFrom(): Set<City> = customers.map { it.city }.toSet()

fun Shop.getCustomersFrom(city: City): List<Customer> = customers.filter { it.city == city }
```

## Néhány hasznos funkció (15. koan)

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

Innen pedig már nem lesz nehéz a koan, innen indulunk, ugye.
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

## Kollekció hierarchiák kilapítása (16. koan)

Többszörösen egymásba ágyazott kollekciók bejárása és minden nemű manipulálása tud problémás lenni. Ebben segít a `.flatMap()` funkció, amely segít ezeket egy listává lapítani. Mutatok két példát, és rögtön érteni fogjátok.
```kotlin
val result = listOf("abc", "12").flatMap { it.toCharList() }
result == listOf('a', 'b', 'c', '1', '2')
```

Nézzünk rá a feladatra:
```kotlin
// Return all products this customer has ordered
val Customer.orderedProducts: Set<Product> get() {
    TODO()
}

// Return all products that were ordered by at least one customer
val Shop.allOrderedProducts: Set<Product> get() {
    TODO()
}
```

Majd a megoldásra is. Ehhez érdemes tudni két fontos infót, ha ránéztek a Shop.kt filera, akkor látni fogjátok:
- Az `Order` objektumnak van egy `products` eleme.
- A `Customer` objektumnak pedig a `orderedProducts`.

Ezekkel már szépen fogsz tudni dolgozni.

```kotlin
val Customer.orderedProducts: Set<Product> get() {
    return orders.flatMap { it.products }.toSet()
}

val Shop.allOrderedProducts: Set<Product> get() {
    return customers.flatMap { it.orderedProducts }.toSet()
}
```

## Kollekciók legkisebb és legnagyobb eleme (17. koan)

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

Jöhet is az aktuális koanunk:
```kotlin
// Return a customer whose order count is the highest among all customers
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = TODO()

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = TODO()
```

Az első az egyszerűbb, ott mindössze annyival kell tisztában lenni, hogy a listák `.size` tulajdonsága adja vissza a méretét.

A második azonban trükkösebb lehet, itt mindenek előtt a `Product`-ok kollekcióját ki kell nyerni az `orders`-ből, ehhez pedig az előzőleg ismertetett `.flatMap()`-et érdemes használni. 

Gondoljatok csak vissza, mindez Java-ban sokkal masszívabb kód darabot eredményezett volna.

## Kollekciók rendezése (18. koan)

Gyakran használt művelet a különböző kollekciók rendezése is, szerencsére a Kotlin erre frankón kireszelt megoldásokat kínál.

```kotlin
listOf("bbb", "a", "cc").sorted() == listOf("a", "bbb", "cc")
listOf("bbb", "a", "cc").sortedBy { it.length } == listOf("a", "cc", "bbb")
```

Az első esetben a stringek tartalma szerint rendez, a második esetében viszont az elemek hossza alapján.

Ezek alapján lássunk is neki a soron következő koannak:
```kotlin
// Return a list of customers, sorted by the ascending number of orders they made
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = TODO()
```

Az egyes `Customer` objektumok `orders` kollekciójának mérete (`size` tulajdonsága) alapján kell végülis rendezni a `.sortBy()`-al. 

```kotlin
fun Shop.getCustomersSortedByNumberOfOrders(): List<Customer> = customers.sortedBy { it.orders.size }
```

Egy sor lesz ismét az egész. 

## Kollekciók összegzése (19. koan)

Kövektező nagyon gyakori művelet, hogy egy kollekció elemeinek bizonyos értékeit, valamilyen szabályosság szerint összegezni kell.

```kotlin
listOf(1, 5, 3).sum() == 9
listOf("a", "b", "cc").sumBy { it.length() } == 4
```

A következők történtek a kollekcióban található értékekkel:
1. Három számot adta össze.
2. Három `String` hosszát adta össze.

Ezek alapján lássunk is neki a soron következő koannak:
```kotlin
// Return the sum of prices of all products that a customer has ordered.
// Note: the customer may order the same product for several times.
fun Customer.getTotalOrderPrice(): Double = TODO()
```

Szokásos a történet, az `orders` kollekció `products` elemeinek a `price` értékeit kell összegezni a `.sumByDouble()` funkcióval.

```kotlin
fun Customer.getTotalOrderPrice(): Double = orders.flatMap { it.products }.sumByDouble { it.price }
```

## Kollekciók összegzése másképp (22. koan)

Van az összegzésnek egy általánosabb formája is, ami sok más esetben is tök jól használni, más nyelvekben reducernek is szokták nevezni.

Lássuk is a szokásos egyszerű példánkat:
```kotlin
listOf(1, 2, 3, 4).fold(1, {
    partProduct, element ->
    element * partProduct
}) == 24
```

Majd pedig essünk neki a soron következő koannak:
```kotlin
// Return the set of products that were ordered by every customer
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    TODO()
}
```

A megoldás az eddigi legkomplexebb. [TODO] Ezt okosan meg kell fogalmazni.
```kotlin
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    val allProducts = customers.flatMap { it.orders.flatMap { it.products }}.toSet()
    return customers.fold(allProducts, {
        orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
    })
}
```

## Kollekciók csoportosítása (20. koan)

Szintén megszokott probléma, hogy a kollekciók elemeit csoportosítani kell valamilyen szempont szerint. Például a vásárlókat, város vagy kor szerint. 

A Kotlin erre is kínál egy nagyon kellemes megoldást a `.groupBy()` használatával. Aki használt már SQL-t, feltételezem a többség igen, annak bizonyosan nagyon ismerős lesz.

Lássuk is a szokásos példánkat:
```kotlin
val result = listOf("a", "b", "ba", "ccc", "ad").groupBy { it.length() }

result == mapOf(1 to listOf("a", "b"), 2 to listOf("ba", "ad"), 3 to listOf("ccc"))
```

Az történik itt, hogy csoportosítsuk a kollekció elemeit a `String`-ek hossza alapján. Az eredmény ennek megfelelően pedig egy `Map` lesz, aminek a kulcsa a *hossz* lesz, az érték pedig a *megfelelő elemek listája*.

Ezek alapján lássunk is neki a soron következő koannak:
```kotlin
// Return a map of the customers living in each city
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = TODO()
```

A megoldás adja magát, minden `Customer` objektumnak van egy `city` tulajdonsága, aszerint kell csoportosítani a most megtanult új funkcióval.

```kotlin
fun Shop.groupCustomersByCity(): Map<City, List<Customer>> = customers.groupBy { it.city }
```

## Kollekciók partícionálása (21. koan)

Gyakran van arra szükség, hogy egy kollekció elemeit szétválasszuk valamilyen tulajdonságuk szerint. Például külön vegyük a pozitív és a negatív számokat. Vagy rendeléseknél külön vegyük a leszállított és a nem szállított elemeket.

Lássuk is erre a szokásos egyszerű példánkat:
```kotlin
val numbers = listOf(1, 3, -4, 2, -11)
val (positive, negative) = numbers.partition { it > 0 }
positive == listOf(1, 3, 2)
negative == listOf(-4, -11)
```

Lássuk sorban, ami itt történt:
1. Létrehoztunk egy listát számokból, vegyesen pozitív és negatív.
2. Partícionáltuk ezeket, egyik része a 0-nál nagyobbak, a másik a kisebbek. 

>Külön érdekesség, hogy a második lépésben a dekonstrukciós operátort használtuk. Erről nem volt még szó korábban. 
>[TODO] Ezt fejtsd ki

Ezek alapján essünk neki a soron következő koannak:
```kotlin
// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = TODO()
```

A megoldása csak egy picit, pár sorral komplexebb a korábbiaknál, alapvetően az `Order` objektum `isDelivered` tulajdonságára épít.

```kotlin
fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = customers.filter {
    val (delivered, undelivered) = it.orders.partition { it.isDelivered }
    undelivered.size > delivered.size
}.toSet()
```

## Kombináljuk a korábban tanultakat (23. koan)

Most már nem fogok semmi újat mutatni, helyette kombináljuk az eddig látottakat. 

Így néz ki a mostani koanunk:
```kotlin
// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    TODO()
}

// Return how many times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    TODO()
}
```

A megoldás pedig. [TODO] Ezt fejtsd ki.

```kotlin
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    return orders.filter { it.isDelivered }.flatMap { it.products }.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return customers.flatMap { it.getOrderedProductsList() }.count { it == product }
}

fun Customer.getOrderedProductsList(): List<Product> {
    return orders.flatMap { it.products }
}
```

## Végül pedig a nagy feladat következik (24. koan)

Itt ténylegesen kombináljunk mindent, amit eddig tanultunk. Veszünk egy jó csúnya Java kódot, és azt megszépítjük a Kotlinnal.

Ez a Java kód:
```java
public Collection<String> doSomethingStrangeWithCollection(
        Collection<String> collection
) {
    Map<Integer, List<String>> groupsByLength = Maps.newHashMap();
    for (String s : collection) {
        List<String> strings = groupsByLength.get(s.length());
        if (strings == null) {
            strings = Lists.newArrayList();
            groupsByLength.put(s.length(), strings);
        }
        strings.add(s);
    }

    int maximumSizeOfGroup = 0;
    for (List<String> group : groupsByLength.values()) {
        if (group.size() > maximumSizeOfGroup) {
            maximumSizeOfGroup = group.size();
        }
    }

    for (List<String> group : groupsByLength.values()) {
        if (group.size() == maximumSizeOfGroup) {
            return group;
        }
    }
    return null;
}
```

[TODO] Értelmezzük mi is csinál ez a kód.
 
Itt pedig a Kotlinos változat:
```kotlin
fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection. groupBy { s -> s.length }

    val maximumSizeOfGroup = groupsByLength.values.map { group -> group.size }.max()

    return groupsByLength.values.firstOrNull { group -> group.size == maximumSizeOfGroup }
}
```

Első ránézésre rövidebb, de egyúttal egyszerűbben olvasható is lett a kód.