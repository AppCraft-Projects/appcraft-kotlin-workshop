package org.codetome.kotlin.workshop.introduction._4_Hierarchies


open class Person() {
    open fun validate() {

    }
}

open class OpenCustomer() : Person() {
    final override fun validate() {

    }
}

class SpecialCustomer : OpenCustomer() {

}

data class CustomerEntity(val name: String) : Person()

fun main(args: Array<String>) {

    val customer = OpenCustomer()

    customer.validate()
}