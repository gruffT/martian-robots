package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import org.garethtomlinson.exceptions.BigPlanetException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MarsTest {
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
