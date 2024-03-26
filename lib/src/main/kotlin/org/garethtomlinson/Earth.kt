package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import org.garethtomlinson.mission.Mars
import org.garethtomlinson.mission.Mission
import org.garethtomlinson.reporting.Log

class Earth {
    companion object {
        fun mars(missionDetails: String): Mars {
            val missionDetailLines = missionDetails.trim().split("\n").filter { line -> line.isNotEmpty() }
            if (missionDetailLines.isEmpty()) throw BadConfigurationException("Mission Details", "No Mars configuration")
            val marsConfiguration = missionDetailLines[0]
            return Mars.from(marsConfiguration)
        }

        fun missions(
            missionDetails: String,
            mars: Mars,
        ): List<Mission> {
            val missionDetailsWithNoMars = missionDetails.trim().split("\n").drop(1).map { it.trim() }
            val missionChunks = missionDetailsWithNoMars.chunked(3).map { chunk -> chunk.filter { line -> line != "" } }
            if (missionDetails.trim().isEmpty() || missionChunks.isEmpty()) {
                throw BadConfigurationException(
                    "Missions",
                    "No missions specified",
                )
            }
            if (missionChunks.any { chunk -> chunk.size != 2 }) throw BadConfigurationException("Missions", "Input incorrectly chunked")
            return missionChunks.map { chunk -> Mission.from(missionConfig = chunk, mars = mars) }
        }

        fun expedition(
            missions: List<Mission>,
            mars: Mars,
        ): Log {
            return Log(
                missions.fold(Outcome.prepare(mars)) {
                        outcome, mission ->
                    executeMission(mission, outcome)
                }.missionReports(),
            )
        }

        private fun executeMission(
            mission: Mission,
            outcome: Outcome,
        ): Outcome {
            val missionStart = outcome.startNewMission(mission.robot)
            if (mission.instructions.isEmpty()) return missionStart
            val missionOutcome: Outcome =
                mission.instructions.fold(missionStart) {
                        outcomeStage: Outcome, instruction: Instruction ->
                    outcomeStage.execute(instruction)
                }
            return missionOutcome
        }
    }
}
