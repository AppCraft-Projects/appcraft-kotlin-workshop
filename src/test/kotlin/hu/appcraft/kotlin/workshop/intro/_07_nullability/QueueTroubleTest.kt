package hu.appcraft.kotlin.workshop.intro._07_nullability

import org.junit.Test

class QueueTroubleTest {

    @Test
    fun shouldNotThrowNPEWhenGetFirstIsCalled() {
        QueueTrouble().fetchFirst()
    }
}