# Bevezető

## Mi a Kotlin?

- A [JetBrains](https://www.jetbrains.com/) saját fejlesztésű programozási nyelve, azé a cégé, akik az [IDEA](https://www.jetbrains.com/idea/) fejlesztői környezet mögött is állnak.
- Egy egyszerű, és sokoldalú Java alternatíva
- Ami kifogástalan együttműködésre képes a Java-val
- Java bájtkódra fordul
- A JVM-en fut
- Továbbá képes Javascript-re is fordulni

Ha elolvassuk a dokumentációt, akkor találhatunk még pár ígéretes dolgot:
- Többet elérhetünk kevesebb kód írásával
- Megoldást kínál sok, Java-ban fellelhető problémára
- Továbbra is használhatjuk a Java ökoszisztémát vele
- Frontend és backend kódot is tudunk írni ugyanazon a nyelven
- 100%-ban együttműködő a Java-val
- E tekintetben jobban teljesít, mint más alternatívák (Clojure, Scala)
- És csak egy vékony réteg komplexitást ad hozzá a Java-hoz

Tekintsük át, hogy a Kotlin milyen eszközöket ad a kezünkbe:

## Funkciók és változók

Kotlinban funkciókat a `fun` kulcsszó használatával deklarálhatunk. A szintaxis a következő:

```kotlin
fun double(x: Int): Int {
    return 2*x
}
```

Változókat a `val`, illetve a `var` kulcsszó használatával hozhatunk létre. A `val` kulcsszót használjuk, ha nem kívánjuk
megváltoztatni a referencia értékét később (ez a `final` kulcsszó megfelelője Java-ban). A `var` használatával változónk
később kaphat új értéket:


```kotlin
val a: Int = 1  // változó deklaráció és értékadás
a = 2           // HIBA

var b: Int = 2
b = 3           // működik

```


## `If`, `when`, `for` és `while`

Az `If`, a `when`, a `for` és a `while` kifejezések, azaz értéket adnak vissza:

```kotlin
// Tradícionális felhasználás
var max = a 
if (a < b) max = b

// If + else
var max: Int
if (a > b) {
    max = a
} else {
    max = b
}
 
// Kifejezésként
val max = if (a > b) a else b
```

A `when` kulcsszó a `switch` helyett került bevezetésre. Használata a következő:

```kotlin
when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> { // az else egy kód blokk
        print("x is neither 1 nor 2")
    }
}
```

Ha két esetet ugyanúgy szeretnénk kezelni, akkor kombinálhatók:

```kotlin
when (x) {
    0, 1 -> print("x == 0 or x == 1")
    else -> print("otherwise")
}
```

kondícióként nem csak konstansokat használhatunk:

```kotlin
when (x) {
    parseInt(s) -> print("s encodes x")
    else -> print("s does not encode x")
}
```

illetve használhatjuk a `when`-t kifejezésként is:

```kotlin
val result = when (x) {
    0 -> "No items present."
    else -> "$x item(s)."
}
```

A `for` loop hasonlóan működik, mint a Java-ban az enhanced for loop:

```kotlin
for (item: Int in ints) {
    // ...
}
```

A `while` a Java-hoz képest változatlan:

```kotlin
while (x > 0) {
    x--
}
```

## Osztályok, objektumok és konstruktorok

### Deklaráció

Osztályokat a Java-hoz hasonlóan a `class` kulcsszóval deklarálunk:

```kotlin
class Invoice {
}
```

de azzal ellentétben osztályaink lehetnek üresek:

```kotlin
class Empty
```

Ez igaz interfészeinkre és adat osztályainkra is.

Egy osztálynak lehet **egy** elsődleges és több **másodlagos** konstruktora:

```kotlin
class Person constructor(firstName: String)
```

Amennyiben csak egy konstruktorunk van, a `constructor` kulcsszó elhagyható:

```kotlin
class Person (firstName: String)
```

Osztályunkat az `init` blokk használatával tudjuk inicializálni:

```kotlin
class Customer(name: String) {
    init {
        logger.info("Customer initialized with value $name")
    }
}
```

A konstruktor paramétereket az inicializáló blokkon kívül lehet a mező inicializáláskor is használni:

```kotlin
class Customer(name: String) {
    val customerKey = name.toUpperCase()
}
```

illetve a mezőinket akár deklarálhatjuk a konstruktorban is:

```kotlin
class Person(val firstName: String, val lastName: String, var age: Int) {
    // ...
}
```

A konstruktorunkban használhatunk láthatósági módosítókat és annotációkat is, viszont ebben az esetben a `constructor`
kulcsszó használata kötelező:

```kotlin
class Customer public @Inject constructor(name: String) { /* ... */ }
```

Osztályaink rendelkezhetnek másodlagos konstruktorral, melyeket a `constructor` kulcsszóval deklarálhatunk:

```kotlin
class Person {
    constructor(parent: Person) {
        parent.children.add(this)
    }
}
```

Ha egyszerre akarunk használni elsődleges és másodlagos konstruktort, akkor a másodlagos konstruktornak delegálnia **kell**
az elsődleges konstruktornak:

```kotlin
class Person(val name: String) {
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
```

Amennyiben egy nem-absztrakt osztálynak nem deklarálunk konstruktort, akkor a Kotlin generál neki egy paraméter nélküli
elsődleges publikus konstruktort. Ha ezt nem szeretnénk, akkor priváttá is tehetjük az osztályunk konstruktorát:

```kotlin
class DontCreateMe private constructor ()
```

### Létrehozás

Ahhoz, hogy példányosítsuk egy osztályunkat, meg kell hívni a kívánt konstuktort pontosan úgy, mint ahogy egy funkciót hívnánk:

```kotlin
val invoice = Invoice()

val customer = Customer("Joe Smith")
```

### Öröklődés

Minden osztály közös őse az `Any` típus.
Amennyiben szeretnénk, hogy az osztályunk egy másik típusból örököljön, egy kettőspont után határozzuk meg a típust:

```kotlin
open class Base(p: Int)

class Derived(p: Int) : Base(p)
```

Csak interfészekből és olyan osztályokból lehet örökölni, amelyek rendelkeznek az `open` kulcsszóval.

Amennyiben felül akarunk definiálni egy műveletet, az `override` kulcsszót kell használnunk.

### A `companion object`

A Kotlinban nem található meg a `static` kulcsszó. 

Ha olyan mezőket, vagy funkciókat szeretnénk, amik elérhetők egy osztály példányosítása nélkül is, akkor deklarálhatjuk őket
egy külön fájlban:

```kotlin
fun foo(bar: String) {
    println(bar)
}
```

illetve, deklarálhatjuk őket egy osztályon belül is egy `companion object`-ben:

```kotlin
class Wom {

    companion object {
        fun foo(bar: String) {
            println(bar)
        }
    }

}
```

A fenti esetben a `foo` függvényt tudjuk használni `Wom` példányosítása nélkül is:

```kotlin
    Wom.foo("baz")
```


## Érték- és adatosztályok
Amit itt láthatunk, az egy jó öreg POJO a szokásos boilerplate kóddal:

```java
/**
 * A plain old Java object with all the boilerplate.
 */
public class CubeCoordinate {

    private final int x;
    private final int y;
    private final int z;

    public CubeCoordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CubeCoordinate cubeCoordinate = (CubeCoordinate) o;
        return getX() == cubeCoordinate.getX() &&
                getY() == cubeCoordinate.getY() &&
                getZ() == cubeCoordinate.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    @Override
    public String toString() {
        return "CubeCoordinate {x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
```
Nézzük meg, hogy néz ki ugyanez Kotlin-ban:

```kotlin
data class CubeCoordinate(val x: Int, val y: Int, val z: Int)
```

## Destruktúrálás

Osztályainkat *destruktúrálhatjuk*, ami egyfajta kicsomagolást jelent:

```kotlin
class Person (val firstName: String, val lastName: String) {

    operator fun component1() = firstName

    operator fun component2() = lastName
}

val (firstName: String, lastName: String) = Person("Elvis", "Presley")
```

Ahhoz, hogy destruktúrálhassunk egy osztályt, deklarálnunk kell a `component*` *operátorokat*, ahogy a fenti példában látható.
Amennyiben nem akarunk speciális megfeleltetéseket használni a `component*` függvényeinkben, használhatunk *adat osztályokat* is:

```kotlin
data class Person (val firstName: String, val lastName: String)

val (firstName, lastName) = Person("Elvis", "Presley")
```

*Adat osztályok* használatakor a `component*` függvények automatikusan generálódnak.


## `Pair` és `Triple`

Amennyiben egy függvényből szeretnénk egy, vagy több értékkel visszatérni használhatjuk a `Pair`, vagy a `Triple` osztályokat:


```kotlin
fun callHTTPEndpoint(url: String): Pair<Int, Status> {
    // ...
    return Pair(result, status)
}

val (result, status) = callHTTPEndpoint("http://www.google.com")

```

## Null biztonság

A `null`-ok ellenőrzése általában sok logikai művelettel és boilerplate-tel jár.
Nézzük meg a következő példát:

```java
public class JavaUser {

    static class Address {
        String city;
    }

    private final String firstName;
    private final String lastName;
    private final List<Address> addresses;

    /**
     * If you want to make sure nothing is `null` you have to check everything.
     */
    public static String getFirstCity(JavaUser user) {
        if(user != null && user.addresses != null && !user.addresses.isEmpty()) {
            for(Address address : user.addresses) {
                if(address.city != null) {
                    return address.city;
                }
            }
        }
        throw new IllegalArgumentException("This User has no cities!");
    }
}
```

A Kotlin használatával több opciónk is van.
Ha együtt akarunk működni Java-projektekkel, vagy egy már meglévő Java-projekten használunk Kotlin-t is,
akkor lehet a `null` biztonsági operátort (`?`) használni:

```kotlin
data class KotlinUserWithNulls(val firstName: String?,
                               // String? means that it is either a String object or a null
                               val lastName: String?,
                               val addresses: List<Address> = listOf()) {

    data class Address(val city: String?)

    companion object {
        fun fetchFirstCity(user: KotlinUserWithNulls?): String? {
            user?.addresses?.forEach { it.city?.let { return it } }
            return null
        }
    }
}
```

Ha viszont nem kell Java-kóddal együttműködni, akkor jobban járunk, ha egyáltalán nem használunk `null`-t a projektünkön:

```kotlin
data class KotlinUserWithoutNulls(val firstName: String,
                                  // this parameter can't be null
                                  val lastName: String,
                                  val addresses: List<Address> = listOf()) {

    data class Address(val city: String)

    companion object {
        fun fetchFirstCity(user: KotlinUserWithNulls)
         = user.addresses.first().city
    }
}
```

## Típus kikövetkeztetés

A Kotlin támogatja a típusok kikövetkeztetését, ami azt jelenti, hogy sok esetben a kontextusból ki tudja deríteni a fordító a
típusok megjelölése nélkül is azt, hogy egy-egy referencia milyen típussal rendelkezik.
Nézzük meg a következő példát:

```kotlin
data class KotlinUser(val firstName: String,
                      val lastName: String,
                      val addresses: List<Address> = listOf()) {

    data class Address(val city: String)

    fun getFirstAddressNoInference(): Address {
        val firstAddress: Address = addresses.first()
        return firstAddress
    }
}
```

A fenti kód egyszerűsíthető, ha hagyjuk a Kotlin fordítónak, hogy kikövetkeztesse a típusokat:

```kotlin
fun getFirstAddressInferred(): Address {
    val firstAddress = addresses.first()
    return firstAddress
}
```

További egyszerűsítés:

```kotlin
fun getFirstAddress() = addresses.first()
```

## Együttműködés Kotlin és Java között
Az alábbi példában látható, hogy hogyan tud együttműködni Java és Kotlin kód:

```java
public class KotlinInterop {

    public void helloJava() {
        System.out.println("Hello from Java!");
    }

    public void helloKotlin() {
        JavaInterop.createInstance().helloKotlin();
    }
}
```

```kotlin
class JavaInterop {

    fun helloJava() {
        KotlinInterop().helloJava()
    }

    fun helloKotlin() {
        println("Hello from Kotlin!")
    }

    companion object {

        @JvmStatic
        fun createInstance() = JavaInterop()
    }
}
```