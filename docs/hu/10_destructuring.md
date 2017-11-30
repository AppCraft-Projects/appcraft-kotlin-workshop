# Destruktúrálás

Vitán felül a legegyszerűbb téma következik most, mégis sokszor nagyon hasznos lesz.

## Mit jelent a destruktúrálás?

Destructuring valójában egy egyszerű és könnyed módja annak, hogy kicsomagoljunk egy *data class*ot. 

A jobb érthetőség kedvéért alap példát az `Position` osztállyal, így csináltuk volna ezt a régi eszköztárral:
```kotlin
data class Position(val column: Int,
                    val row: Int)

val position = Position(3, 4)

val column = postion.column
val row = postition.row

println(column)
println(row)
```

Van erre egy másik megoldási lehetőség is:
```kotlin
data class Position(val column: Int,
                    val row: Int)

val position = Position(3, 4)

val column = postion.component1
val row = postition.component2

println(column)
println(row)
```

**TIPP**: Érdemes próbálkozni a Ctrl+Space kombinációval, rettentő sokat tud segíteni ez a fajta okos kiegészítés.

A dekstruktúrálás azonban a következő trükkös lehetőséget nyújtja:
```kotlin
data class Position(val column: Int,
                    val row: Int)

val position = Position(3, 4)

val (column, row) = postion

println(column)
println(row)
```

A kód kicsit tömörebb és olvashatóbb lett, kevesebbet is kellett gépelni.

## Visszatérési érték desktruktúrálása

Lényegében ugyanígy történik akkor is, ha funkció visszatérési értékét kell szétbontani:

```kotlin
data class Position(val column: Int,
                    val row: Int)

fun positionReturns() {
  return Position(3, 4)
} 

val (column, row) = postion

println(column)
println(row)
```

## Érték kihagyása

Akkor azokat "_"-al kell jelölni:

```kotlin
data class Position(val column: Int,
                    val row: Int)

val position = Position(3, 4)
val position2 = Position(6, 8)

val (column, _) = postion
val (_, row) = postion2

println(column)
println(row)
```

**TIPP**: Érdemes használni a Alt+Enter kombinációt, rettentő sokat tud segíteni, felajánl okos átalakításokat, igazi *MAGIC*. 😍

**TIPP**: Ha sok elemes az objektum, de neked csak az első 1-2 elem kell, akkor csinálhatod azt, hogy csak az elsőt destrukturálod, a többihez pedig nem teszel ki alsó vonást.

## Érdemes óvatosnak lenni

Azonban tartogat ez a módszer bizonyos veszélyeket, amelyekre érdemes előre felkészülni. Tegyük fel kiegészítjük a data class objektumunkat egy *deepth* propertyvel, ami szintén String típusú, de a destructuringon nem változtatunk. Mi fog történni?

```kotlin
data class Position(val deepth: Int,
                    val column: Int,
                    val row: Int)

val postion = Position(12, 3, 4)

val (column, row) = postion

println(column) // 12
println(row) // 3
```

Ugye nem teljesen erre számítottatok, az van, hogy a *component2* megváltozott, és nem fogja úgy párosítani, ahogy azt egy ember tenné. Lesz egy nem várt elcsúszás, viszont fordítási időben erre semmi figyelmeztetést nem kapsz. Rossz hír továbbá, hogy nem lehet jelölni mivel kössük össze.

## `Pair` és `Triple`

Az előző példán csavarunk egy kicsit. Speciális esetben lehetőség van a `Pair` és `Triple` osztályok használatára is.

```kotlin
data class Position(val column: Int,
                    val row: Int)

fun twoValuesReturn(): Pair<Int, Int> {
  val position = Position(3, 4)
  val (column, row) = position
  return Pair(column, row)
}

val (column, row) = twoValuesReturn()
```

Azonban érdemes óvatosnak lenni vele, habár néha hasznos, de sokszor nehezebb áttekinteni, hogy melyik érték mi is volt.

## Map és bejárás

A `Map` struktúra esetében azonban szépen megjelenik a `Pair`, az lényegében ilyeneket kezel. Ilyen helyzetben tényleg jó is használni őket.

```kotlin
data class Position(val column: Int,
                    val row: Int)

var map: HashMap<Int, Position> = HashMap()
map.put(1, Position(3, 4))
 
for((key, value) in map){
  println("Key: $key, Value: $value")
}
```
