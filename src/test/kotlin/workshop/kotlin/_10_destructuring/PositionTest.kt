package workshop.kotlin._10_destructuring

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

@Suppress("UNREACHABLE_CODE")
class PositionTest {

    @Test
    fun shouldProperlyDestructureDataClass() {

        val position = Position(3, 4)

        val column: Int = 0
        val row: Int = 0



        TODO("Change the above code to use a destructuring declaration to set the value of column and row")

        assertThat(column).isEqualTo(3)
        assertThat(row).isEqualTo(4)

    }

    @Test
    fun shouldProperlyDestructureTheReturnValueOfAFunction() {

        val size = Position(3, 4).toSize()

        val columns: Int = 0
        val rows: Int = 0

        TODO("Change the above code to use a destructuring declaration to set the value of columns and rows")

        assertThat(columns).isEqualTo(3)
        assertThat(rows).isEqualTo(4)

    }
}