package workshop.kotlin._02_java_interop

import org.junit.Test

class PersonKTest {

    @Test
    fun buildPersonTest() {

        Person.create {
            name = "John"
            surname = "Doe"
        }
    }

    @Test
    fun classicBuilderTest() {

        PersonWithClassicBuilder.Builder.create()
                .name("John")
                .surname("Doe")
                .build()
    }
}