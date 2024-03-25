package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class OutcomeTest {
    @Test fun shouldSerialiseToString() {
        assertEquals(expected = "1 1 N", actual = Outcome(robot = Robot.startingWith(1, 1, Orientation.NORTH), lost = false).toString())
    }

    @Test fun shouldSerialiseToStringIfLost() {
        assertEquals(expected = "2 2 S LOST", actual = Outcome(robot = Robot.startingWith(2, 2, Orientation.SOUTH), lost = true).toString())
    }
}
