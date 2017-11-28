package workshop.kotlin._04_immutability_and_copy

fun main(args: Array<String>) {
    data class Address(var street: String, val city: String, val country: String)
    data class User(val name: String, var address: Address)

    val address = Address("Matyas kiraly u. 45.", "Kazincbarcika", "Magyarország")
    val user = User("Gabor", address)


    // TODO Copy user, alter the value of user2.address.street.
    // Both have changed, why?
}