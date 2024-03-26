@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.garethtomlinson.mission

import org.garethtomlinson.Orientation
import org.garethtomlinson.Robot
import org.garethtomlinson.exceptions.BadConfigurationException
import org.garethtomlinson.exceptions.BigPlanetException
import kotlin.test.*

class MarsTest {
    @Test fun shouldReturnInsideBoundsTrueIfRobotXIsInClosedOpenBound() {
        val mars = Mars.from("1 1")

        fun robotFactory(x: Int): Robot = Robot.with(x, 0, Orientation.NORTH)
        assertFalse(mars.insideBounds(robotFactory(-1)))
        assertTrue(mars.insideBounds(robotFactory(0)))
        assertTrue(mars.insideBounds(robotFactory(1)))
        assertFalse(mars.insideBounds(robotFactory(2)))
    }

    @Test fun shouldReturnInsideBoundsTrueIfRobotYIsInClosedOpenBound() {
        val mars = Mars.from("1 1")

        fun robotFactory(y: Int): Robot = Robot.with(0, y, Orientation.NORTH)
        assertFalse(mars.insideBounds(robotFactory(-1)))
        assertTrue(mars.insideBounds(robotFactory(0)))
        assertTrue(mars.insideBounds(robotFactory(1)))
        assertFalse(mars.insideBounds(robotFactory(2)))
    }

    @Test fun shouldCreateAMarsFromAConfigurationString() {
        val mars = Mars.from("1 2")

        assertEquals(expected = 2, actual = mars.width)
        assertEquals(expected = 3, actual = mars.height)
    }

    @Test fun shouldAllowWhiteSpacePaddingOfConfigurationString() {
        val mars = Mars.from("  1 2         ")

        assertEquals(expected = 2, actual = mars.width)
        assertEquals(expected = 3, actual = mars.height)
    }

    @Test fun shouldAllowMarsOfWidthAndHeight50() {
        val mars = Mars.from("50 50")

        assertEquals(expected = 51, actual = mars.width)
        assertEquals(expected = 51, actual = mars.height)
    }

    @Test fun shouldThrowAnBigPlanetExceptionForMarsWidthLargerThan50() {
        val exception =
            assertFailsWith<BigPlanetException>(
                block = {
                    Mars.from("51 1")
                },
            )
        assertEquals(expected = """Planet coordinate greater than 50 specified: `51 1`""", actual = exception.message)
    }

    @Test fun shouldThrowAnBigPlanetExceptionForMarsHeightLargerThan50() {
        val exception =
            assertFailsWith<BigPlanetException>(
                block = {
                    Mars.from("1 51")
                },
            )
        assertEquals(expected = """Planet coordinate greater than 50 specified: `1 51`""", actual = exception.message)
    }

    @Test fun shouldThrowABadConfigurationExceptionForMalformedInput() {
        val malformedInputs =
            sequenceOf(
                "1 a",
                "a 1",
                "a a",
                "1",
                "a",
                "",
                // Robot Configuration
                "1 1 E",
                // Instruction sequence
                "RFRFRFRF",
            )
        malformedInputs.forEach { input ->
            val exception =
                assertFailsWith<BadConfigurationException>(
                    block = {
                        Mars.from(input)
                    },
                )
            assertEquals(expected = """A bad configuration has been provided for Mars: `$input`""", actual = exception.message)
        }
    }
}
