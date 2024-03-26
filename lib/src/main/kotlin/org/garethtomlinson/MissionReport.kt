package org.garethtomlinson

data class MissionReport(val lastPosition: Robot, val lost: Boolean) {
    override fun toString(): String {
        return "${lastPosition.location.x()} ${lastPosition.location.y()} ${lastPosition.orientation.value}" +
            if (lost) " LOST" else ""
    }
}
