# Konvenciók

A Kotlin számos magas szintű funkcióra kínál nyelvi szinten megoldás.

## Összehasonlítás (25. koan)

Időről-időre szükség van arra, hogy az osztályok példányait össze lehessen hasonlítani. Egyszerű típusok esetében ez az összevetés egészen egyértelmű. 

Mutatok erre néhány példát:
```kotlin
val a = 5
val b = 13

println(a < b) // true
println(a > b) // false 
```

Komplexebb esetekben, például különböző adat osztályok esetében már jellemzően nem generálhatóak ezek automatikusan, szükség lehet saját logika írására. Ebből a helyzetből indul ki a feladatunk is:
```kotlin
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    
}

fun compare(date1: MyDate, date2: MyDate) = date1 < date2
```

Adott egy saját dátum implementáció, a `MyData`, ezeket szeretnénk szépen összevetni, erre pedig szükség van saját logika írására.. Ebben az esetben felülírjuk (`override`) a `compareTo` metódust. Innentől a megoldás már adja magát.

```kotlin
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}
```

## In Range (26. koan)

Szinte minden nap előkerül az a feladat, hogy szeretnénk megállapítani, hogy egy elem (szám, karakter, valami más), része egy sorozatnak. A legtöbb nyelv esetében erre az alap osztálykönyvtár nyújt megoldást, a Java esetében a legtöbb kollekciónál létezik a `contains` funkció. A Kotlin abban megy tovább, hogy erre nyelvi szinten is kínál egy kényelmes megoldást. 

Máris mutatok néhány példát, így egyszerűbb lesz elképzelni:
```kotlin
val list = listOf("a", "b")
"a" in list  // list.contains("a")
"a" !in list // !list.contains("a")

val numbers = 1..10
3 in numbers // numbers.contains(3)
```

Apróság, de a második példánál érdemes figyelni, mennyivel egyszerűbb így egy sorozatot létrehozni. Hát még ha fordító szinten is optimalizálnak erre.

De térjénk vissza, szóval innentől pedig a feladat nem nehéz, ismét dátumokban gondolkodunk, meg szeretnénk állapítani, hogy egy új dátum egy adott intevallumon belül van-e:
```kotlin
class DateRange(val start: MyDate, val endInclusive: MyDate)

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}
```

A megoldáshoz a `contains` műveletet kell definiálni, és segít majd eldönteni, hogy adott elem benne van-e. 

```kotlin
class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(item: MyDate): Boolean = start <= item && item <= endInclusive
}
```

## Range to

Nekem kifejezetten megtetszett, hogy számokból ennyire egyszerűen lehet sorozatot létrehozni, milyen jó lenne ez a `MyData` esetében is, dátumok sorozatát létrehozni. A következőt szeretnénk kapni:
```kotlin
MyDate(2015, 5, 11)..MyDate(2015, 5, 12)
```

Innentől pedig a feladatot nem nehéz kitalálni:
```kotlin
operator fun MyDate.rangeTo(other: MyDate) = TODO()

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}
```

Szerencsére a nyelv kínál erre eszközt, egy újabb műveletet, a `rangeTo`-t kell megírni. A megoldás pedig egészen meglepően rövid:
```kotlin
operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)
```

## For Loop

Haladjunk tovább ezen az úton, bővítsük a `MyData` osztályt, szeretnénk ezt a sorozatot könnyedén bejárni a `For` ciklus használatával, valahogy így.
```kotlin
fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}
```

A megoldáshoz létre kell hozni egy `Iterator` példányt, amit az esetünkben `DataIterator`-nak hívnak. Ennek két metódusa van:
- `next()`: A sorozat következő elemét adja vissza.
- `hasNext()`: `true` értéket ad vissza, amennyiben van következő elem. 

Így lazy módon lehet bejárni egy sorozat elemeit. Ez egy spórolós megoldás, csak azokat az elemeket állítjuk elő, amelyekre ténylegesen szükség van, így kímélve az erőforrásokat.

```kotlin
class DateRange(val start: MyDate, val end: MyDate): Iterable<MyDate>{
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange:DateRange) : Iterator<MyDate> {
    var current: MyDate = dateRange.start
    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }
    override fun hasNext(): Boolean = current <= dateRange.end
}
```

## Operátor túltöltés

Az előző példáknál már találkoztunk az egyes műveletek túltöltésével. Érdemes lenne kicsit alaposabban megismerni. A feladat segíteni fog az átlátásában, innen indulunk ki:
```kotlin
import TimeInterval.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

enum class TimeInterval { DAY, WEEK, YEAR }

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = TODO()

fun task1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

fun task2(today: MyDate): MyDate {
    TODO("Uncomment") //return today + YEAR * 2 + WEEK * 3 + DAY * 5
}
```

Kicsit terekünk a példán, bevezetünk egy új `enum`-ot, a `TimeInterval`-t, ami egy idő intervallumot ír le nap, hét és év szerint. A `MyDate` esetében szeretnénk azt, hogy egy sima `plus` művelettel ezt egy adott dátumhoz lehessen adni.

A megoldás mindösszesen néhány sor:
```kotlin
operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)
```

## Dekonstrukció

A dekonstrukció műveletével találkozhattatok már, épp ezért ideje megismerni közelebbről is, természetesen továbbra is a `MyDate` példánál maradva. A dekinstrukció használatával egy osztály elemei darabjaira bonthatóak.

```kotlin
val date = MyDate(2017, 11, 20)
val (year, month, dayOfMonth) = date
```

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

fun isLeapDay(date: MyDate): Boolean {
    val (year, month, dayOfMonth) = date

    // 29 February of a leap year
    return year % 4 == 0 && month == 2 && dayOfMonth == 29
}
```

Az omniózus dekonstrukció a 4. sorban van, de ez nem fog csak így simán menni, hibát fog jelezni. A megoldás azonban egyszerű, mindössze annyi a dolgod, hogy sima `class` helyett `data class`-ot definiálj, innentől pedig szinte ingyen kapod.
```kotlin
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)
```

## Invoke

[TODO] Bevezető szövegezés kell ide... ezt egyelőre én se látom át jól :(

Mutatok is néhány példát:
```kotlin

```

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
class Invokable {
    var numberOfInvocations: Int = 0
        private set
    operator fun invoke(): Invokable {
        TODO()
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()
```

A megoldás pedig: [TODO] fejtsd ki
```kotlin
class Invokable {
    var numberOfInvocations: Int = 0
        private set
    operator fun invoke(): Invokable {
        numberOfInvocations++
        return this
    }
}
```