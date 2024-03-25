package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException
import org.garethtomlinson.exceptions.BigPlanetException

class Mars private constructor(val width: Int, val height: Int) {
    fun insideBounds(robot: Robot): Boolean {
        val x = robot.location.first
        val y = robot.location.second
        return x in 0..<width && y in 0..<height
    }

    companion object {
        fun from(configuration: String): Mars {
            val configurationElements =
                configurationRegex.find(configuration.trim())
                    ?: throw BadConfigurationException("Mars", configuration)
            val (widthBound, heightBound) = configurationElements.destructured

            val width = widthBound.toInt() + 1
            val height = heightBound.toInt() + 1

            if (width > 51 || height > 51) throw BigPlanetException(configuration)

            return Mars(width, height)
        }

        private val configurationRegex = """^(\d+) (\d+)$""".toRegex()
    }
}
