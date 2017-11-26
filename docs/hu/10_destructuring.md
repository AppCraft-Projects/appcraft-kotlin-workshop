# Destruktúrálás

Vitán felül a legegyszerűbb téma következik most, mégis sokszor nagyon hasznos lesz.

## Mit jelent a destruktúrálás?

Destructuring valójában egy egyszerű és könnyed módja annak, hogy kicsomagoljunk egy Data Classot. 

A jobb érthetőség kedvéért alap példát az Address osztállyal, így csináltuk volna ezt a régi eszköztárral:
```kotlin
data class Address(val street: String, val city: String, val country: String)

val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország") 

val street = home.street
val city = home.city 
val coutry = home.country

println(street)
println(city)
println(country)
```

Van erre egy másik megoldási lehetőség is:
```kotlin
data class Address(val street: String, val city: String, val country: String)

val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország") 

val street = home.component1
val city = home.component2
val coutry = home.component3

println(street)
println(city)
println(country)
```

**TIPP**: Érdemes próbálkozni a Ctrl+Space kombinációval, rettentő sokat tud segíteni ez a fajta okos kiegészítés.

[TODO] Mikor hasznos igazán ez component1, 2, 3 megoldás? 

## Visszatérési érték desktruktúrálása

Lényegében ugyanígy történik akkor is, ha funkció visszatérési értékét kell szétbontani:

```kotlin
data class Address(val street: String, val city: String, val country: String)

fun addressReturns() {
  return Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország") 
} 

val (street, city, coutry) = someFunction()

println(street)
println(city)
println(country)
```

## Komplex visszatérési érték

Az előző példán csavarunk egy kicsit. Speciális esetben lehetőség van a Pair és Triple osztályok használatára is.

```kotlin
data class Address(val street: String, val city: String, val country: String)

fun twoValuesReturn(): Pair<Int, String> {
    return Pair(1, Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország") )
}
 
val (id, address) = twoValuesReturn()
```

[TODO] Mikor van igazán haszna a Pair és Triple használatának, erre lenne jó példát találni.

## Érték kihagyása

Akkor azokat "_"-al kell jelölni:

```kotlin
data class Address(val street: String, val city: String, val country: String)

val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország") 

val (street, _, coutry) = home
val (_, _, coutry2) = home
val (street3, city3, _) = home

println(street)
println(country)
println(country2)
println(street3)
println(city3)
```

**TIPP**: Érdemes próbálkozni a Alt+Enter kombinációval, rettentő sokat tud segíteni, felajánl okos átalakításokat, igazi *MAGIC*. 😍

## Map és bejárás

```kotlin
data class Address(val street: String, val city: String, val country: String)

var map: HashMap<Int, Person> = HashMap()
map.put(1, Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország"))
 
for((key, value) in map){
    println("Key: $key, Value: $value")
}
```