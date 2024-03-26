package org.garethtomlinson

class Outcome private constructor(val robotPositions: List<List<Robot>>, val mars: Mars) {
    fun execute(instruction: Instruction): Outcome {
        val previousMissions = robotPositions.dropLast(1)
        val currentMission = robotPositions.last()
        val updatedMission = currentMission + currentMission.last().execute(instruction)
        return Outcome(robotPositions = previousMissions + listOf(updatedMission), mars)
    }

    fun missionReports(): List<MissionReport> {
        return robotPositions.map { mission -> MissionReport(mission.last(), !mars.insideBounds(mission.last())) }
    }

    fun startNewMission(robot: Robot): Outcome {
        return Outcome(robotPositions = robotPositions + listOf(listOf(robot)), mars = mars)
    }

    companion object {
        fun prepare(mars: Mars): Outcome = Outcome(robotPositions = listOf(), mars = mars)
    }
}
