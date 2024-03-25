package org.garethtomlinson

import kotlin.test.Test
import kotlin.test.assertTrue

class RobotTest {
    val baseRobot = Robot.startingWith(1, 1, Orientation.NORTH)

    @Test fun shouldExecuteARightTurn() {
        assertTrue(
            Robot.startingWith(1, 1, Orientation.EAST).equivalent(baseRobot.execute(Instruction.RIGHT)),
        )
    }
}
