package org.garethtomlinson

class Robot private constructor(val location: Location, val orientation: Orientation) {
    fun execute(instruction: Instruction): Robot {
        return when (instruction) {
            Instruction.LEFT -> Robot(location, orientation.turnLeft())
            Instruction.RIGHT -> Robot(location, orientation.turnRight())
            Instruction.FORWARD -> Robot(stepInDirection(location, orientation), orientation)
        }
    }

    private fun stepInDirection(
        location: Location,
        orientation: Orientation,
    ): Location {
        return when (orientation) {
            Orientation.NORTH -> Location(location.x(), location.y() + 1)
            Orientation.EAST -> Location(location.x() + 1, location.y())
            Orientation.SOUTH -> Location(location.x(), location.y() - 1)
            Orientation.WEST -> Location(location.x() - 1, location.y())
        }
    }

    fun equivalent(other: Robot): Boolean {
        return location == other.location && orientation == other.orientation
    }

    companion object {
        fun with(
            x: Int,
            y: Int,
            orientation: Orientation,
        ): Robot {
            return Robot(Location(x, y), orientation)
        }
    }
}
