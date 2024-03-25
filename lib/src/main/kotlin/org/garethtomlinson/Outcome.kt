package org.garethtomlinson

data class Outcome(val robot: Robot, val lost: Boolean) {
    override fun toString(): String =
        "${robot.location.first} ${robot.location.second} ${robot.orientation.value}${if (lost) " LOST" else "" }"
}
