package workshop.kotlin._04_immutability_and_copy

fun main(args: Array<String>) {
    data class Address(var street: String, val city: String, val country: String)
    data class User(val name: String, var address: Address)

    val address1 = Address("Matyas kiraly u. 45.", "Kazincbarcika", "Magyarország")
    val address2 = Address("Kossuth Lajos u. 12.", "Baja", "Magyarország")

    val addresses = listOf<Address>(address1, address2)

    // TODO Copy addresses, alter the value of addresses2[0].street.
    // Both have changed, why?

}