package org.garethtomlinson

import org.garethtomlinson.mission.Mars
import org.garethtomlinson.reporting.MissionReport

typealias RobotTrail = List<Robot>

class Status private constructor(val missions: List<RobotTrail>, val mars: Mars) {
    fun execute(instruction: Instruction): Status {
        val previousMissions = missions.dropLast(1)
        val previousLostPositions =
            previousMissions.map { mission -> mission.last() }
                .filter { robot -> !mars.insideBounds(robot) }

        val currentMission = missions.last()
        val currentRobot = currentMission.last()

        // Ignore commands to a lost Robot
        if (!mars.insideBounds(currentRobot)) return Status(missions = missions, mars = mars)

        val updatedRobot = currentRobot.execute(instruction)

        val updatedMission =
            currentMission +
                if (robotIsColocatedWithLostRobot(previousLostPositions, updatedRobot)) {
                    currentRobot
                } else {
                    updatedRobot
                }

        return Status(missions = previousMissions + listOf(updatedMission), mars)
    }

    private fun robotIsColocatedWithLostRobot(
        previousLostPositions: List<Robot>,
        updatedRobot: Robot,
    ): Boolean = previousLostPositions.any { robot -> updatedRobot.equivalent(robot) }

    fun missionReports(): List<MissionReport> {
        return missions.map { mission -> MissionReport(lastKnownPosition(mission), !mars.insideBounds(mission.last())) }
    }

    private fun lastKnownPosition(mission: RobotTrail): Robot = mission.last { robot -> mars.insideBounds(robot) }

    fun startNewMission(robot: Robot): Status {
        val robotTrail = listOf(robot)
        return Status(missions = missions + listOf(robotTrail), mars = mars)
    }

    companion object {
        fun prepare(mars: Mars): Status = Status(missions = listOf(), mars = mars)
    }
}
