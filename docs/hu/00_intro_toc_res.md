# Bevezető

## Néhány gondolat az elejére

Mindketten nagyon szeretjük a nyelvet használni, és úgy érezzük nagyon szép jövő áll még előtte. Pláne majd amikor igazán megerősödik a multi-platformos vonal is. 

Ezért is fogalmazódott meg bennünk még tavasszal workshop ötlete. A célunk egy olyan anyag összerakása volt, ahol a Java fejlesztőknek meg tudjuk mutatni a Kotlin nyelv előnyeit. Apró különbség, de annó még egy 2-3 napos teljes anyagot kezdtünk kidolgozni.

Majd nyáron elkezdtünk beszélgetni erről Gálffy Csabával, és felvetődött, hogy ez az egész szuper lenne a HWSW konferenicán is. Persze kicsit másképp, nem lesz rá 2-3 nap, inkább három, de úgy gondoltuk meg lehet ezt csinálni. Sokkal pörgősebb, fókuszáltabb egy jó áttekintést tudunk ad a nyelvről megszerzett tapasztalatainkból.

Fontos hangsúlyozni, hogy most törekszünk teljességre inkább a mindennapokban elterjedtebb, illetve jól használható részekre koncentrálunk. Nem kifejezetten a domain specifikus alkalmazást, avagy az Android vagy mondjuk a Spring fejlesztést tárgyaljuk. Habár az előbbiről az Anko kapcsán lesz szó. Inkább olyan tudást adunk át, amelyet mindkét területen jól lehet használni.

Egyébként folyamatosan törekedtünk arra, hogy minél inkább életszerű kódokkal dolgozzunk, ezért a példa kódok jelentős részét Ádám egyik projektéből, a Zirconból emeltük át, ami erre található: [http://bit.ly/adam-zircon](http://bit.ly/adam-zircon).

Ezek alapján biztos érzitek, hogy zúzós dologra vállalkoztok a következő órákban, és bizony feszes lesz a tempó. Ne számítsatok egy hagyományos elmélyülős-mentorálás workshopra. Inkább tekintsetek erre úgy, mint egy interaktív előadásra, ami azért egészen mélyen tud a témában elmerülni.

## Tartalom

A következő témákról lesz tehát szó a következő órákban:

- [A Java-Kotlin konvertálás](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/01_convert_from_java.md)
- [Együttműködés a Java-val](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/02_java_interop.md)
- [Adat osztályok](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/03_data_classes.md)
- [Immutabilitás és másolás](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/04_immutability_and_copy.md)
- [Típus Kikövetkeztetés, `Any` és `Nothing`](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/05_type_inference_any_nothing.md)
- [Epxression, statement, `if` és `when`](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/06_expression_statement_if_and_when.md)
- [Null kezelés](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/07_nullability.md)
- [Funkciók](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/08_functions.md)
- [Kiegészítő funkciók](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/09_extension_functions.md)
- [Destruktúrálás](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/10_destructuring.md)
- [Kollekciók](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/11_collections.md)
- [Funkcionális programozás](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/12_functional_programming.md)
- [Anko](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/13_anko.md)
- [Mi maradt ki?](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/14_missing.md)
- [Linkgyűjtemény](https://github.com/AppCraft-Projects/appcraft-kotlin-workshop/blob/master/docs/hu/15_links.md)
- Q&A

## Anyagok

Számos módon próbálunk nektek segíteni, hogy a nagy tempó ellenére jól tudjatok minket követni. Itt találtok egy jó kis előkészített repot: [http://bit.ly/hwsw-kotlin-workshop](http://bit.ly/hwsw-kotlin-workshop). 

Kérlek ezt húzzátok le magatokhoz, és a legutolsó commitot checkoutoljátok ki

**Mit találtok itt?**
- Mindegyik lépéshez a forráskódokat, hol kisebb, hol nagyobb kód darabokról lesz szó.  
- Mindegyik lépéshez a jegyzeteket. 

Ezeket használva nem kell a jegyzeteléssel időt tölteni, mert szinte minden le van írva. Valamint akkor sem lesz baj, ha valahol lemaradtok, mindent lehet később otthonról pótolni.

Kérdés esetén minket a következő email címeken értek el:
gabor (pötty) orosz (kukac) appcraft (pötty) hu
adam (pötty) arold (kukac) appcraft (pötty) hu

Ennyit a bevezetésről, csapjunk bele!
