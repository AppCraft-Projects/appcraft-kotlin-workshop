package workshop.kotlin._04_immutability_and_copy


fun main(args: Array<String>) {

    data class Address(val street: String, val city: String, val country: String)
    data class User(val name: String, val address: Address)

    val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")
    val me = User("Gabor", home)

    // TODO alter me.address.street
}