package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals

class LogTest {
    @Test fun shouldSerialiseToString() {
        val mars = Mars.from("1 1")
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
                            Outcome.fromFirstMission(robot = Robot.startingWith(1, 1, Orientation.NORTH), mars = mars),
                            Outcome.fromFirstMission(robot = Robot.startingWith(2, 2, Orientation.SOUTH), mars = mars),
                        ),
                ).toString(),
        )
    }
}
