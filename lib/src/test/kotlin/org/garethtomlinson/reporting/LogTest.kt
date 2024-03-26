package org.garethtomlinson.reporting

import org.garethtomlinson.Orientation
import org.garethtomlinson.Robot
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
                    reports =
                        listOf(
                            MissionReport(lastPosition = Robot.with(1, 1, Orientation.NORTH), lost = false),
                            MissionReport(lastPosition = Robot.with(2, 2, Orientation.SOUTH), lost = true),
                        ),
                ).toString(),
        )
    }
}
