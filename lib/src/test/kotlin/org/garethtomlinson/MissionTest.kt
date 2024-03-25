package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MissionTest {
    @Test fun shouldThrowABadConfigurationExceptionIf2LinesArentGiven() {
        val inputs =
            sequenceOf(
                listOf(),
                listOf(""),
                listOf("", "", ""),
            )
        inputs.forEach {
            val exception = assertFailsWith<BadConfigurationException> { Mission.from(it) }
            assertEquals(
                expected = "A bad configuration has been provided for Mission: `Incorrect number of inputs given`",
                actual = exception.message,
            )
        }
    }
}
