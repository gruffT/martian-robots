package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class OutcomeTest {
    private val mars = Mars.from("2 2")
    private val baseRobot = Robot.startingWith(2, 2, Orientation.NORTH)

    @Test fun shouldSerialiseToString() {
        assertEquals(
            expected = "2 2 N",
            actual = Outcome.fromFirstMission(robot = baseRobot, mars = mars).toString(),
        )
    }

    @Test fun shouldSerialiseToStringIfLost() {
        assertEquals(
            expected = "3 3 S LOST",
            actual = Outcome.fromFirstMission(robot = Robot.startingWith(3, 3, Orientation.SOUTH), mars = mars).toString(),
        )
    }

    @Test fun shouldReportIfLost() {
        assertTrue(
            Outcome.fromFirstMission(
                robot = baseRobot,
                mars = Mars.from("1 1"),
            ).isLost(),
        )
    }

    @Test fun shouldReportIfNotLost() {
        assertFalse(Outcome.fromFirstMission(robot = baseRobot, mars = mars).isLost())
    }

    @Test fun shouldReturnLastPositionOfMission() {
        assertTrue(
            Outcome.fromFirstMission(robot = baseRobot, mars = mars).lastPosition().equivalent(baseRobot),
        )
    }

    @Test fun shouldExecuteAnInstruction() {
        val outcome = Outcome.fromFirstMission(robot = baseRobot, mars = mars)
        val updatedOutcome = outcome.execute(Instruction.RIGHT)

        assertEquals(expected = mars, updatedOutcome.mars)
        assertTrue(updatedOutcome.robotPositions.size == 2)
        assertEquals(expected = baseRobot, updatedOutcome.robotPositions[0])
        assertTrue(
            updatedOutcome.robotPositions[1].equivalent(Robot.startingWith(2, 2, Orientation.EAST)),
        )
    }
}
