# Konvenciók

[TODO] Bevezető szöveget megírni.

## Összehasonlítás (25. koan)

[TODO] Bevezető szövegezés kell ide...

Mutatok is néhány példát:
```kotlin
// TODO kell ide néhány példa
```

[TODO] mi is történt ezekben a példákban, magyarázd el

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    
}

fun compare(date1: MyDate, date2: MyDate) = date1 < date2
```

A megoldás pedig: [TODO] fejtsd ki
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

[TODO] Bevezető szövegezés kell ide...

Mutatok is néhány példát:
```kotlin
val list = listOf("a", "b")
"a" in list  // list.contains("a")
"a" !in list // !list.contains("a")
```

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
class DateRange(val start: MyDate, val endInclusive: MyDate)

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}
```

A megoldás pedig: [TODO] fejtsd ki
```kotlin
class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(item: MyDate): Boolean = start <= item && item <= endInclusive
}
```

## Range to

[TODO] Bevezető szövegezés kell ide...

Mutatok is néhány példát:
```kotlin
MyDate(2015, 5, 11)..MyDate(2015, 5, 12)
```

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
operator fun MyDate.rangeTo(other: MyDate) = TODO()

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}
```

A megoldás pedig: [TODO] fejtsd ki
```kotlin
operator fun MyDate.rangeTo(other: MyDate) = DateRange(this, other)
```

## For Loop

[TODO] Bevezető szövegezés kell ide...

Mutatok is néhány példát:
```kotlin

```

Innentől pedig a feladat nem nehéz, innen indulunk ki:
```kotlin
fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}
```

A megoldás pedig: [TODO] fejtsd ki
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

[TODO] Bevezető szövegezés kell ide...

Mutatok is néhány példát:
```kotlin

```

Innentől pedig a feladat nem nehéz, innen indulunk ki:
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

A megoldás pedig: [TODO] fejtsd ki
```kotlin
operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)
```

## Dekonstrukció

[TODO] Bevezető szövegezés kell ide...

Mutatok is néhány példát:
```kotlin

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

A megoldás pedig: [TODO] fejtsd ki
```kotlin
data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)
```

## Invoke

[TODO] Bevezető szövegezés kell ide...

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