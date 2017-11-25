package workshop.kotlin._07_nullability


data class UserWithNulls(val firstName: String?,
        // String? means that it is either a String object or a null
                         val lastName: String?,
                         val addresses: List<Address> = listOf()) {

    data class Address(val city: String?)

    companion object {
        fun fetchFirstCity(user: UserWithNulls?): String? {
            user?.addresses?.forEach { it.city?.let { return it } }
            return null
        }
    }
}