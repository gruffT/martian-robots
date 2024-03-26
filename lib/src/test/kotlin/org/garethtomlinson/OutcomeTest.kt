package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OutcomeTest {
    private val mars = Mars.from("2 2")
    private val baseRobot = Robot.startingWith(2, 2, Orientation.NORTH)

    @Test fun shouldExecuteAnInstruction() {
        val outcome = Outcome.prepare(mars = mars).startNewMission(robot = baseRobot)
        val updatedOutcome = outcome.execute(Instruction.RIGHT)

        assertEquals(expected = mars, updatedOutcome.mars)
        assertTrue(updatedOutcome.robotPositions.size == 1)
        assertTrue(updatedOutcome.robotPositions[0].size == 2)
        assertEquals(expected = baseRobot, updatedOutcome.robotPositions[0][0])
        assertTrue(
            updatedOutcome.robotPositions[0][1].equivalent(Robot.startingWith(2, 2, Orientation.EAST)),
        )
    }

    @Test fun shouldReturnMissionReports() {
        val otherRobot = Robot.startingWith(2, 2, Orientation.SOUTH)
        val outcome =
            Outcome.prepare(mars)
                .startNewMission(robot = baseRobot)
                .startNewMission(robot = otherRobot)
        val missionReports = outcome.missionReports()

        assertTrue(missionReports.size == 2)
        assertEquals(expected = MissionReport(lastPosition = baseRobot, lost = false), actual = missionReports[0])
        assertEquals(expected = MissionReport(lastPosition = otherRobot, lost = false), actual = missionReports[1])
    }

    @Test fun shouldReturnMissionReportsForLostRobot() {
        val outcome = Outcome.prepare(Mars.from("1 1")).startNewMission(robot = baseRobot)
        val missionReports = outcome.missionReports()

        assertTrue(missionReports.size == 1)
        assertEquals(expected = MissionReport(lastPosition = baseRobot, lost = true), actual = missionReports[0])
    }
}
