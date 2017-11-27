# Null biztonság

A null-ok ellenőrzése általában sok logikai művelettel és boilerplate-tel jár. A Java 8 megjelenése óta ezen már tudunk segíteni az Optional használatával, de mi történik akkor, ha egy Optional referencia a `null`? Bizony, ilyenkor megkapjuk a szokásos `NullPointerException`-t, ami a Java 20 éves fennállása óta még mindig nem képes megmondani, hogy mi volt a null. Nézzük meg a következő példát:

```java
public class JavaUser {

    static class Address {
        String city;
    }

    private final String firstName;
    private final String lastName;
    private final List<Address> addresses;

    /**
     * If you want to make sure nothing is `null`
     * you have to check everything.
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

A Kotlin használatával több opciónk is van. Ha együtt akarunk működni Java-projektekkel, vagy egy már meglévő Java-projekten használunk Kotlin-t is, akkor lehet a `null` biztonsági operátort (`?`) használni:


```kotlin
data class KotlinUserWithNulls(val firstName: String?,
       // String? means that it is either a String object or a null
                               val lastName: String?,
                               val addresses: List<Address> =  listOf()) {

    data class Address(val city: String?)

    companion object {
        fun fetchFirstCity(user: KotlinUserWithNulls?): String? {
            user?.addresses?.forEach { it.city?.let { return it } }
            return null
        }
    }
}
```

A `?` jobb oldalán lévő kód csak akkor fog futni, ha a bal oldalán lévő kifejezés nem `null`.
A `let` funkció létrehoz egy lokális scope-ot azzal az objektummal, amin meg lett hívva, így itt az `it` változó az `it.city`-re fog mutatni a visszatéréskor.
Ha viszont nem kell Java-kóddal együttműködni, akkor jobban járunk, ha egyáltalán nem használunk `null`-t a projektünkön:

```kotlin
data class KotlinUserWithoutNulls(val firstName: String,
                                  // this parameter can't be null
                                  val lastName: String,
                                  val addresses: List<Address> = listOf()) {

    data class Address(val city: String)

    companion object {
        fun fetchFirstCity(user: KotlinUserWithNulls) =
            user.addresses.first().city
    }
}
```

Ha nincs a kódbázisunkban `null` (nics `?` sehol), akkor lényegesen egyszerűbb lesz az egész.

## Hibalehetőségek

A fentiek mellett tudnunk kell azt is, hogy bizonyos esetekben azt hihetjük, hogy nem láthatunk `null`-t, de az mégis előfordulhat:

```kotlin
class MysteryNull {

    private val c: String

    init {
        bar()
        c = ""
    }
    private fun bar() {
        println(c.length)
    }
}
```

A fenti kódrészlet ugyan lefordul, viszont ha példányosítjuk az osztályt, akkor NPE lesz a jutalmunk. Az ok az, hogy az előtt
próbáltuk a `c` változó értékét elérni, hogy az értéket kapott volna.

A fenti mellett érdemes figyelni a JDK osztályok használatakor is:

```kotlin
class QueueTrouble {

    val queue: Queue<Double> = LinkedList()

    fun fetchFirst() = queue.peek().toInt()
}
```

A fenti esetben, ha meghívjuk a `fetchFirst` függvényt, akkor szintén NPE lesz az eredmény, ugyanis a `peek` `null`-lal tér vissza.
Ennek oka az, hogy a Java forráskódban nincs jelölve az, hogy a `peek` `null`-lal is visszatérhet:

```kotlin
 E peek();
```

Ezekre az esetekre kiemelten figyeljünk.