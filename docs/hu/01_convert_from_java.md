# Kódátalakítás Java-ról Kotlin-ra

## Az alap probléma

Egy új nyelv esetében, nyilvánvaló nehézséget okoz, ha a régi nyelven írt dolgokat az újra át kell írni. Itt jön jól minden tooling segítség, és ebben a JetBrains emberei zseniálisak.

Mind az IntelliJ IDEA-ba, mind az Android Studioban beépítve megtalálható egy elég frankó átalakító eszköz. Két irányból is elérhető:
- Mint action is elérhető (Shift+Cmd+A), majd *Convert Java File to Kotlin File*.
- Illetve a főmenüben, *Code / Convert Java File to Kotlin File* parancs.
- Valamint, és ez igazi *MAGIC*, copy-paste-el is működik.
- Van egy kis online eszköz is, [itt](https://try.kotlinlang.org/). Jobb felül keresd a Convert from Java gombot.

Ennyi az egész. 

Mindenek előtt, egy kis projekt konfiguráció, ha egy *Java* projektet szeretnél átalakítani, akkor a *build.gradle* file-on érdemes egy kicsit alakítani, hogy befogadja a *Kotlin*-t:

```groovy
buildscript {
    // ...

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
	}
}

apply plugin: 'kotlin'

// ...

dependencies {
	compile("org.jetbrains.kotlin:kotlin-stdlib-jre8:${kotlinVersion}")

    // ...
}
```

Most egyébként mi eleve Kotlinos projektet generáltunk, így ez jól is van így.

Következő lépésként lássunk egy gyakorlati példát, alakítsunk át egy darabka Java kódot Koltinra. 

Így néz ki eredetileg Java-ban:
```java
public class Size {

    private final int columns;
    private final int rows;

    public Size(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return getColumns() == size.getColumns() &&
                getRows() == size.getRows();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumns(), getRows());
    }

    @Override
    public String toString() {
        return "Size{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }
}
```

Majd ilyen lesz Koltinban:
```kotlin
class Size(val columns: Int, val rows: Int) {

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val size = o as Size?
        return columns == size!!.columns && rows == size.rows
    }

    override fun hashCode(): Int {
        return Objects.hash(columns, rows)
    }

    override fun toString(): String {
        return "Size{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}'
    }
}
```

Már így is kijön egy jelentősebb különbség, de majd meglátjátok, össze fog az eredmény összemenni még sokkal-sokkal jobban is.

## Tapasztalatok

Jó eszközről van szó, általánosságban jó szokott lenni az eredemény, de nem mindig fog az ízlésed szerint dolgozni. Épp ezért elő fog olyan fordulni, hogy bele kell nyúlnod kézzel. Ilyenkor persze nem árt némi Kotlin tudás. 

Azt szoktuk javasolni, hogy érdemes kisebb egységekben (funkció, legfeljebb osztály) haladni, így sokkal könnyebb lesz később átlátni, hogy mi történt, és korrigálni, ha valami nem az igazi.

## Időnként azért képes csúnya dolgokat csinálni

- Ami nekem nem tetszik, hogy az interfacebe rak fieldeket (get valami, set valamiból fieldek).
- Van hogy hiábra fut, kommenteket nem vesz át.
- A bitműveleteket nem kezeli mindig jól (nézni fogod, hogy mi az shl - mondjuk shift left).
