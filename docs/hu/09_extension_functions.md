# Kiegészítő funkciók

## A alap probléma 

*Java*-ban számtalanszor fordul elő olyan helyzet, hogy van egy osztály, amelyet praktikus lenne kiegészítened, de nincs erre lehetőség. Ilyenkor lehet ugyan nyúlni a *Java dekorátor*okért, de ez egyrészt trükkös, másrészt nem is mindig sikerül jól.

Például, ha egy olyan dekorátort szeretnénk készíteni, ami a `List` interfész összes implementációjával képes működni, akkor nem szerencsés a `List`-et kiterjesztenünk, mert úgy elég sok metódust kellene implementálnunk, ehelyett érdemesebb az `AbstractList`-ből kiindulnunk.

Persze ehhez kell az `AbstractList`hez hasonló megoldás, és jobb ha az osztály nem `final`.

## Egy egyszerű példa

Így tudsz kiegészíteni egy `Int`-ekkel dolgozó `MutableList`-et:
```kotlin
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] 
    this[index1] = this[index2]
    this[index2] = tmp
}

var numbers = mutableListOf(1, 2, 3)
numbers.swap(3, 1)
```

Üdítően egyszerű a szintaktika, a `MutableList<Int>` után egy sima '.' és írod a metódust, `swap(index1: Int, index2: Int) { ... }`, mintha azt normálisan tennéd. A funkción belül a `this` kulcsszú egyébként a `MutableList` objektum példányára utal.

## Jó példa

Mutatok egy gyakorlatiasabb kódrészletetet is, ahol generikus adattípusokkal dolgozunk:

```kotlin
fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this)
        result.add(transform(item))
    return result
}
```

Habár egy elég elterjedt osztályról van szó, de általánosan is nagyon jól használható metódussal bővült. Ez fontos, ezen el lehet csúszni könnyedén.

## Rossz példa

Vannak azonban olyan helyzetek, amikor ez inkább zavaró.

```kotlin
fun String.extractCustomerName() : String {
    return "Customer Name"
}
```

Ez esetben szintén egy nagyon gyakran használt osztályt egészíthetsz ki egy az adott terület probléma domain-jéhez köthető funkcióval. Hasonló esetben a projektben mindenki találkozni fog ezzel a kiegészítő funkcióval, és bizonyosan vakarni fogja a fejét, hogy ez mi a csuda, és mire jó.

Tudom, nem túl frappáns a példa, de szemlélteti a lényeget.

Ilyen esetben érdemes kerülni.

Helyette érdemesebb inkább a funkciót a *domain object*be rakni.