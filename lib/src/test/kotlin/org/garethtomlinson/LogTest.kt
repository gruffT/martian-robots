package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class LogTest {
    @Test fun shouldSerialiseToString() {
        assertEquals(
            expected =
                """
                1 1 N
                2 2 S LOST
                """.trimIndent(),
            actual =
                Log(
                    outcomes =
                        listOf(
                            Outcome(robot = Robot.startingWith(1, 1, Orientation.NORTH), lost = false),
                            Outcome(robot = Robot.startingWith(2, 2, Orientation.SOUTH), lost = true),
                        ),
                ).toString(),
        )
    }
}
