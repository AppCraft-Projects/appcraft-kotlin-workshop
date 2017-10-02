package org.codetome.kotlin.workshop.student.introduction._3TaxiPark


import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class _3TestFrequentPassengers {
    @Test
    fun testOnlyPair() {
        val park = taxiPark(1..1, 1..1, order(1, 1), order(1, 1))
        assertEquals(passengers(1), park.findFrequentPassengers(driver(1)))
    }

    @Test
    fun testFrequentPassengers() {
        val park = taxiPark(1..2, 1..4, order(1, 1), order(1, 1), order(1, 1, 3), order(1, 3), order(1, 2), order(2, 2))
        assertEquals(passengers(1, 3), park.findFrequentPassengers(driver(1)))
    }

    @Test
    fun testNoFrequentPassengers() {
        val park = taxiPark(1..2, 1..4, order(1, 1), order(1, 1), order(1, 1, 3), order(1, 3), order(1, 2), order(2, 2))
        assertTrue(park.findFrequentPassengers(driver(2)).isEmpty())
    }
}