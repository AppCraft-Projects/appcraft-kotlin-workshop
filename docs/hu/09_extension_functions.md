# Kiegészítő funkciók

## A alap probléma 

Java-ban számtalanszor fordul elő olyan helyzet, hogy van egy osztály, amelyet praktikus lenne kiegészítened, de nincs erre lehetőség. Ilyenkor lehet ugyan nyúlni a Java dekorátorokért, de ez egyrészt trükkös, másrészt nem is mindig sikerül jól.

Például, ha egy olyan dekorátort szeretnénk készíteni, ami a List interfész összes implementációjával képes működni, akkor nem szerencsés a List-et kiterjesztenünk, mert úgy elég sok metódust kellene implementálnunk, ehelyett érdemesebb az AbstractList-ből kiindulnunk.

Persze ehhez kell az AbstractListhez hasonló megoldás, és jobb ha az osztály nem final.

## Jó példa

Mutatok egy gyakorlatias kódrészletet:

```kotlin
  data class Address(var street: String, val city: String, val country: String)

  fun <T> MutableList<T>.swap(firstIndex: Int, secondIndex: Int) {
      val tmp = this[index1]
      this[index1] = this[index2]
      this[index2] = tmp
  }

  val address1 = Address("Matyas kiraly u. 45.", "Kazincbarcika", "Magyarország")
  val address2 = Address("Kossuth Lajos u. 12.", "Baja", "Magyarország")
  val address3 = Address("Széchenyi István u. 34.", "Eger", "Magyarország")

  val addresses = mutableListOf<Address>(address1, address2, address3)
  addresses.swap(0, 2)
  println(addresses)
```

Habár egy elég elterjedt osztályról van szó, de általánosan is nagyon jól használható metódussal bővült.

## Rossz példa

Vannak azonban olyan helyzetek, amikor inkább zavaró. 

```kotlin
fun String.extractCustomerName() : String {
    // ...
}
```

Ez esetben szintén egy nagyon gyakran használt osztályt egészíthetsz ki egy az adott terület probléma domainjéhez köthető funkcióval. Hasonló esetben a projektben mindenki találkozni fog ezzel a kiegészítő funkcióval, és bizonyosan vakarni fogja a fejét, hogy ez mi a csuda, és mire jó.

Ilyen esetben érdemes kerülni.

Helyette érdemesebb inkább a funkciót a domain objectbe rakni.