package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class OrientationTest {
    @Test fun shouldReturnNorthForN() {
        assertEquals(expected = Orientation.NORTH, actual = Orientation.from("N"))
    }

    @Test fun shouldReturnEastForE() {
        assertEquals(expected = Orientation.EAST, actual = Orientation.from("E"))
    }

    @Test fun shouldReturnSouthForS() {
        assertEquals(expected = Orientation.SOUTH, actual = Orientation.from("S"))
    }

    @Test fun shouldReturnWestForW() {
        assertEquals(expected = Orientation.WEST, actual = Orientation.from("W"))
    }

    @Test fun shouldThrowBadConfigurationExceptionForUnknownInput() {
        val exception = assertFailsWith<BadConfigurationException> { Orientation.from("?") }
        assertEquals(expected = "A bad configuration has been provided for Orientation: `?`", actual = exception.message)
    }

    @Test fun shouldBeAbleToTurnLeft() {
        assertEquals(expected = Orientation.WEST, actual = Orientation.NORTH.turnLeft())
        assertEquals(expected = Orientation.SOUTH, actual = Orientation.WEST.turnLeft())
        assertEquals(expected = Orientation.EAST, actual = Orientation.SOUTH.turnLeft())
        assertEquals(expected = Orientation.NORTH, actual = Orientation.EAST.turnLeft())
    }

    @Test fun shouldBeAbleToTurnRight() {
        assertEquals(expected = Orientation.EAST, actual = Orientation.NORTH.turnRight())
        assertEquals(expected = Orientation.SOUTH, actual = Orientation.EAST.turnRight())
        assertEquals(expected = Orientation.WEST, actual = Orientation.SOUTH.turnRight())
        assertEquals(expected = Orientation.NORTH, actual = Orientation.WEST.turnRight())
    }
}
