# Kotlin alapok

Ez a modul bevezető információkkal szolgál a Kotlin használatához. Az alábbiakban részletezni fogjuk a Java -> Kotlin
konverter használatát és pár alepvető eszközt, amit a Kotlin ad nekünk.

## A Java -> Kotlin konverter használata

Amennyiben a fejlesztéshez IDEA-t használunk, úgy rendelkezésünkre áll a [Java -> Kotlin konverter](https://www.jetbrains.com/help/idea/converting-a-java-file-to-kotlin-file.html).
Ennek a használata rendkívül egyszerű:

- Jelöljünk ki egy java osztályt.
- Másoljuk a tartalmát a vágólapra.
- Illesszük be a kívánt Kotlin (`.kt`) fájlba a tartalmat.
- Rövid várakozás után megjelenik a konvertált kód.

> A Kotlin konvertáló nem mindig ad tökéletes eredményt, néha nem forduló kód az eredmény. Ebben az esetben próbáljuk meg a Java osztályt függvényenként konvertálni.

## Kulcsszó argumentumok

A Kotlin lehetővé teszi számunkra *kulcsszó argumentumok* használatát. Ez a jellemző már ismert lehet más nyelvekből (pl. Python).
Használatuk nagyon egyszerű. Nézzük meg az alábbi példát:

```kotlin
// definiálunk egy függvényt két paraméterrel
fun foo(param1: String, param2: Int) {
    // ...
}

// majd meghívjuk a függvényt, de a függvényhívásban a paramétereket a megszokott mód helyett kulcsszavakkal adjuk át:
foo(param2 = 1, param1 = "Qux")
```

A kulcsszó paraméterek hasznosak lehetnek több esetben

- Ha komibnálva használjuk alapértelmezett argumentumokkal (lásd alább)
- Ha egy függvénynek sok paramétere van és szeretnénk látni, hogy melyik paraméter melyik névhez tartozik
- Ha fel akarunk készülni arra, hogy az implementáció változni fog (paraméterek sorrendje változik, vagy új paraméterek kerülnek be)

## Alapértelmezett argumentumok

Az *alapértelmezett argumentumok* lehetővé teszik számunkra, hogy a függvényeinkben és az osztályainkban megadhassunk alapértelmezett értékeket
a paramétereiknek. Ez hasonló a Java-ból már ismert *túltöltésre*, de annál lényegesen könnyebben használható és kevésebb lehetőséget ad hibákra:

```kotlin
fun foo(param1: String = "bar", param2: Int = 0) {
    // ...
}

// használat:
foo(param1 = "qux")
```

A fenti példában átadjuk a `param1` paramétert kulcsszó paraméterként, és a `param2` megadását elhagyjuk. Ilyenkor az alapértelmezett érték (`0`) kerül behelyettesítésre függvényhíváskor.

## Lambdák

A Kotlin támogatja a *Java 8*-ból már ismert *lambdákat*. Ezek gyakorlatilag névtelen függvények (Java 8 előtt SAM (Single Abstract Method) interfészekkel működött).


## String template-k

## Adat osztályok

## Nullozható típusok

