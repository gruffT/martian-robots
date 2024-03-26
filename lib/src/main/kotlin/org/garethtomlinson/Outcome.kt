package org.garethtomlinson

class Outcome private constructor(val robotPositions: List<Robot>, val mars: Mars) {
    fun execute(instruction: Instruction): Outcome {
        return Outcome(robotPositions = robotPositions + robotPositions.last().execute(instruction), mars)
    }

    fun isLost(): Boolean {
        return !mars.insideBounds(robotPositions.last())
    }

    fun lastPosition(): Robot {
        return robotPositions.last()
    }

    override fun toString(): String {
        if (robotPositions.isEmpty()) return "Mission has no outcome"
        val robot = robotPositions.last()
        return "${robot.location.first} ${robot.location.second} ${robot.orientation.value}${if (isLost()) " LOST" else "" }"
    }

    companion object {
        fun fromFirstMission(
            robot: Robot,
            mars: Mars,
        ): Outcome {
            return Outcome(robotPositions = listOf(robot), mars = mars)
        }
    }
}
