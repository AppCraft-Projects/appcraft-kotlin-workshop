package org.codetome.kotlin.workshop.introduction._3_Classes

// similar to javaCode.Person

class AnotherPerson(val name: String, val age: Int)

fun main(args: Array<String>) {
    // no new keyword, constructor is called as a regular function
    val person = AnotherPerson("Alice", 26)
    println(person.name)
}