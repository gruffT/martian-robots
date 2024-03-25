package org.garethtomlinson

class Missions private constructor() {
    companion object {
        fun from(configurationLines: List<List<String>>): List<Mission> {
            return listOf(Mission(), Mission())
        }
    }
}
