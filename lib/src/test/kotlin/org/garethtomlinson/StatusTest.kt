package org.garethtomlinson

import org.garethtomlinson.mission.Mars
import org.garethtomlinson.reporting.MissionReport
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StatusTest {
    private val mars = Mars.from("2 2")
    private val baseRobot = Robot.with(2, 2, Orientation.NORTH)

    @Test fun shouldExecuteAnInstruction() {
        val status = Status.prepare(mars = mars).startNewMission(robot = baseRobot)
        val updatedStatus = status.execute(Instruction.RIGHT)

        assertEquals(expected = mars, updatedStatus.mars)
        assertTrue(updatedStatus.missions.size == 1)
        assertTrue(updatedStatus.missions[0].size == 2)
        assertEquals(expected = baseRobot, updatedStatus.missions[0][0])
        assertTrue(
            updatedStatus.missions[0][1].equivalent(Robot.with(2, 2, Orientation.EAST)),
        )
    }

    @Test fun shouldIgnoreCommandsIfLost() {
        val status =
            Status.prepare(mars = mars).startNewMission(robot = baseRobot)
                .execute(Instruction.FORWARD)
                .execute(Instruction.FORWARD)
                .execute(Instruction.RIGHT)

        val missionReport = status.missionReports()[0]
        assertTrue(missionReport.lost)
        assertTrue(missionReport.lastPosition.equivalent(baseRobot))
        assertTrue(status.missions.last().last().equivalent(Robot.with(2, 3, Orientation.NORTH)))
    }

    @Test fun shouldIgnoreInstructionIfItWouldMoveRobotToLostRobotSite() {
        val newRobot = Robot.with(2, 2, Orientation.NORTH)

        val status =
            Status.prepare(mars = mars).startNewMission(robot = baseRobot)
                .execute(Instruction.FORWARD)
                .startNewMission(newRobot)
                .execute(Instruction.FORWARD)

        assertTrue(status.missionReports().size == 2)
        val firstMissionReport = status.missionReports().first()
        assertTrue(firstMissionReport.lost)
        assertTrue(firstMissionReport.lastPosition.equivalent(baseRobot))
        assertTrue(status.missions.first().last().equivalent(Robot.with(2, 3, Orientation.NORTH)))

        val secondMissionReport = status.missionReports().last()
        assertFalse(secondMissionReport.lost)
        assertTrue(secondMissionReport.lastPosition.equivalent(newRobot))

        assertTrue(status.missions.size == 2)
        assertTrue(status.missions[0].size == 2)

        assertRobotHasIgnoredCommand(status.missions[1][0], status.missions[1][1])
    }

    private fun assertRobotHasIgnoredCommand(
        original: Robot,
        updated: Robot,
    ) {
        assertTrue(original.equivalent(updated))
    }

    @Test fun shouldReturnMissionReports() {
        val otherRobot = Robot.with(2, 2, Orientation.SOUTH)
        val status =
            Status.prepare(mars)
                .startNewMission(robot = baseRobot)
                .startNewMission(robot = otherRobot)
        val missionReports = status.missionReports()

        assertTrue(missionReports.size == 2)
        assertEquals(expected = MissionReport(lastPosition = baseRobot, lost = false), actual = missionReports[0])
        assertEquals(expected = MissionReport(lastPosition = otherRobot, lost = false), actual = missionReports[1])
    }

    @Test fun shouldReturnMissionReportsForLostRobotStatingLastKnownPosition() {
        val status = Status.prepare(Mars.from("2 2")).startNewMission(robot = baseRobot).execute(Instruction.FORWARD)
        val missionReports = status.missionReports()

        assertTrue(missionReports.size == 1)
        assertTrue(missionReports[0].lost)
        assertTrue(missionReports[0].lastPosition.equivalent(baseRobot))
    }
}
