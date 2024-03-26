package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import org.garethtomlinson.exceptions.MissedPlanetException

class Mission private constructor(val robotPositions: List<Robot>, val instructions: List<Instruction>, val mars: Mars) {
    fun execute(instruction: Instruction): Mission {
        return Mission(robotPositions = robotPositions + robotPositions.last().execute(instruction), listOf(), mars)
    }

    companion object {
        fun from(
            missionConfig: List<String>,
            mars: Mars,
        ): Mission {
            if (missionConfig.size != 2) {
                throw BadConfigurationException(
                    objectName = "Mission",
                    configuration = "Incorrect number of inputs given",
                )
            }
            val (robotConfiguration, instructionConfiguration) = missionConfig

            val robotElements =
                robotRegex.find(robotConfiguration)
                    ?: throw BadConfigurationException("Robot", robotConfiguration)
            val (x, y, shortOrientation) = robotElements.destructured
            val robot = Robot.startingWith(x.toInt(), y.toInt(), Orientation.from(shortOrientation))
            if (!mars.insideBounds(robot)) throw MissedPlanetException(robot)

            if (!instructionConfiguration.matches(
                    instructionRegex,
                )
            ) {
                throw BadConfigurationException("Instructions", instructionConfiguration)
            }
            val instructions: List<Instruction> =
                instructionConfiguration.map { Instruction.from(it.toString()) }

            return Mission(robotPositions = listOf(robot), instructions = instructions, mars = mars)
        }

        private val robotRegex = """^(\d+) (\d+) ([NESW])$""".toRegex()
        private val instructionRegex = """^[FLR]*$""".toRegex()

        fun outcome(
            mission: Mission,
            mars: Mars,
        ): Outcome {
            val lastRobotPosition = mission.robotPositions.last()
            return Outcome(robot = lastRobotPosition, lost = !mars.insideBounds(lastRobotPosition))
        }
    }
}
