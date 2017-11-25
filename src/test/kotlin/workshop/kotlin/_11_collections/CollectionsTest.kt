package workshop.kotlin._11_collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CollectionsTest {

    @Test
    fun sumOfSortedValuesShouldBe18() {

        assertThat(sumOfSortedValues).isEqualTo(18)
    }

    @Test
    fun preAndPostFixedStringJoinOfInferredListShouldHaveParensAroundIt() {

        assertThat(preAndPostFixedStringJoinOfInferredList).isEqualTo("(Foo, bar)")
    }

    @Test
    fun setFromSortedValuesShouldContainProperElements() {

        assertThat(setFromSortedValues).containsExactly(1, 3, 5, 6)
    }
}