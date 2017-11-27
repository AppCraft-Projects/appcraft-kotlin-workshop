package workshop.kotlin._02_java_interop

data class PersonWithClassicBuilder(val name: String, val surname: String) {

    class Builder(var name: String = "", var surname: String = "") {

        fun name(name: String) = also { this.name = name }

        fun surname(surname: String) = also { this.surname = surname }

        fun build() = PersonWithClassicBuilder(name, surname)

        companion object {

            @JvmStatic
            fun create() = Builder()
        }
    }
}