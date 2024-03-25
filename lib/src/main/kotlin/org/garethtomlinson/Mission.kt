package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

class Mission private constructor(val robot: Robot) {
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
            return Mission(robot)
        }

        private val robotRegex = """^(\d+) (\d+) ([NESW])$""".toRegex()
    }
}
