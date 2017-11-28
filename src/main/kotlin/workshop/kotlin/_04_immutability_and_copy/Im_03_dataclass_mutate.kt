package workshop.kotlin._04_immutability_and_copy


fun main(args: Array<String>) {

    data class Address(val street: String, val city: String, val country: String)

    val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")

    // TODO alter home.street
}