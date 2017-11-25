package workshop.kotlin._02_java_interop;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class PositionTest {

    @Test
    public void shouldBeAbleToAccessTopLeftCornerFromKotlinClass() {

        fail("Fix the `Position` data class so that uncommenting the code below will compile");

//        assertThat(Position.UNKNOWN).isEqualTo(new Position(Integer.MAX_VALUE, Integer.MAX_VALUE));
//        assertThat(Position.of(2, 3)).isEqualTo(new Position(2, 3));
    }
}
