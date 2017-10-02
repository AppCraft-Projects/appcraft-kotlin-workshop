package org.codetome.kotlin.workshop.introduction._4_Hierarchies

import org.codetome.kotlin.workshop.introduction._Shared.*

interface CustomerRepository {
    val isEmpty: Boolean
        get() = true

    fun store(obj: Customer) {
        // implement code to store
    }

    fun getById(id: Int): Customer
}

