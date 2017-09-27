package org.jtebrains.kotlinworkshop.student.introduction._2Shop


import org.jetbrains.kotlinworkshop.student.introduction._2Shop.*
import org.junit.jupiter.api.*
import kotlin.test.*

class _3AllAnyAndOtherPredicates {
    @Test fun testCustomerIsFromCity() {
        assertTrue(customers[lucas]!!.isFrom(Canberra))
        assertFalse(customers[lucas]!!.isFrom(Budapest))
    }

    @Test fun testAllCustomersAreFromCity() {
        assertFalse(shop.checkAllCustomersAreFrom(Canberra))
    }

    @Test fun testAnyCustomerIsFromCity() {
        assertTrue(shop.hasCustomerFrom(Canberra))
    }

    @Test fun testCountCustomersFromCity() {
        assertEquals(2, shop.countCustomersFrom(Canberra))
    }

    @Test fun testAnyCustomerFromCity() {
        assertEquals(customers[lucas], shop.findAnyCustomerFrom(Canberra))
        assertEquals(null, shop.findAnyCustomerFrom(City("Chicago")))
    }
}
