# Függvények

A Java-val ellentétben Kotlin-ban egy függvény nem kell, hogy egy osztály része legyen, akár egy külön fájlban is
definiálhatjuk őket:

```kotlin
fun <T> lock(lock: Lock = ReentrantLock(), body: () -> T): T {
    lock.lock()
    try {
        return body()
    }
    finally {
        lock.unlock()
    }
}
```

A fenti példa a definíció után használható lesz globálisan elérhető függvényként a projektünkben.
Ezen kívül az is megfigyelhető, hogy hogyan tudunk egy függvénynek egy másikat beadni paraméterként:

```kotlin
body: () -> T
```

Itt egy `body` nevű paramétert definiálunk, ami egy olyan függvény, ami nem vár paramétert és visszatér `T` típusú
értékkel. Ezt hívjuk lambda szintaxisnak.

A Kotlin hoz az sdk-val pár hasonló függvényt, ilyen például a `let`, az `also` és a  `run`:

```kotlin
public inline fun <T, R> T.let(block: (T) -> R): R = block(this)

public inline fun <T> T.also(block: (T) -> Unit): T { block(this); return this }

public inline fun <R> run(block: () -> R): R = block()

```

Ezek mind olyan függvények, amik más függvényeket várnak paraméterként, amiket kód blokk-ként kezelnek és futtatnak.
A `let` akkor lehet hasznos, ha egy saját scope-ot akarunk kialakítani. `also`-t érdemes használni például builder-ek
írásakor, hogy a `this`-t vissza tudjuk adni anélkül, hogy sok kódot kelljen írnunk. A `run` futtatja a kód blokkot
és visszatér az utolsó kifejezés értékével.

Amennyiben függvényünk utolsó paramétere egy függvény, használhatjuk az egyszerűsített szintaxist, például a fenti `lock`
funkció meghívása így nézhet ki:

```kotlin
lock {
    var num = 0
    num++
}
```