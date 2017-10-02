package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _5MaxMin {
    @Test fun testCustomerWithMaximumNumberOfOrders() {
        assertEquals(customers[reka], shop.getCustomerWithMaximumNumberOfOrders())
    }

    @Test fun testTheMostExpensiveOrderedProduct() {
        assertEquals(rubyMine, customers[nathan]!!.getMostExpensiveOrderedProduct())
    }
}
