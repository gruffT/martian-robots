package org.garethtomlinson.reporting

import org.garethtomlinson.Robot
import org.garethtomlinson.x
import org.garethtomlinson.y

data class MissionReport(val lastPosition: Robot, val lost: Boolean) {
    override fun toString(): String {
        return "${lastPosition.location.x()} ${lastPosition.location.y()} ${lastPosition.orientation.value}" +
            if (lost) " LOST" else ""
    }
}
