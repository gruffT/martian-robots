package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MissionReportTest {
    private val robot = Robot.startingWith(2, 2, Orientation.NORTH)

    @Test fun shouldSerialiseToString() {
        assertEquals(
            expected = "2 2 N",
            actual = MissionReport(lastPosition = robot, lost = false).toString(),
        )
    }

    @Test fun shouldSerialiseToStringIfLost() {
        assertEquals(
            expected = "2 2 N LOST",
            actual = MissionReport(lastPosition = robot, lost = true).toString(),
        )
    }

    @Test fun shouldExposeDataMembers() {
        val report = MissionReport(lastPosition = robot, lost = true)
        assertEquals(expected = robot, actual = report.lastPosition)
        assertTrue(report.lost)
    }
}
