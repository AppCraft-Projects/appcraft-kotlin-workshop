# Mutable és immutable típusok?

## Bevezető gondolatok

> **Mit is jelent az immutable?** Röviden annyit nem tudod megváltoztatni az értékét, hanem a változásokkal egy új jön létre. Ezzel alapvetően számos olyan nehezen felderíthető hibának lehet elébe menni, amelyek az alkalmazás állapotának szabad manipulálásából következik. Többszálú környezetben ez kiváltképp nehezen átlátható.

Sok nyelv esetében az érték szerinti típusok és String jó példát jelentenek erre.

Rögtön az elején érdemes eloszlatni egy gyakori félreértést, a Kotlin típusok javarészt nem immutable-ök, neked kell azzá tenned. 

Nagyon hasonlatosan működik mint a *Java* esetében a *final* kulcsó esetében történt. Avagy a referencia típusok esetében nem lehet megváltoztatni a referenciát, de azon belül az egyes mezőket meg lehet. 

Ennek ellenére is úgy értékelem, hogy fontos lépéseket tesz a nyelv, hogy ebbe az irányba tereljen.

## *val* vs *var*

Jellemzően a következők szerint magyarázzák mindezt:
* Ha a *var* kulcsszóval deklarálsz egy változót, akkor az mutable lesz, és az értéke változtatható. 
* Ha azonban a *val*-al teszed ugyanezt, akkor immutable lesz. Ezt követően a fordító nem fogja azt engedni, hogy az értéket megváltoztasd. 

Bizonyosodjunk meg ezekről az állításokkal néhány példával.

Először nézzünk egy *var*-os esetet:
```kotlin
  var number = 12
  println(number) // 12

  number++
  println(number) // 13
```

Most ugyanez *val*-al:
```kotlin
  val number = 12
  println(number)

  number++
  println(number) // Error - Kotlin: Val cannot be reassigned
```

A hibaüzenettel a fordító figyelmeztet arra, hogy nem lehet megváltoztatni egy *val*-al deklarált változó értékét.

Nézzük most ugyanazt egy referencia típussal:
```kotlin
  data class Address(val street: String, val city: String, val country: String)

  val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")

  home.street = "Whatever st. 1033" // Error - Kotlin: Val cannot be reassigned
```

Az előzőhöz hasonló hibaüzenetet fogunk látni.

Ez pedig lemehet tetszőleges mélységig:
```kotlin
  data class Address(val street: String, val city: String, val country: String)
  data class User(val name: String, val address: Address)

  val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")
  val me = User("Gabor", home)

  me.address.street = "Whatever st. 1033" // Error - Kotlin: Val cannot be reassigned
```

Eddig egészen úgy működik, ahogy azt várnánk, itt tényleg immutable.

## Probléma #1: *var*-al deklarált data class mező

Mutatok is erre két példát, ahol kicsit a fejük tetejére fognak állni a dolgok.

Előbb egy objektum-hierachiával:

```kotlin
  data class Address(var street: String, val city: String, val country: String)
  data class User(val name: String, val address: Address)

  val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")
  val me = User("Gabor", home)

  println(me.address)

  me.address.street = "Whatever st. 1033"
  println(me.address)
```

Az előző példával ellentétben most simán meg tudtam változtatni ezt az értéket, pedig mindössze annyit csináltam, hogy az *Address* data class *street* mezőjét *var*-ra állítottam. 

Tehát a *val*-tól nem lett a teljes adatstruktúra immutable!

Illetve hasonló, de kollekció szempontból:

```kotlin
  data class Address(var street: String, val city: String, val country: String)

  val address1 = Address("Matyas kiraly u. 45.", "Kazincbarcika", "Magyarország")
  val address2 = Address("Kossuth Lajos u. 12.", "Baja", "Magyarország")

  val addresses = listOf<Address>(address1, address2)
  println(addresses)

  addresses[0].street = "Valami"
  println(addresses)
```

Nagyon hasonló mint az előző. Nem tudok a kollekcióhoz új elemet hozzáadni, vagy egy meglévőt kivenni, ez nem lehetséges. Azonban a *street* mezőt bármelyik objektum esetében meg tudom változatni. 

## Probléma #2: Copy

Nagyon király dolog, hogy a data classok számos más metódus mellett a *copy()*-t is elkészítik nekünk. Ennek használatával simán lemásolható az objektumok tartalma. Azonban ez nem minden esetben működik úgy, ahogy arra előzetesen számítanál. Máris mutatok két példát, amivel sokkal jobban át fogjátok látni.

Az első példával lássunk egy objektum-hierarchiát, nemrég láttunk ezzel egy problémát, de most mutatok egy újat:

```kotlin
  data class Address(var street: String, val city: String, val country: String)
  data class User(val name: String, var address: Address)

  val address = Address("Matyas kiraly u. 45.", "Kazincbarcika", "Magyarország")
  val user = User("Gabor", address)

  val user2 = user.copy()
  user2.address.street = "Valami"

  println(user) // ... Valami ...
  println(user2) // ... Valami ...
```

A másik is ezt a mintát fogja követni, de ezúttal egy kollekciót használva:

```kotlin
  data class Address(var street: String, val city: String, val country: String)
  data class User(val name: String, var address: Address)

  val address1 = Address("Matyas kiraly u. 45.", "Kazincbarcika", "Magyarország")
  val address2 = Address("Kossuth Lajos u. 12.", "Baja", "Magyarország")

  val addresses = listOf<Address>(address1, address2)
  val addresses2 = addresses.toList()

  addresses2[0].street = "Valami"

  println(addresses)
  println(addresses2)
```

Mindkét esetben lemásoltam a struktúrát, megváltoztattam a szokásos *street* mezőt, és bizony az eredetik is változtak. A háttérben az történik, hogy a *copy()* és a *toList()* sekély másolatot készítenek. Ez pedig azt jelenti, hogy a gyermek elemekből nem készített másolatot, egyszerűen csak átmásolja a referenciát, és ennyi. 

Az ilyen helyzetekre szoktuk mondani, hogy leakel a struktúra, megváltoztatom az egyiket, és meglepetésre a másik is változik.

## Probléma #3: Mi van, ha immutable-t várunk, de mutable jön?

Ez egy trükkös példa lesz, amit könnyű benézni.

data class Border internal constructor(val borderType: BorderType,
                                       val borderPositions: Set<BorderPosition>) {

  companion object {
    fun of(borderType: BorderType = BorderType.SOLID, 
            borderPositions: Set<BorderPosition> = BorderPosition.values().toSet()) =

      Border(borderType, borderPositions.toSet())
  }
}

Amikor építed akkor Set-et vársz, de elfogad MutableSet-et is, mert az a gyereke. Ilyenre nincs felkészítve és felrobbanhat.

## Hogyan védekezhetek az előbbi problémák ellen?

Útravalóul mutatok néhány megoldási ötletet:

**1. probléma**: 
  * Figyelj arra, hogy a *data class*-ok minden mezője *val*-al legyen deklarálva. 
  * Sok helyen egészen odáig mennek, hogy mindehova *val*-t írnak, hacsak nincs egy kifejezett érv a *var* mellett.
  * A unit testek jellemzően kiszűrik a hasonlóakat.

**2. probléma**: 
  * [TODO] deep copy

**3. probléma**: 
  * Készíts defenzív copy-t. Sajnos a nyelv nincs erre felkészítve, bizony pedánsnak kell lenni.

```kotlin
Border(borderType, borderPositions.toSet())
```
