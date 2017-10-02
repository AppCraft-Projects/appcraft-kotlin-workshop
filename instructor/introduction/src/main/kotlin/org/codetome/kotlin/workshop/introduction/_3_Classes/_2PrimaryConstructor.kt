package org.codetome.kotlin.workshop.introduction._3_Classes

/* short syntax */
class Person1(val name: String)

/* full syntax */
//           constructor parameter
class Person2(name: String) {

    // property
    val name: String

    // constructor body
    init {
        this.name = name
    }
}
