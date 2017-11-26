# Kódátalakítás Java-ról Kotlin-ra

## Az alap probléma

Egy új nyelv esetében, nyilvánvaló nehézséget okoz, ha a régi nyelven írt dolgokat az újra át kell írni. Itt jön jól minden tooling segítség, és ebben a JetBrains emberei zseniálisak.

Mind az IntelliJ IDEA-ba, mind az Android Studioban beépítve megtalálható egy elég frankó átalakító eszköz. Két irányból is elérhető:
- Mint action is elérhető (Shift+Cmd+A), majd *Convert Java File to Kotlin File*
- Illetve a főmenüben, *Code / Convert Java File to Kotlin File* parancs

Ennyi az egész. 

Példaképp alakítsunk át egy darabka Java kódot Koltinra.

Így néz ki Java-ban:
```java
public class JavaCode {
  public String toJSON(Collection<Integer> collection) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Iterator<Integer> iterator = collection.iterator();
    while (iterator.hasNext()) {
      Integer element = iterator.next();
      sb.append(element);
      if (iterator.hasNext()) {
          sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}
```

Így pedig Koltinban:
```kotlin
class JavaCode {
  fun toJSON(collection:Collection<Int>):String {
    val sb = StringBuilder()
    sb.append("[")
    val iterator = collection.iterator()
    while (iterator.hasNext())
    {
      val element = iterator.next()
      sb.append(element)
      if (iterator.hasNext())
      {
        sb.append(", ")
      }
    }
    sb.append("]")
    return sb.toString()
  }
}
```

Itt egyelőre minimális a különbség, de majd meglátjátok, össze fog az eredmény összemenni még sokkal-sokkal jobban is.

## Tapasztalatok

Jó eszközről van szó, általánosságban jó szokott lenni az eredemény, de nem mindig fog az ízlésed szerint dolgozni. Épp ezért elő fog olyan fordulni, hogy bele kell nyúlnod kézzel. Ilyenkor persze nem árt némi Kotlin tudás. 

Azt szoktuk javasolni, hogy érdemes kisebb egységekben (funkció, legfeljebb osztály) haladni, így sokkal könnyebb lesz később átlátni, hogy mi történt, és korrigálni, ha valami nem az igazi.

## Időnként azért képes csúnya dolgokat csinálni

- Ami nekem nem tetszik, hogy az interfacebe rak fieldeket (get valami, set valamiból fieldek) [TODO] keressetek példát
- Van hogy hiábra fut, kommenteket nem vesz át [TODO] keressetek példát
- A bitműveleteket nem kezeli mindig jól (nézni fogod, hogy mi az shl - mondjuk shift left) [TODO] keressetek példát
- Array-ek konvertálása nem mindig az igazi, pl ByteArray, ami spéci Kotlinos cucc [TODO] keressetek példát

