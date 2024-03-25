package org.garethtomlinson

import org.garethtomlinson.exceptions.BadConfigurationException

enum class Instruction(val value: String) {
    FORWARD("F"),
    LEFT("L"),
    RIGHT("R"),
    ;

    companion object {
        fun from(shortInstruction: String): Instruction {
            return Instruction.entries.find { it.value == shortInstruction }
                ?: throw BadConfigurationException("Instruction", shortInstruction)
        }
    }
}
