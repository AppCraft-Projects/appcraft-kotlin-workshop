package workshop.kotlin._02_java_interop;

import kotlin.Unit;
import org.junit.Test;

public class PersonTest {

    @Test
    public void personBuilderTest() {

        Person.create((builder) -> {
            builder.name = "John";
            builder.surname = "Doe";
            return Unit.INSTANCE;
        });
    }

    @Test
    public void classicBuilderTest() {
        PersonWithClassicBuilder.Builder.create()
                .name("John")
                .surname("Doe")
                .build();
    }
}
