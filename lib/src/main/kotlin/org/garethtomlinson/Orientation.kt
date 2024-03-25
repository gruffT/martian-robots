package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

enum class Orientation(val value: String) {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W"),
    ;

    companion object {
        fun from(shortOrientation: String): Orientation {
            return entries.find { it.value == shortOrientation }
                ?: throw BadConfigurationException("Orientation", shortOrientation)
        }
    }
}
