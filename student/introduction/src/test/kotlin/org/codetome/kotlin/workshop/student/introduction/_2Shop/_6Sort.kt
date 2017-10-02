package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _6Sort {
    @Test fun testGetCustomersSortedByNumberOfOrders() {
        assertEquals(sortedCustomers, shop.getCustomersSortedByNumberOfOrders())
    }
}
