# Mutable és immutable típusok

## Tartalom

- A copy jön a data classokkal... idea-ban mutasd be, ez nagyon egyszerű

– Az hogy valami val, a referencia lesz immutable, mint a final... de ami benne van, az lehet mutable... nincs igazi immutable típus Kotlinban, neked kell csinálni
- Leakelhet a mutabilitás

- Kellenek példák
- Hogy néz ki amikor ténylegesen immutable
- Hogy néz ki, amikor nem, és leakel, ezt be kell mutatni
- Hogyan lehet kiküszöbölni a leakelést

## Bevezető gondolatok

> **Mit is jelent az immutable?** Röviden annyit nem tudod megváltoztatni az értékét, hanem a változásokkal egy új jön létre. Ezzel alapvetően számos olyan nehezen felderíthető hibának lehet elébe menni, amelyek az alkalmazás állaptának szabad manipulálásából következik. Többszálú környezetben ez kiváltképp nehezen átlátható.

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

Ez pedig lemehet tetszőleg mélységig:
```kotlin
  data class Address(val street: String, val city: String, val country: String)
  data class User(val name: String, val address: Address)

  val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")
  val me = User("Gabor", home)

  me.address.street = "Whatever st. 1033" // Error - Kotlin: Val cannot be reassigned
```

Eddig egészen úgy működik, ahogy azt várnánk, itt tényleg immutable.

## De nem mindig az

Mutatok egy példát, ahol kicsit a fejük tetejére fognak állni a dolgok:

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

Mutatok még egy példát:

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

Az ilyen helyzetekre szoktuk mondani, hogy leakel a struktúra.

## Hogyan védekezhetek?

[TODO]
