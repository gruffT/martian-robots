package org.garethtomlinson

data class Log(val reports: List<MissionReport>) {
    override fun toString(): String {
        return reports.joinToString(separator = "\n") { it.toString() }
    }
}
