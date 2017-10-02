package org.codetome.kotlin.workshop.introduction._5_Nullability

class A(val value: Int)

fun foo(a: Any?) = (a as? A)?.value

fun main(args: Array<String>) {
    println(foo(A(42)))   // 42
    println(foo(42))      // null
    println(foo(null))    // null
}