# Adat osztályok

## A Kotlin módszer

Az adat osztályok sok nyelven sok mindent jelenthetnek, ezért nincs a Java-ból alapból adat osztályok létrehozására lehetőség.
Ezzel szemben a Kotlin készítői pragmatikusak voltak és kiválasztottak a sok lehetőség közül egyet, ami szerintük megfelelő.
Alapvetően elmondható, hogy a nyelv megalkotói figyelembe vették az Effective Java c. könyvben leírtakat és ez sokszor visszaköszön
a megoldásaikban.

Az adat osztályok Kotlin-ban nagyon hasonlóak ahhoz, amit a Lombok ad nekünk Java oldalról: olyan POJO-kat tudunk vele létrehozni, amik

- tartalmaznak beépített `hashCode`, `equals` és `toString` metódusokat,
- gettereket és settereket, valamint
- destruktúrálhatók

Fontos, hogy az adat osztályok Kotlin-ban nem megváltoztathatatlanok (tehát nem érték osztályok).

Lássuk hogy néz ki egy tipikus POJO Java-ban:

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

Ha ezt a kódot átmásoljuk egy Kotlin osztályba az átalakító segítségével, akkor az alábbi eredményt látjuk:

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

és innen már csak egy lépés eljutni egy adat osztályig:

```kotlin
data class Size(val columns: Int, val rows: Int)
```

A Kotlin fordító a mezőink alapján fogja kigenerálni a `hashCode`, `equals`, `toString` metódusokat és a getter / setter párokat.