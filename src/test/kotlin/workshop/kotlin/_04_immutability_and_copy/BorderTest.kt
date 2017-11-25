package workshop.kotlin._04_immutability_and_copy

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BorderTest {

    @Test
    fun shouldNotBeModifiedWhenUnderlyingSetChanges() {

        val mutablePositions = mutableSetOf(BorderPosition.BOTTOM)
        val expectedPositions = mutablePositions.toSet()
        val result = Border.of(
                borderType = BorderType.DASHED,
                borderPositions = mutablePositions)

        mutablePositions.add(BorderPosition.TOP)

        assertThat(result.borderPositions).containsExactlyElementsOf(expectedPositions)
    }
}