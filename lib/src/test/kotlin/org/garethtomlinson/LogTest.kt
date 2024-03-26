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
                    reports =
                        listOf(
                            MissionReport(lastPosition = Robot.startingWith(1, 1, Orientation.NORTH), lost = false),
                            MissionReport(lastPosition = Robot.startingWith(2, 2, Orientation.SOUTH), lost = true),
                        ),
                ).toString(),
        )
    }
}
