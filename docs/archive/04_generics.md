# Generikus típusok

A Java-hoz hasonlóan a Kotlin is támogatja a generikus típusokat:

```kotlin
class Node<T>(t: T) {
    var value = t
}
```

A Java-val ellentétben itt nem kell kézzel behelyettesíteni a típusparamétert, mert a fordító képes azt kitalálni a kontextusból:

```kotlin
val node = Node(1) // Az `1` típusa `Int`, így a fordító automatikusan tudni fogja, hogy egy `Node<Int>`-ről van szó
```



## Generikus függvények

## Generikus osztályok

## Invariancia, kovariancia, kontravariancia