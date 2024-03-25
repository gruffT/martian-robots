package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class MarsTest {
    @Test fun shouldCreateAMarsFromAConfigurationString() {
        val mars = Mars.from("1 2")

        assertEquals(expected = 2, actual = mars.width)
        assertEquals(expected = 3, actual = mars.height)
    }
}
