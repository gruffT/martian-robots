package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

class Earth {
    companion object {
        fun mars(missionDetails: String): Mars {
            val missionDetailLines = missionDetails.trim().split("\n").filter { line -> line.isNotEmpty() }
            if (missionDetailLines.isEmpty()) throw BadConfigurationException("Mission Details", "No Mars configuration")
            val marsConfiguration = missionDetailLines[0]
            return Mars.from(marsConfiguration)
        }

        fun missions(missionDetails: String): List<Mission> {
            val missionDetailsWithNoMars = missionDetails.trim().split("\n").drop(1)
            val missionChunks = missionDetailsWithNoMars.chunked(3).map { chunk -> chunk.filter { line -> line != "" } }
            if (missionDetails.trim().isEmpty() || missionChunks.isEmpty()) {
                throw BadConfigurationException(
                    "Missions",
                    "No missions specified",
                )
            }
            if (missionChunks.any { chunk -> chunk.size != 2 }) throw BadConfigurationException("Missions", "Input incorrectly chunked")
            return Missions.from(missionChunks)
        }
    }
}
