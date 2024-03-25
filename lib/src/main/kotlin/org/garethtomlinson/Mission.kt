package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

class Mission {
    companion object {
        fun from(missionConfig: List<String>) {
            if (missionConfig.size != 2) {
                throw BadConfigurationException(
                    objectName = "Mission",
                    configuration = "Incorrect number of inputs given",
                )
            }
        }
    }
}
