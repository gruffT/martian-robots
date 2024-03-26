package org.garethtomlinson

typealias RobotTrail = List<Robot>

class Outcome private constructor(val missions: List<RobotTrail>, val mars: Mars) {
    fun execute(instruction: Instruction): Outcome {
        val previousMissions = missions.dropLast(1)
        val previousLastLostPositions =
            previousMissions.map { mission -> mission.last() }
                .filter { robot -> !mars.insideBounds(robot) }

        val currentMission = missions.last()
        val currentRobot = currentMission.last()
        if (!mars.insideBounds(currentRobot)) return Outcome(missions = missions, mars = mars)

        val updatedRobot = currentRobot.execute(instruction)

        val updatedMission =
            currentMission +
                if (robotIsColocatedWithLostRobot(previousLastLostPositions, updatedRobot)) {
                    currentRobot
                } else {
                    updatedRobot
                }

        return Outcome(missions = previousMissions + listOf(updatedMission), mars)
    }

    private fun robotIsColocatedWithLostRobot(
        previousLastLostPositions: List<Robot>,
        updatedRobot: Robot,
    ) = previousLastLostPositions.any { robot -> updatedRobot.equivalent(robot) }

    fun missionReports(): List<MissionReport> {
        return missions.map { mission -> MissionReport(lastKnownPosition(mission), !mars.insideBounds(mission.last())) }
    }

    private fun lastKnownPosition(mission: RobotTrail): Robot {
        return mission.last { robot -> mars.insideBounds(robot) }
    }

    fun startNewMission(robot: Robot): Outcome {
        val robotTrail = listOf(robot)
        return Outcome(missions = missions + listOf(robotTrail), mars = mars)
    }

    companion object {
        fun prepare(mars: Mars): Outcome = Outcome(missions = listOf(), mars = mars)
    }
}
