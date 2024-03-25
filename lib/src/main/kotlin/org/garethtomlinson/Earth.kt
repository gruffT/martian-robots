package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

class Earth {
    companion object {
        fun mars(missionDetails: String): Mars {
            val missionDetailLines = missionDetailLines(missionDetails)
            if (missionDetailLines.isEmpty()) throw BadConfigurationException("Mission Details", "No Mars configuration")
            val marsConfiguration = missionDetailLines[0]
            return Mars.from(marsConfiguration)
        }

        private fun missionDetailLines(missionDetails: String): List<String> =
            missionDetails.trim().split("\n").filter { line -> line.isNotEmpty() }

        fun missions(missionDetails: String) {
            val missionDetailLines = missionDetailLines(missionDetails)
            if (missionDetailLines.isEmpty() || missionDetailLines.size < 3) {
                throw BadConfigurationException(
                    "Missions",
                    "No missions specified",
                )
            }
        }
    }
}
