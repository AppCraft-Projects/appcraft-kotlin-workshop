# Expression, statement és a *when* operátor

## Expression vs statement

A Kotlin esetében különösen éles, ahogy ez a két fogalom elválik egymástól, valamint ahogy azt látni fogjátok, a különbség a Java-val szemben egészen szembetűnő.

### Expression

> Az expression tartalmaz változókat és operátorokat és pontosan egy értéket adnak vissza.

Lássunk erre egy példát:
```kotlin
val score: Int
score = 90 + 25
```

Ebben a példában a `90 + 25` egy expression, egy Int értéket ad vissza.

### Az *if* operátor is expression

Vigyük egy kicsit tovább, a Kotlin esetében az `if` operátor is egy epxression. Ellenben Java-ban statement volt. 

Egy példa hamar meg fogja világítani:
```kotlin
  val a = 12
  val b = 13
  val max: Int

  max = if (a > b) a else b
  println("$max")
```

Ebben a példában az `if (a > b) a else b` az expression, ami Int értéket ad vissza és azt a `max` változóhoz rendeli.

### Statement

> A statement tartalmaz mindent, ami a végrehajtás egy teljes egységéhez szükséges. Az expression mindig egy statement része.

Ez alapján az előző példában az `if (a > b) a else b` az expression, a `max = if (a > b) a else b` pedig a statment. 

### Miért jó, ha az *if* expression?
Azért jó, hogy az *if* operátor értékkel tér vissza, mert így rugalmasabban lehet használni, például át lehet adni függvénynek, ilyesmik.

Valamint ennek köszönhetően nincs szükség a Java féle ?: operátorra, ez így sokkal simábban elintézhető.

## A *when* operátor

A C-stílusú nyelvek `switch` operátorát váltja a `when`. Működését tekintve nagyon hasonló, de tud számos okosságot, amitől jelentősen kényelmesebb lesz használni.

Lássunk egy példát, érteni fogjátok:
```kotlin
fun Int.isOdd() = this % 2 == 1
fun Int.isEven() = this % 2 == 0

fun main(args: Array<String>) {
    val x = 3

    when (x) {
        1 -> print("x == 1")
        2, 3 -> print("x == 2 or x == 3")
        in 4..10 -> print("x is in the range")
        !in 4..10 -> print("x is outside the range")
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }

    when {
        x.isEven() -> print("x is even")
        x.isOdd() -> print("x is odd")
        else -> print("WAT?")
    }
}
```

Rengeteg olyan finomság, amit a switch nem tudott, ezáltal nagyban egyszerűsíthető a kód.

Kiegészítésként még annyit, hogy a `when` is expression, ez pedig az `if`-hez hasonló előnyökkel jár.

## Egy gyakorlatias példa a végére

Kezdjünk ezzel a példával:

```kotlin
object TextColorFactory {

  // TODO: simplify this method by using the `if` with a `return` statement
  fun fromString(value: String): Color {
    val result: Color?
    if (RGB_COLOR_PATTERN.matcher(value).matches()) {
      val r = Integer.parseInt(value.substring(1, 3), 16)
      val g = Integer.parseInt(value.substring(3, 5), 16)
      val b = Integer.parseInt(value.substring(5, 7), 16)
      result = Color(r, g, b)
    } else {
      throw IllegalArgumentException("Unknown color definition \"" + value + "\"")
    }
    return result
  }

  private val RGB_COLOR_PATTERN = Pattern.compile("#[0-9a-fA-F]{6}")
}
```

Ez egy tipikus Java-s kód, de mivel itt az if egy epxression, így lehet csinálni belőle egy ilyet, kitörölni a resultos részt.

```kotlin
object TextColorFactory {

    // TODO: simplify this method by using the `if` with a `return` statement
    fun fromString(value: String): Color {
        return if (RGB_COLOR_PATTERN.matcher(value).matches()) {
            val r = Integer.parseInt(value.substring(1, 3), 16)
            val g = Integer.parseInt(value.substring(3, 5), 16)
            val b = Integer.parseInt(value.substring(5, 7), 16)
            Color(r, g, b)
        } else {
            throw IllegalArgumentException("Unknown color definition \"" + value + "\"")
        }
    }

    private val RGB_COLOR_PATTERN = Pattern.compile("#[0-9a-fA-F]{6}")
}
```

Következő lépés, hogy nem kell a return se, és a visszatérési érték sem. Az = jel mindig legyen a sor végén, a következő sortól legyen a kód.

```kotlin
object TextColorFactory {

    // TODO: simplify this method by using the `if` with a `return` statement
    fun fromString(value: String) =
        if (RGB_COLOR_PATTERN.matcher(value).matches()) {
            val r = Integer.parseInt(value.substring(1, 3), 16)
            val g = Integer.parseInt(value.substring(3, 5), 16)
            val b = Integer.parseInt(value.substring(5, 7), 16)
            Color(r, g, b)
        } else {
            throw IllegalArgumentException("Unknown color definition \"" + value + "\"")
        }

    private val RGB_COLOR_PATTERN = Pattern.compile("#[0-9a-fA-F]{6}")
}
```