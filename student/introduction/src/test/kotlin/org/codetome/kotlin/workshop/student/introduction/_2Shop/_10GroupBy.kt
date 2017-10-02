package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _10GroupBy {
    @Test fun testGroupCustomersByCity() {
        assertEquals(groupedByCities, shop.groupCustomersByCity())
    }
}
