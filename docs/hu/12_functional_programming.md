# Funkcionális programozás

Mostanában sokat hallani a funkcionális programozásról és a Java 8 megjelenésével már használhatjuk az Oracle által megálmodott eszköztárat, a Stream API-tis erre a célra, ami így működik:

```java
public class JavaUser {
 
    // ...
    
    public static Set<String> fetchCitiesOfUsers(
                                  List<JavaUser> users) {
        return users.stream()
                .flatMap(user -> user.addresses.stream())
                .map(JavaUser.Address::getCity)
                .collect(Collectors.toSet());
    }
}
```

Ennek a megfelelője a Kotlin-ban nagyon hasonló, de némileg mégis más:

```kotlin
fun fetchCitiesOfUsers(users: List<KotlinUser>) = users
            .flatMap(KotlinUser::addresses)
            .map(Address::city)
            .toSet()
```

A Kotlin ad számunkra több függvényt is, amikkel funkcionálisan tudunk programozni:

## `map`

A `map` lehetővé teszi számunkra, hogy egy kollekció elemeit egyesével átalakítsuk valami mássá:

```kotlin
fun positionToSize(positions: List<Position>) : List<Size> = 
        positions.map { Size(it.column, it.row) }
```

## `flatMap`

A `flatMap` használatával ki tudunk "lapítani" kollekciókat, innen ered a név is:

```kotlin
fun flattenPositions(positionGroups: List<List<Position>>): List<Position> = 
        positionGroups.flatMap { it }
```

## `filter`

A `filter` esetében kiválogathatunk elemeket, amik nem felelnek meg egy predikátumnak:

```kotlin
fun filterOdds(numbers: List<Int>): List<Int> = numbers.filter { it %2 == 0 }
```

## `reduce`

A `reduce`, ahogy a nevéből is adódik, egy kollekciót redukál egy értékre. Erre jó példa az, ha összesíteni akarjuk
egy listában a számokat:

```kotlin
fun sumNumbers(numbers: List<Int>): Int = numbers.reduce(Int::plus)
```

Habár a `reduce` hibát dob, ha üres listát adunk neki:

```kotlin
sumNumbers(listOf())
```

a `fold` használatával megadhatunk alapértelmezett értéket, ha nincs a listának eleme, és így kiküszöbölhetjük a fenti problémát:

```kotlin
fun sumNumbers(numbers: List<Int>): Int = numbers.fold(0, Int::plus)
```

A fentieket kombinálhatjuk is, így például ha szeretnénk összerakni egy listát azokkal a pozíciókkal, amik egy valamilyen méretű
négyszögbe beleférnek, akkor azt így megtehetjük:

```kotlin
fun Size.fetchPositions(): List<Position> = (0 until rows).flatMap { row ->
    (0 until columns).map { column ->
        Position.of(column, row)
    }
}
```