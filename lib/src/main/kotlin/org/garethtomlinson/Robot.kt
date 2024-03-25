package org.garethtomlinson

class Robot private constructor(val location: Location, val orientation: Orientation) {
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
