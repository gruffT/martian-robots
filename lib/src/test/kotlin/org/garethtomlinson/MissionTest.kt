@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import org.garethtomlinson.exceptions.MissedPlanetException
import kotlin.test.*

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

    @Test fun shouldThrowABadConfigurationExceptionIfInstructionsAreNotValid() {
        val inputs =
            sequenceOf(
                listOf("1 1 N", "A"),
                listOf("1 1 N", "R L"),
                listOf("1 1 N", "R1L"),
                listOf("1 1 N", "FAR"),
            )
        inputs.forEach {
            val exception = assertFailsWith<BadConfigurationException> { Mission.from(it) }
            assertEquals(
                expected = "A bad configuration has been provided for Instructions: `${it[1]}`",
                actual = exception.message,
            )
        }
    }

    @Test fun shouldReturnARobotAndInstructions() {
        val mission = Mission.from(listOf("1 1 N", "FRL"))
        assertEquals(expected = Location(1, 1), actual = mission.robot.location)
        assertEquals(expected = Orientation.NORTH, actual = mission.robot.orientation)
        assertEquals(
            expected =
                listOf(
                    Instruction.FORWARD,
                    Instruction.RIGHT,
                    Instruction.LEFT,
                ),
            actual = mission.instructions,
        )
    }

    @Test fun shouldThrowAMissedPlanetExceptionIfLandingOutsideOfMarsBounds() {
        val mission = Mission.from(listOf("2 2 N", ""))
        val exception =
            assertFailsWith<MissedPlanetException> {
                mission.execute(Mars.from("1 1"))
            }
        assertEquals(expected = "Robot landed off planet at: 2 2", actual = exception.message)
    }
}
