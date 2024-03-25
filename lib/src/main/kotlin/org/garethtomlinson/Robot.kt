package org.garethtomlinson

class Robot private constructor(val location: Location, val orientation: Orientation) {
    fun equivalent(other: Robot): Boolean {
        return location == other.location && orientation == other.orientation
    }

    fun execute(instruction: Instruction): Robot {
        return when (instruction) {
            Instruction.RIGHT -> Robot(location, Orientation.EAST)
            else -> Robot(location, orientation)
        }
    }

    companion object {
        fun startingWith(
            x: Int,
            y: Int,
            orientation: Orientation,
        ): Robot {
            return Robot(Location(x, y), orientation)
        }
    }
}
