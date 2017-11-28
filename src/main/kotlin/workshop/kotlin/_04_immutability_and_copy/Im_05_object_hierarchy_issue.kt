package workshop.kotlin._04_immutability_and_copy

fun main(args: Array<String>) {

    data class Address(var street: String, val city: String, val country: String)
    data class User(val name: String, val address: Address)

    val home = Address("Matyas kiraly u. 45", "Kazincbarcika", "Magyarország")
    val me = User("Gabor", home)

    println(me.address)

    // TODO alter the value of me.address.street.
    // It can be changed, why?
}