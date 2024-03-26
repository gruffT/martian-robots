package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OutcomeTest {
    private val mars = Mars.from("2 2")
    private val baseRobot = Robot.with(2, 2, Orientation.NORTH)

    @Test fun shouldExecuteAnInstruction() {
        val outcome = Outcome.prepare(mars = mars).startNewMission(robot = baseRobot)
        val updatedOutcome = outcome.execute(Instruction.RIGHT)

        assertEquals(expected = mars, updatedOutcome.mars)
        assertTrue(updatedOutcome.missions.size == 1)
        assertTrue(updatedOutcome.missions[0].size == 2)
        assertEquals(expected = baseRobot, updatedOutcome.missions[0][0])
        assertTrue(
            updatedOutcome.missions[0][1].equivalent(Robot.with(2, 2, Orientation.EAST)),
        )
    }

    @Test fun shouldIgnoreCommandsIfLost() {
        val outcome =
            Outcome.prepare(mars = mars).startNewMission(robot = baseRobot)
                .execute(Instruction.FORWARD)
                .execute(Instruction.FORWARD)
                .execute(Instruction.RIGHT)

        val missionReport = outcome.missionReports()[0]
        assertTrue(missionReport.lost)
        assertTrue(missionReport.lastPosition.equivalent(baseRobot))
        assertTrue(outcome.missions.last().last().equivalent(Robot.with(2, 3, Orientation.NORTH)))
    }

    @Test fun shouldIgnoreInstructionIfItWouldMoveRobotToLostRobotSite() {
        val newRobot = Robot.with(2, 2, Orientation.NORTH)

        val outcome =
            Outcome.prepare(mars = mars).startNewMission(robot = baseRobot)
                .execute(Instruction.FORWARD)
                .startNewMission(newRobot)
                .execute(Instruction.FORWARD)

        assertTrue(outcome.missionReports().size == 2)
        val firstMissionReport = outcome.missionReports().first()
        assertTrue(firstMissionReport.lost)
        assertTrue(firstMissionReport.lastPosition.equivalent(baseRobot))
        assertTrue(outcome.missions.first().last().equivalent(Robot.with(2, 3, Orientation.NORTH)))

        val secondMissionReport = outcome.missionReports().last()
        assertFalse(secondMissionReport.lost)
        assertTrue(secondMissionReport.lastPosition.equivalent(newRobot))

        assertTrue(outcome.missions.size == 2)
        assertTrue(outcome.missions[0].size == 2)

        assertRobotHasIgnoredCommand(outcome.missions[1][0], outcome.missions[1][1])
    }

    private fun assertRobotHasIgnoredCommand(
        original: Robot,
        updated: Robot,
    ) {
        assertTrue(original.equivalent(updated))
    }

    @Test fun shouldReturnMissionReports() {
        val otherRobot = Robot.with(2, 2, Orientation.SOUTH)
        val outcome =
            Outcome.prepare(mars)
                .startNewMission(robot = baseRobot)
                .startNewMission(robot = otherRobot)
        val missionReports = outcome.missionReports()

        assertTrue(missionReports.size == 2)
        assertEquals(expected = MissionReport(lastPosition = baseRobot, lost = false), actual = missionReports[0])
        assertEquals(expected = MissionReport(lastPosition = otherRobot, lost = false), actual = missionReports[1])
    }

    @Test fun shouldReturnMissionReportsForLostRobotStatingLastKnownPosition() {
        val outcome = Outcome.prepare(Mars.from("2 2")).startNewMission(robot = baseRobot).execute(Instruction.FORWARD)
        val missionReports = outcome.missionReports()

        assertTrue(missionReports.size == 1)
        assertTrue(missionReports[0].lost)
        assertTrue(missionReports[0].lastPosition.equivalent(baseRobot))
    }
}
