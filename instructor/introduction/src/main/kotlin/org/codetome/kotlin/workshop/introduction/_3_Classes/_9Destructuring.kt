package org.codetome.kotlin.workshop.introduction._3_Classes


fun main(args: Array<String>) {

    val (country, city) = Pair("Spain", "Madrid")


    val (country1, city1, continent1) = Triple("Spain", "Madrid", "Europe")

    val (age, _) = Person("Joe Smith", 42)

}