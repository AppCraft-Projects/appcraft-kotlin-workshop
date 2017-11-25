package workshop.kotlin._07_nullability

import org.junit.Test

class QueueTroubleTest {

    @Test
    fun shouldNotThrowNPEWhenGetFirstIsCalled() {
        QueueTrouble().fetchFirst()
    }
}