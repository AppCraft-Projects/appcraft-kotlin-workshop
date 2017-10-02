package org.codetome.kotlin.workshop.introduction._4_Generics

import org.codetome.kotlin.workshop.introduction._Shared.*


interface Repository<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
}

fun main(args: Array<String>) {

    val customerRepo = CustomerRepository<Customer>()

    val customer = customerRepo.getById(10)
    val customers = customerRepo.getAll()


}
