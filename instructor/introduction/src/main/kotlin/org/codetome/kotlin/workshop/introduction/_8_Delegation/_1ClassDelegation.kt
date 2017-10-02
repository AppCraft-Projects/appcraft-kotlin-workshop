package org.codetome.kotlin.workshop.introduction._8_Delegation

import org.codetome.kotlin.workshop.introduction._Shared.*


interface Repository {
    fun getById(id: Int): Customer
    fun getAll(): List<Customer>
}

interface Logger {
    fun logAll()
}

class Controller(repository: Repository, logger: Logger) : Repository by repository, Logger by logger {


}