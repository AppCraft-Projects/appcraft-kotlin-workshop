package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _8Zip {
    @Test
    fun testZipNameAndCustomer() {
        assertEquals(customers.toList(), shop.zipNameAndCustomer())
    }
}