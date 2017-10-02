package org.codetome.kotlin.workshop.student.introduction._2Shop

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class _11Partition {
    @Test fun testGetCustomersWhoHaveMoreUndeliveredOrdersThanDelivered() {
        assertEquals(setOf(customers[reka]), shop.getCustomersWithMoreUndeliveredOrdersThanDelivered())
    }
}
