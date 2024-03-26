package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

enum class Orientation(val value: String) {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W"),
    ;

    fun turnLeft(): Orientation {
        val newOrdinal = (this.ordinal + 4 - 1) % 4 // Prevents -1 when going left from NORTH
        return entries[newOrdinal]
    }

    fun turnRight(): Orientation {
        val newOrdinal = (this.ordinal + 1) % 4
        return entries[newOrdinal]
    }

    companion object {
        fun from(shortOrientation: String): Orientation {
            return entries.find { it.value == shortOrientation }
                ?: throw BadConfigurationException("Orientation", shortOrientation)
        }
    }
}
