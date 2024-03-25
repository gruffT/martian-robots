package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

class Mission private constructor(val robot: Robot, val instructions: List<Instruction>) {
    fun execute(mars: Mars): Outcome {
        return Outcome(robot = robot, lost = false)
    }

    companion object {
        fun from(missionConfig: List<String>): Mission {
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

            if (!instructionConfiguration.matches(
                    instructionRegex,
                )
            ) {
                throw BadConfigurationException("Instructions", instructionConfiguration)
            }
            val instructions: List<Instruction> =
                instructionConfiguration.map { Instruction.from(it.toString()) }

            return Mission(robot, instructions)
        }

        private val robotRegex = """^(\d+) (\d+) ([NESW])$""".toRegex()
        private val instructionRegex = """^[FLR]*$""".toRegex()
    }
}
