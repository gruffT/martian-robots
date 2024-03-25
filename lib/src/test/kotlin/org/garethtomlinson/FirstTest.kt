package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class FirstTest {
    @Test fun shouldRunTests() {
        assertEquals(
            expected = "World",
            actual = First.hello(),
        )
    }
}
