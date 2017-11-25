package workshop.kotlin._11_collections

/**
 * Creating a list of Strings.
 * Specifying a generic type is only necessary if the
 * collection is empty.
 */
val myList = listOf<String>()

/**
 * They type (String) is inferred here.
 */
val inferredList = mutableListOf("Foo", "bar")

/**
 * You can create `Map`s out of `Pair`s using `mapOf`.
 */
val somePairs = mapOf(Pair("one", 1), Pair("two", 2))

/**
 * This will contain (1, 2, 3).
 */
val aSet = setOf(1, 2, 1, 2, 3)

/**
 * This will contain "1, 2, 3".
 */
val contentsOfASet = aSet.joinToString()

/**
 * This will contain (1, 3, 3, 5, 6).
 */
val sortedValues = listOf(5, 1, 3, 3, 6).sorted()

// TODO: fix the code below to pass the tests defined in CollectionsTest.kt

val sumOfSortedValues: Int = 0

val preAndPostFixedStringJoinOfInferredList: String = ""

val setFromSortedValues: Set<Int> = setOf()




