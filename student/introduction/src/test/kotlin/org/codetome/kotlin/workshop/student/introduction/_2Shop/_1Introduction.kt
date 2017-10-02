package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _1Introduction {
    @Test fun testSetOfCustomers() {
        assertEquals(customers.values.toSet(), shop.getSetOfCustomers())
    }
}
