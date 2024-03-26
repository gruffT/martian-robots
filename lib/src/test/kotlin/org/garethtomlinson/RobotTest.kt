package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertTrue

class RobotTest {
    private val baseRobot = Robot.with(1, 1, Orientation.NORTH)

    @Test fun shouldExecuteARightTurn() {
        assertTrue(
            Robot.with(1, 1, Orientation.EAST).equivalent(baseRobot.execute(Instruction.RIGHT)),
        )
    }

    @Test fun shouldExecuteALeftTurn() {
        assertTrue(
            Robot.with(1, 1, Orientation.WEST).equivalent(baseRobot.execute(Instruction.LEFT)),
        )
    }

    @Test fun shouldExecuteAForwardFromNorth() {
        assertTrue(
            Robot.with(1, 2, Orientation.NORTH).equivalent(baseRobot.execute(Instruction.FORWARD)),
        )
    }

    @Test fun shouldExecuteAForwardFromEast() {
        assertTrue(
            Robot.with(2, 1, Orientation.EAST).equivalent(Robot.with(1, 1, Orientation.EAST).execute(Instruction.FORWARD)),
        )
    }

    @Test fun shouldExecuteAForwardFromSouth() {
        assertTrue(
            Robot.with(
                1,
                0,
                Orientation.SOUTH,
            ).equivalent(Robot.with(1, 1, Orientation.SOUTH).execute(Instruction.FORWARD)),
        )
    }

    @Test fun shouldExecuteAForwardFromWest() {
        assertTrue(
            Robot.with(0, 1, Orientation.WEST).equivalent(Robot.with(1, 1, Orientation.WEST).execute(Instruction.FORWARD)),
        )
    }
}
