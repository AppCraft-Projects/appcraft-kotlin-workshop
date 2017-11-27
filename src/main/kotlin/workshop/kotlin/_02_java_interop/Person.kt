package workshop.kotlin._02_java_interop

class Person private constructor(val name: String, val surname: String) {

    private constructor(builder: Builder) : this(builder.name, builder.surname)

    companion object {
        @JvmStatic
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        lateinit var name: String
        lateinit var surname: String

        fun name(init: Builder.() -> String) = apply { name = init() }

        fun surname(init: Builder.() -> String) = apply { surname = init() }

        fun build() = Person(this)
    }
}