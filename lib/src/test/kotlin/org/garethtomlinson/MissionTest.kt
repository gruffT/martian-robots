package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import kotlin.test.Test
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

    @Test fun shouldThrowABadConfigurationExceptionIfRobotConfigIsNotCorrect() {
        val inputs =
            sequenceOf(
                listOf("", ""),
                listOf("1 E", ""),
                listOf("1 1", ""),
                listOf("a 1 E", ""),
                listOf("1 a E", ""),
                listOf("1 1 1", ""),
                listOf("1 1 A", ""),
            )
        inputs.forEach {
            val exception = assertFailsWith<BadConfigurationException> { Mission.from(it) }
            assertEquals(
                expected = "A bad configuration has been provided for Robot: `${it[0]}`",
                actual = exception.message,
            )
        }
    }

    @Test fun shouldReturnARobot() {
        val robot = Mission.from(listOf("1 1 N", "")).robot
        assertEquals(expected = Location(1, 1), actual = robot.location)
        assertEquals(expected = Orientation.N, actual = robot.orientation)
    }
}
