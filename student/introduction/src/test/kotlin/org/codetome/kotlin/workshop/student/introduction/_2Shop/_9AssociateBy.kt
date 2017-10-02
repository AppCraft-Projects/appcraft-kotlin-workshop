package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _9AssociateBy {
    @Test
    fun testZipNameAndCustomer() {
        assertEquals(customers, shop.associateCustomersByName())
    }
}