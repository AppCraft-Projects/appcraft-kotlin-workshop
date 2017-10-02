package org.codetome.kotlin.workshop.student.introduction._2Shop


import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _4FlatMap {
    @Test fun testGetOrderedProductsSet() {
        assertEquals(setOf(idea), customers[reka]!!.orderedProducts)
    }

    @Test fun testGetAllOrderedProducts() {
        assertEquals(orderedProducts, shop.allOrderedProducts)
    }
}
