package org.garethtomlinson

data class Log(val outcomes: List<Outcome>) {
    override fun toString(): String {
        return outcomes.joinToString(separator = "\n") { it.toString() }
    }
}
