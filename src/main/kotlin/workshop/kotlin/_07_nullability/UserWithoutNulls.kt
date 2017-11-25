package workshop.kotlin._07_nullability

data class UserWithoutNulls(val firstName: String,
        // this parameter can't be null
                            val lastName: String,
                            val addresses: List<Address> = listOf()) {

    data class Address(val city: String)

    companion object {
        fun fetchFirstCity(user: UserWithoutNulls) = user.addresses.first().city
    }
}